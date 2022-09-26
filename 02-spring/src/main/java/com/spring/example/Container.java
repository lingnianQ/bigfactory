package com.spring.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Lazy //定义singleton对象的延迟加载特性
@Scope("singleton") //用于定义bean作用域
@Component //用于定义bean组件
public class Container {
    /**
     * @PostConstruct 注解描述的方法为生命周期初始化方法
     */
    @PostConstruct
    public void doInit(){}
    /**
     * @PreDestroy 注解描述的方法为生命周期销毁方法,单例对象销毁之前执行.
     */
    @PreDestroy
    public void doDestory(){}
}
