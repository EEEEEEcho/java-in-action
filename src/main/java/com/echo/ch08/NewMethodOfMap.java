package com.echo.ch08;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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


        HashMap<String,ArrayList<String>> friendsToMovies = new HashMap<>();
        //笨办法
        String friend = "Raphael";
        ArrayList<String> movies = friendsToMovies.get(friend);
        if (movies == null){
            movies = new ArrayList<>();
            friendsToMovies.put(friend,movies);
        }
        movies.add("Start Wars");
        System.out.println(friendsToMovies);
        //计算模式
        friendsToMovies.computeIfAbsent("Raphael",name -> new ArrayList<>()).add("Star Wars");

        //笨办法的删除
        String key = " Raphael";
        String value = "Jack Reacher 2";
        HashMap<String,String> favoriteMovies = new HashMap<>();
        if(favoriteMovies.containsKey(key) &&
                Objects.equals(favoriteMovies.get(key),value)){
            favoriteMovies.remove(key);
        }
        //删除模式
        favoriteMovies.remove(key,value);

        //替换模式
        favoriteMovies.put("Raphael","Star Wars");
        favoriteMovies.put("Olivia","James Bond");
        favoriteMovies.replaceAll((myFriend,movie) -> movie.toUpperCase(Locale.ROOT));
        System.out.println(favoriteMovies);
        //合并map
        //第一个map
        Map<String, String> family = Map.ofEntries(Map.entry("Teo", "Start Wars"), Map.entry("Cristina", "James Bond"));
        //第二个map
        Map<String, String> frineds = Map.ofEntries(Map.entry("Raphael", "Star Wars"),Map.entry("Cristina", "Matrix"));
        HashMap<String, String> everyOne = new HashMap<>(family);
        //这样会使重复的键以最新的值为主
        //everyOne.putAll(frineds);
        //System.out.println(everyOne);
        //使用merge处理重复的键
        //连接键重复的两部电影名
        frineds.forEach((k,v) -> everyOne.merge(k,v,(movie1,movie2) -> movie1 + " & " + movie2));
        System.out.println(everyOne);

        //很常用的一个方法，用来执行初始化检查
        Map<String,Long> moviesToCount = new HashMap<>();
        String movieName = "James Bound";
        Long count = moviesToCount.get(movieName);
        if (count == null){
            moviesToCount.put(movieName,1L);
        }
        else{
            moviesToCount.put(movieName,count + 1);
        }

        //新方法 ， 如果不存在就设置为1，否则在原来的基础上+1
        moviesToCount.merge(movieName,1L,(k,c) -> c + 1L);
        System.out.println(moviesToCount);
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
