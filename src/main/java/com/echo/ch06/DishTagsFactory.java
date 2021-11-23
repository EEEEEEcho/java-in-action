package com.echo.ch06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DishTagsFactory {
    private static HashMap<String, List<String>> dishTags;

    public static HashMap<String,List<String>> getDishTags(){
        if (dishTags == null){
            dishTags = new HashMap<>();
            dishTags.put("pork", Arrays.asList("greasy","salty"));
            dishTags.put("beef", Arrays.asList("salty","roasted"));
            dishTags.put("chicken", Arrays.asList("fried","crisp"));
            dishTags.put("french fries", Arrays.asList("greasy","fried"));
            dishTags.put("rice", Arrays.asList("light","natural"));
            dishTags.put("season fruit", Arrays.asList("fresh","natural"));
            dishTags.put("pizza", Arrays.asList("tasty","salty"));
            dishTags.put("prawns", Arrays.asList("tasty","roasted"));
            dishTags.put("salmon", Arrays.asList("delicious","fresh"));
        }
        return dishTags;
    }
}
