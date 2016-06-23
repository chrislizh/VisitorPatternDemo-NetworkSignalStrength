package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.actualVisitors;

import com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern.IVisitor;

/**
 * Created by cli on 23/06/2016.
 */
public class DummyVisitor implements IVisitor<Object>{
    @Override
    public Integer visit(Object obj) {
        return null;
    }
}
