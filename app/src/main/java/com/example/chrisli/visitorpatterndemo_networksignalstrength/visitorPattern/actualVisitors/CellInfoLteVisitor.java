package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors;

import android.telephony.CellInfoLte;

import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.IVisitor;

/**
 * Created by cli on 23/06/2016.
 */
public class CellInfoLteVisitor implements IVisitor<CellInfoLte> {
    @Override
    public Integer visit(CellInfoLte cellInfoLte) {
        return cellInfoLte.getCellSignalStrength().getLevel();
    }
}
