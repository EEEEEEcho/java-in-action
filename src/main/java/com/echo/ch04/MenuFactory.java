package com.echo.ch04;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {
    private static final List<Dish> menu = new ArrayList<>();
    private static final List<Dish> specialMenu = new ArrayList<>();
    static {
        menu.add(new Dish("pork",false,800, Dish.Type.MEAT));
        menu.add(new Dish("beef",false,700, Dish.Type.MEAT));
        menu.add(new Dish("chicken",false,400, Dish.Type.MEAT));
        menu.add(new Dish("french fries",true,530, Dish.Type.OTHER));
        menu.add(new Dish("rice",true,350, Dish.Type.OTHER));
        menu.add(new Dish("season fruit",true,120, Dish.Type.OTHER));
        menu.add(new Dish("pizza",true,550, Dish.Type.MEAT));
        menu.add(new Dish("prawns",false,300, Dish.Type.FISH));
        menu.add(new Dish("salmon",false,450, Dish.Type.FISH));
    }
    static {
        specialMenu.add(new Dish("season fruit",true,120, Dish.Type.OTHER));
        specialMenu.add(new Dish("prawns",false,300, Dish.Type.FISH));
        specialMenu.add(new Dish("rice",true,350, Dish.Type.OTHER));
        specialMenu.add(new Dish("chicken",false,400, Dish.Type.MEAT));
        specialMenu.add(new Dish("french fries",true,530, Dish.Type.OTHER));
    }

    public static List<Dish> getMenu(){
        return menu;
    }

    public static List<Dish> getSpecialMenu(){
        return specialMenu;
    }
}
