package com.jdk8;

import java.util.Locale;
import java.util.Optional;

/**
 * Optional是JDK1.8中引入的一个API对象，可以将此对象理解为一个容器对象，
 * 通过此对象可以更好的简化对null的判断，避免出现一下NPE(空指针异常)
 * 此对象的创建方式：
 * 1)empty();返回一个空的容器对象
 * 2)of();为一个非null值的对象创建一个Optional对象，传输参数为null，假如为null会抛出空指针异常
 * 3)ofNullable(T value) 创建一个可为空的Optional，如果为非空，则返回Optional描述的指定值，
 * 负责返回空的Optional对象
 */
public class OptionalTests {

    public static String toUpper01(String str){
        if(str!=null) {
            return str.toUpperCase();
        }
        return "parameter can not be null";
    }
    public static String toUpper02(String str){
        return Optional.ofNullable(str)
                .map(s->s.toUpperCase())
                .orElse("parameter can not be null");
    }
    public static void main(String[] args) {
        String s0="hello";
        String s1 = toUpper02(s0);
        System.out.println(s1);
        String abcd =
                Optional.of(s1).filter(str -> str.length() > 2).get();
        System.out.println(abcd);
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);
        System.out.println(empty.get());
    }
}
