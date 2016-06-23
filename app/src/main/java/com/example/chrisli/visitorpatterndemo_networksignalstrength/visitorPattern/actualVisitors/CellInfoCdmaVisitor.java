package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors;

import android.telephony.CellInfoCdma;

import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.IVisitor;

/**
 * Created by cli on 23/06/2016.
 */
public class CellInfoCdmaVisitor implements IVisitor<CellInfoCdma> {
    @Override
    public Integer visit(CellInfoCdma cellInfoCdma) {
        return cellInfoCdma.getCellSignalStrength().getLevel();    }
}
