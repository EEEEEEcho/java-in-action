package com.echo.ch05;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Chapter5Exercise {
    public static void main(String[] args) {
        List<Transaction> transactions = TransactionFactory.getTransactions();
        //找出2011年发生的所有交易，并按交易额排序
        List<Transaction> collect1 = transactions.stream().filter(t1 -> t1.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect1);
        //交易员都在哪些不同的城市工作过
        List<String> collect2 = transactions.stream()
                .map(t2 -> t2.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect2);
        //查找所有来自于剑桥的交易员，并按名字排序
        List<Trader> collect3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect3);
        //返回所有交易员的姓名字符串，按字母顺序排序
        String collect4 = transactions.stream()
                .map(t4 -> t4.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (s1, s2) -> s1 + s2);
        System.out.println(collect4);
        //有没有交易员是在米兰工作的
        boolean milan = transactions.stream()
                .anyMatch(t5 -> t5.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
        //打印生活在剑桥的交易员的所有交易额
        List<Integer> collect6 = transactions.stream()
                .filter(t6 -> t6.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println(collect6);
        //所有交易中，最高的交易额是多少
        Integer max = transactions.stream()
                .max(Comparator.comparingInt(Transaction::getValue)).map(Transaction::getValue).get();
        System.out.println(max);
        //交易额最小的交易
        Transaction min = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue)).get();
        System.out.println(min);
    }
}
