package com.echo.ch13.demo2;

public interface Rotatable {
    void setRotationAngle(int angleInDegree);
    int getRotationAngle();
    default void rotateBy(int angleInDegrees){
        setRotationAngle((getRotationAngle() + angleInDegrees) % 360);
    }
}
