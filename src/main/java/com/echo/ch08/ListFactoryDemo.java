package com.echo.ch08;

import java.util.List;

public class ListFactoryDemo {
    public static void main(String[] args) {
        //这个列表是只读的，只能读取元素，不能增删改元素
        List<String> friends = List.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friends);
        //friends.add("Echo"); exception

    }
}
