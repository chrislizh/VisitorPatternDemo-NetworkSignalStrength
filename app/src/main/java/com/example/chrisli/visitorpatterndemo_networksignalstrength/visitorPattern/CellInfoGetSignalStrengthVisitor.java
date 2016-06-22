package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern;

import android.os.Build;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cli on 16/05/2016.
 */
public class CellInfoGetSignalStrengthVisitor implements ICellInfoVisitor {


    public Integer visitCellInfoLte(CellInfoLte cellInfoLte) {
        return cellInfoLte.getCellSignalStrength().getLevel();
    }


    public Integer visitCellInfoGsm(CellInfoGsm cellInfoGsm) {
        return cellInfoGsm.getCellSignalStrength().getLevel();
    }


    public Integer visitCellInfoCdma(CellInfoCdma cellInfoCdma) {
        return cellInfoCdma.getCellSignalStrength().getLevel();
    }


    public Integer visitCellInfoWcdma(CellInfoWcdma cellInfoWcdma) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return cellInfoWcdma.getCellSignalStrength().getLevel();
        }
        return null;
    }

    @Override
    public Integer visit(Object obj) {
        Integer signalStrength = null;
        String strMethod = "visit" + obj.getClass().getSimpleName(); //utilize reflection to get the proper function name

        try {
            Method method = getClass().getMethod(strMethod, new Class[]{obj.getClass()});
            try {
                signalStrength = (Integer) method.invoke(this, obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //all other unexpected exceptions
            e.printStackTrace();
        }

        return signalStrength;
    }
}
