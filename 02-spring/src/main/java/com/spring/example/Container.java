package com.spring.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Lazy
@Scope("singleton")
@Component
public class Container {

    @PostConstruct
    public void doInit(){}

    @PreDestroy
    public void doDestory(){}
}
