package com.echo.ch09.factory;

public class ProductFactory {
    public static Product createProduct(String name){
        switch (name){
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default:throw new RuntimeException("No such product " + name);
        }
    }
}
class Loan implements Product{

}
class Stock implements Product{

}
class Bond implements Product{

}
interface Product{

}