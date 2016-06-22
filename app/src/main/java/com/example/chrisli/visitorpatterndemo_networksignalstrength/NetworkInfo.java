package com.example.chrisli.visitorpatterndemo_networksignalstrength;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;

import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.CellInfoGetSignalStrengthVisitor;
import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.CellInfoWrapper;

import java.util.List;

/**
 * Created by cli on 22/06/2016.
 */
public class NetworkInfo {

    public static Integer getWifiSignalStrength(Context context){
        Integer signalStrength = null;
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if(activeNetwork != null) {
                String networkType = activeNetwork.getTypeName();
                if (networkType != null) {
                    if (networkType.toUpperCase().equals("WIFI")) {
                        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
                        if (wifiManager != null) {
                            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                            if (wifiInfo != null) {
                                signalStrength = wifiInfo.getRssi(); //see http://www.veris.com/docs/whitePaper/vwp18_RSSI_RevA.pdf for how to interpret Received Signal Strength Indicator (RSSI) value
                            }
                        }
                    }
                }
            }
        }
        return signalStrength;
    }

    public static Integer getCellularSignalStrength(Context context){
        Integer signalStrength = null;

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null) {
            String networkType = activeNetwork.getTypeName();
            if (networkType != null) {
                if (networkType.toUpperCase().equals("MOBILE")) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    if (telephonyManager != null) {
                        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();
                        //Have found that the function always returns null as there are bugs on some devices(e.g., Samsung S5, Note 2 and 3 etc) with particular Android OS
                        //See http://stackoverflow.com/questions/16541172/getallcellinfo-returns-null-in-android-4-2-1 for details
                        if (cellInfos != null) {
                            Integer signalLevel = null;
                            for (final CellInfo cellInfo : cellInfos) {
                                if (cellInfo != null && cellInfo.isRegistered()) {
                                    Integer signalLevelThisCell = getCellSignalStrength(cellInfo);
                                    if (signalLevelThisCell != null) {
                                        if (signalLevel == null || signalLevel <= signalLevelThisCell) { //find the best signal level among all registered cells
                                            signalStrength = signalLevelThisCell.intValue();
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        return signalStrength;
    }

    private static Integer getCellSignalStrength(CellInfo cellInfo) {
        Integer signalLevel = null;
        if (cellInfo != null) {
            CellInfoWrapper cellInfoWrapper = new CellInfoWrapper(cellInfo);
            signalLevel = cellInfoWrapper.getSignalStrength(new CellInfoGetSignalStrengthVisitor());
        }
        return signalLevel;
    }

    //this function has a problem that the way to get cellular network strength via a series of if-else clauses along with instanceOf() which not only is not recommended but also the code is hard to maintain
    public static Integer getCellularSignalStrength_notRecommended(Context context){
        Integer signalStrength = null;

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null) {
            String networkType = activeNetwork.getTypeName();
            if (networkType != null) {
                if (networkType.toUpperCase() == "MOBILE") {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    if (telephonyManager != null) {
                        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();
                        //Have found that the function always returns null as there are bugs on some devices(e.g., Samsung) with particular Android OS
                        //See http://stackoverflow.com/questions/16541172/getallcellinfo-returns-null-in-android-4-2-1 for details
                        if (cellInfos != null) {
                            Integer signalLevel = null;
                            for (final CellInfo cellInfo : cellInfos) {
                                if (cellInfo != null && cellInfo.isRegistered()) {
                                    Integer signalLevelThisCell = null;
                                    if (cellInfo instanceof CellInfoLte) {
                                        signalLevelThisCell = ((CellInfoLte) cellInfo).getCellSignalStrength().getLevel();
                                    } else if (cellInfo instanceof CellInfoGsm) {
                                        signalLevelThisCell = ((CellInfoGsm) cellInfo).getCellSignalStrength().getLevel();
                                    } else if (cellInfo instanceof CellInfoCdma) { //Not available in AU, no harm to check though
                                        signalLevelThisCell = ((CellInfoCdma) cellInfo).getCellSignalStrength().getLevel();
                                    } else if (cellInfo instanceof CellInfoWcdma) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                                            signalLevelThisCell = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getLevel();
                                        }
                                    }
                                    if (signalLevelThisCell != null) {
                                        if (signalLevel == null || signalLevel <= signalLevelThisCell) { //find the best signal level among all registered cells
                                            signalLevel = signalLevelThisCell.intValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return signalStrength;
    }

}
