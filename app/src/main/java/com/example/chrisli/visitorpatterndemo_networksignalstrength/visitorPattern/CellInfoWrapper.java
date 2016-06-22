package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern;

import android.telephony.CellInfo;

/**
 * Created by cli on 16/05/2016.
 */
public class CellInfoWrapper implements ICellInfo {

    CellInfo cellInfo_ = null;

    public CellInfoWrapper(CellInfo cellInfo) {
        cellInfo_ = cellInfo;
    }

    @Override
    public Integer getSignalStrength(ICellInfoVisitor iCellInfoVisitor) {
        return iCellInfoVisitor.visit(cellInfo_);
    }
}
