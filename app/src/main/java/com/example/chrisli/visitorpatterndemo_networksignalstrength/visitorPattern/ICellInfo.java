package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern;


/**
 * Created by cli on 16/05/2016.
 */
public interface ICellInfo {
    Integer getSignalStrength(ICellInfoVisitor iCellInfoVisitor);
}
