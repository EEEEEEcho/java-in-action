package com.echo.ch13.demo2;

public interface Resizeable {
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width,int height);
    default void setRelativeSize(int wFactor,int hFactor){
        setAbsoluteSize(getWidth() / wFactor,getHeight() / hFactor);
    }
}
