package com.spring;

import com.spring.service.HelloImpl;
import com.spring.service.IHello;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SpringBootTest
public class JdkProxyTests {
    @Test
    void testJdkProxy(){
        //1.目标对象
        IHello h1=new HelloImpl();
        //2.创建JDK代理对象
        IHello proxy=(IHello)Proxy.newProxyInstance(
                h1.getClass().getClassLoader(),
                h1.getClass().getInterfaces(),
                new InvocationHandler(){//业务处理器(当调用代理对象的方法时会执行这个对象的invoke方法)
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    //1)扩展业务
                    System.out.println("start....");
                    //2)调用目标对象方法
                    Object obj= method.invoke(h1,args);
                    //3)扩展业务
                    System.out.println("end....");
                    return obj;
                }
        });
        //3.调用方法
        proxy.doSayHello();
    }
}
