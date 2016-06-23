package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors;

import android.telephony.CellInfoGsm;

import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.IVisitor;

/**
 * Created by cli on 23/06/2016.
 */
public class CellInfoGsmVisitor implements IVisitor<CellInfoGsm> {

    @Override
    public Integer visit(CellInfoGsm cellInfoGsm) {
        return cellInfoGsm.getCellSignalStrength().getLevel();    }
}
