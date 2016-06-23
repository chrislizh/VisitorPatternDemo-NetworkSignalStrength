package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern;

import android.telephony.CellInfo;

/**
 * Created by cli on 23/06/2016.
 */
public interface IPolymorphicVisitor {
    <T> IVisitor<T> getActualVisitor(Class<? extends CellInfo> elementClass);
}
