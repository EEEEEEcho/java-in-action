package com.echo.ch09.responsble;

public class SpellCheckerProcessing extends ProcessingObject<String>{
    @Override
    public String handleWork(String input) {
        return input.replace("labda","lambda");
    }
}
