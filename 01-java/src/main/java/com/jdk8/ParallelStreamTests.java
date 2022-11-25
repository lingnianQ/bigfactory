package com.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamTests {
    public static void main(String[] args) {
        List<String> asList = Arrays.asList("A", "B", "C", "D", "E");
        //doPrint(asList.stream().sequential());
        //doPrint(asList.stream().parallel());
        doPrint(asList.stream().parallel());
    }
    public static void doPrint (Stream<String> stream) {
        stream.forEach(s -> {
            System.out.println(
                    Thread.currentThread().getName()+"->"+s);
        });
    }
}
