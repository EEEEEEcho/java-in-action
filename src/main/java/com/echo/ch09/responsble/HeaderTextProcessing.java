package com.echo.ch09.responsble;

public class HeaderTextProcessing extends ProcessingObject<String>{
    @Override
    public String handleWork(String text) {
        return "From Raoul,Mario and Alan: " + text;
    }
}
