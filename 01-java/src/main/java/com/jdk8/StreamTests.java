package com.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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

    public static void main(String[] args) {
       // doTest01();
      //  doTest03();
        doTest04();
    }
}
