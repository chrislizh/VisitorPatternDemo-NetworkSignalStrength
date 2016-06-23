package com.example.chrisli.visitorpatterndemo_networksignalstrength.visitorPattern;

/**
 * Created by cli on 23/06/2016.
 */
public interface IVisitor<T> {
    Integer visit(T obj);
}

