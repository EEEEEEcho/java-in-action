package com.echo.ch11;

import java.util.*;
import java.util.stream.Collectors;

public class TestDemo {
    public static void main(String[] args) {

    }

    public static void demo1(Insurance insurance){
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.flatMap(Insurance::getName);
    }

    public static String getCarInsuranceName(Optional<Person> person){
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .flatMap(Insurance::getName)
                .orElse("Unknown");
    }

    public static Set<String> getCarInsuranceNames(List<Person> persons){
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.flatMap(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }

    public static void action(){
        Map<String,Object> map = new HashMap<>();
        //用Opitional封装可能为null的值
        Optional<Object> value = Optional.ofNullable(map.get("key"));
    }

    public static Optional<Integer> stringToInt(String s){
        //异常与Optional的对比，在捕获异常之后，使用Optional处理。
        try{
            return Optional.of(Integer.parseInt(s));
        }
        catch (NumberFormatException e){
            return Optional.empty();
        }
    }
}
