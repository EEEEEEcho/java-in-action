package com.echo.ch06;

import com.echo.ch04.Dish;
import com.echo.ch04.MenuFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GroupByDemo {
    public static void main(String[] args) {
        List<Dish> menu = MenuFactory.getMenu();
        //将菜单中的菜按照类型分类
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);
        //按照菜肴的热量分组,传递一个Function
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(
                dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }
        ));
        System.out.println(dishesByCaloricLevel);
        //过滤,groupingBy的第二个参数传入一个过滤器，只取符合条件的
        Map<Dish.Type, List<Dish>> collectByFilter = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.filtering(
                        dish -> dish.getCalories() > 500, Collectors.toList()
                )));
        System.out.println(collectByFilter);

        //Collectors中的collect函数是要嵌套的
        HashMap<String, List<String>> dishTags = DishTagsFactory.getDishTags();
        Map<Dish.Type, Set<String>> dishNameByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.flatMapping(
                            dish -> dishTags.get(dish.getName()).stream(),
                            Collectors.toSet()
                        )
                ));
        System.out.println(dishNameByType);

        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                Collectors.groupingBy(Dish::getType,    //一级分组
                        Collectors.groupingBy(      //二级分组
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }
                        ))
        );
        System.out.println(dishesByTypeCaloricLevel);

        //按子组收集数据
        Map<Dish.Type, Long> typeCounts = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.counting())
        );
        System.out.println(typeCounts);
        //查找各类型的菜肴中热量最高的菜肴
        Map<Dish.Type, Optional<Dish>> maxCalories = menu.stream().collect(
                Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
        );
        System.out.println(maxCalories);


        Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(
                Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        ))
        );
        System.out.println(mostCaloricByType);
    }
}
