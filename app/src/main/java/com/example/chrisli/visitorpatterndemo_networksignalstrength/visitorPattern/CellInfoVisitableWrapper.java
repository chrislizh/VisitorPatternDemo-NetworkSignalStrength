package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern;

import android.telephony.CellInfo;

/**
 * Created by cli on 23/06/2016.
 */
public class CellInfoVisitableWrapper implements IPolymorphicVisitable {

    private CellInfo cellInfo_;

    public CellInfoVisitableWrapper (CellInfo cellInfo) {
        cellInfo_ = cellInfo;
    }


    //just a wrapper function for accept() that has a more meaningful name in this use case
    public Integer getCellSignalStrength(IPolymorphicVisitor pv) {
        return accept(pv);
    }

    @Override
    public Integer accept(IPolymorphicVisitor pv) {
        if (cellInfo_ != null && pv != null) {
            return pv.getActualVisitor(cellInfo_.getClass()).visit(cellInfo_);
        }
        return null;
    }
}
