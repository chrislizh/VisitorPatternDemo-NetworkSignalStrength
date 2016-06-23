package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors;

import android.os.Build;
import android.telephony.CellInfoWcdma;

import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.IVisitor;

/**
 * Created by cli on 23/06/2016.
 */
public class CellInfoWcdmaVisitor implements IVisitor<CellInfoWcdma> {
    @Override
    public Integer visit(CellInfoWcdma cellInfoWcdma) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return cellInfoWcdma.getCellSignalStrength().getLevel();
        }
        return null;
    }
}
