package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern;

import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;

import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors.CellInfoCdmaVisitor;
import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors.CellInfoGsmVisitor;
import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors.CellInfoLteVisitor;
import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors.CellInfoWcdmaVisitor;
import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors.DummyVisitor;

/**
 * Created by cli on 23/06/2016.
 */
public class CellInfoVisitor implements IPolymorphicVisitor {
    @Override
    public <T> IVisitor<T> getActualVisitor(Class<? extends CellInfo> elementClass) {

        if (elementClass == CellInfoLte.class) {
            return (IVisitor<T>) new CellInfoLteVisitor();
        } else if (elementClass == CellInfoGsm.class) {
            return (IVisitor<T>) new CellInfoGsmVisitor();
        } else if (elementClass == CellInfoCdma.class) {
            return (IVisitor<T>) new CellInfoCdmaVisitor();
        } else if (elementClass == CellInfoWcdma.class) {
            return (IVisitor<T>) new CellInfoWcdmaVisitor();
        }

        return (IVisitor<T>) new DummyVisitor(); //return a dummy visitor if the passed in class is not in the supporting list
    }
}
