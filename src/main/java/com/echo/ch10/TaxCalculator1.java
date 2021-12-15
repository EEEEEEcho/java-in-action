package com.echo.ch10;

public class TaxCalculator1 {
    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public TaxCalculator1 withTaxRegional(){
        useRegional = true;
        return this;
    }

    public TaxCalculator1 withTaxGeneral(){
        useGeneral = true;
        return this;
    }

    public TaxCalculator1 withSurcharge(){
        useSurcharge = true;
        return this;
    }

    public double calculate(Order order){
        return calculate(order,useRegional,useGeneral,useSurcharge);
    }

    private static double calculate(Order order,boolean useRegional,
                                    boolean useGeneral,boolean useSurcharge){
        double value = order.getValue();
        if (useRegional) value = Tax.regional(value);
        if (useGeneral) value = Tax.general(value);
        if (useSurcharge) value = Tax.surcharge(value);
        return value;
    }
}
