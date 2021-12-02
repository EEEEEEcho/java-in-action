package com.echo.ch08;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class NewMethodOfListAndSet {
    public static void main(String[] args) {
        //RemoveIf
        ArrayList<String> list = new ArrayList<>();
        list.add("world");
        list.add("world");
        list.add("string");

//        for(String str: list){
//            if ("world".equals(str)){
//                list.remove(str);
//            }
//        }
        //上述代码会出现ConcurrentModificationException
        //原因：这段代码中集合由两个不同的对象管理
        //Iterator对象管理着对对象的迭代
        //Collection对象管理者对对象的删除
        //Collection对象删除掉某元素之后，可能会导致和迭代器对象状态不一致，然后抛出异常
        System.out.println(list);

        //改进1：都使用Iterator对象
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals("world")){
                iterator.remove();
            }
        }
        System.out.println(list);
        //重置
        list = new ArrayList<>();
        list.add("world");
        list.add("world");
        list.add("string");

        //改进2：使用removeIf
        list.removeIf(str -> "world".equals(str));
        System.out.println(list);

        //replaceAll
        //重置
        list = new ArrayList<>();
        list.add("a12");
        list.add("b13");
        list.add("c14");

        //这会生成一个新的字符串集合
        list.stream()
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        //使用迭代器改变现有集合
        ListIterator<String> iterator1 = list.listIterator();
        while (iterator1.hasNext()){
            String code = iterator1.next();
            iterator1.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }
        System.out.println(list);

        list = new ArrayList<>();
        list.add("a12");
        list.add("b13");
        list.add("c14");

        list.replaceAll(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1));
        System.out.println(list);
    }
}
