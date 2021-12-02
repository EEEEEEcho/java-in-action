package com.echo.ch08;

import java.util.Set;

public class SetFactoryDemo {
    public static void main(String[] args) {
        Set<String> friends = Set.of("Raphael", "Olivia", "Thibaut");
        System.out.println(friends);

        //Set<String> raphael = Set.of("Raphael", "Olivia", "Olivia"); exception

    }
}
