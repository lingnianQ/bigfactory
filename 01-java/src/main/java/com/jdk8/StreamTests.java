package com.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream应用案例
 */
public class StreamTests {

    //Stream对象初识
    static void doTest01(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 8, 7, 6, 9, 10);
        long count = integers.stream() //创建stream对象
                .filter(i -> i % 2 == 0)//对数据进行过滤的中间操作
                .count();//统计个数的终止操作
        System.out.println(count);
    }

    //Stream对象的创建方式
    static void doTest02(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 8, 7, 6, 9, 10);
        Stream<Integer> s1 = integers.stream();
        Stream<Integer> s2 = integers.parallelStream();
        IntStream s3 = Arrays.stream(new int[]{1, 2, 3, 4, 5});
        Stream<Integer> s4 = Stream.of(1, 2, 3, 4);
        Stream<Integer> s5 = Stream.iterate(2, x -> x + 2);
        Stream<Double> s6 = Stream.generate(() -> Math.random());
    }
    //Stream对象的中间操作
    static void doTest03() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 8, 7,7, 6, 9, 10);
        integers.stream()
                .filter((item)->item%2!=0)//中间操作
                .skip(2)//中间操作，跳过两个
                .distinct()//去重操作
                .limit(3) //中间操作，限定只取两个
                .forEach(System.out::println);//终止操作
    }
    //Stream对象的中间操作
    static void doTest04() {
        Stream<String> stream = Arrays.stream(new String[]{"a", "abc", "ab", "abcd"});
        stream.sorted() //排序
              .map(x->x.toUpperCase())//转换为大写
              .forEach(System.out::println);
    }
    //终止操作match
    static void doTest05(){
        List<Integer> list=Arrays.asList(10,11,12,13,14,15);
        boolean allMatch = list.stream()
                .allMatch(x -> x % 2 == 0);
        boolean anyMatch = list.stream().anyMatch(x -> x % 2 == 0);
        boolean noneMatch = list.stream().noneMatch(x -> x > 50);
        System.out.println(allMatch);
        System.out.println(anyMatch);
        System.out.println(noneMatch);
    }

    //终止操作find操作
    static void doTest06(){
        List<Integer> list=Arrays.asList(19,10,11,12,13,14,15);
        Optional<Integer> first = list.stream().sorted().findFirst();
        System.out.println(first.get());

        Optional<Integer> any =
                list.parallelStream().sorted().findAny();
        System.out.println(any.get());
    }
    //count,max终止操作
    static void doTest07() {
        List<Integer> list = Arrays.asList(19, 10, 11, 12, 13, 14, 15);
        long count = list.stream().count();
        System.out.println(count);
        Optional<Integer> max = list.stream().max((x, y) -> {
            return x - y;
        });
        System.out.println(max.get());
    }
    //reduce(缩减)终止操作
    static void doTest08() {
        List<Integer> list = Arrays.asList(10, 20, 30,40);
        Integer reduce = list.stream().reduce(0, (x, y) -> {
            return x + y;
        });
        System.out.println(reduce);
    }
    //Collector终止操作
    static void doTest09() {
        List<Integer> list = Arrays.asList(10, 20, 30,40);
        List<Integer> collect =
                 list.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(collect);
    }



    public static void main(String[] args) {
       // doTest01();
      //  doTest03();
      //  doTest04();
       // doTest05();
       // doTest06();

        //doTest07();
       // doTest08();
        doTest09();
    }
}
