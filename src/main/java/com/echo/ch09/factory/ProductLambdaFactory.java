package com.echo.ch09.factory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 很不戳
 */
public class ProductLambdaFactory {
    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan",Loan::new);
        map.put("stock",Stock::new);
        map.put("bond",Bond::new);
    }
    public static Product createProduct(String name){
        Supplier<Product> supplier = map.get(name);
        if (supplier != null) return supplier.get();
        throw new IllegalArgumentException("No such product " + name);
    }

    public static void main(String[] args) {
        //Comparator.comparing().thenComparing()
    }
}
