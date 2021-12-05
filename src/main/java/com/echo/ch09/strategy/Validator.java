package com.echo.ch09.strategy;

public class Validator {
    private final ValidationStrategy strategy;
    public Validator(ValidationStrategy strategy){
        this.strategy = strategy;
    }
    public boolean validate(String s){
        return strategy.execute(s);
    }

    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b1);
        System.out.println(b2);

        //lambda改进
        Validator validator1 = new Validator((String s) -> s.matches("[a-z]+"));
        Validator validator2 = new Validator((String s) -> s.matches("/d+"));

    }
}
