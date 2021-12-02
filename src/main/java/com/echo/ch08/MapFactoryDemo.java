package com.echo.ch08;

import java.util.Map;

public class MapFactoryDemo {
    public static void main(String[] args) {
        //创建小型map
        Map<String, Integer> friends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);
        System.out.println(friends);

        //创建大型map
        Map<String, Integer> ageOfFriends = Map.ofEntries(
                Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26));
        System.out.println(ageOfFriends);
    }
}
