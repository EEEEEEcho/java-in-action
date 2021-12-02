package com.echo.ch08;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewMethodOfMap {
    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("Echo",27);
        map.put("Park",24);
        map.put("Joker",25);
        map.forEach((friend,age) -> System.out.println(friend + " is " + age + " year's old"));
        //按照键排序
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
        //按照值排序
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(System.out::println);
        //如果键对应的值为空，则获取默认值
        System.out.println(map.getOrDefault("Shaco",23));
        //如果键对应的值不为空，但是也赋了一个默认值，会返回map中对应的值，否则这个方法有啥意义呢。。
        System.out.println(map.getOrDefault("Echo",32));

        //如果没有该键或者该键对应的值为空则计算
        ArrayList<String> lines = new ArrayList<>();
        lines.add("a");
        lines.add("b");
        lines.add("a");

        HashMap<String, byte[]> dataToHash = new HashMap<>();
        lines.forEach(line -> dataToHash.computeIfAbsent(line,NewMethodOfMap::calculateDigest));
        System.out.println(dataToHash);
    }
    public static byte[] calculateDigest(String key){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(key.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
