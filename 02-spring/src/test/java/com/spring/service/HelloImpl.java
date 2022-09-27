package com.spring.service;

public class HelloImpl implements IHello{
    @Override
    public void doSayHello() {
        System.out.println("doSayHello");
    }
}
