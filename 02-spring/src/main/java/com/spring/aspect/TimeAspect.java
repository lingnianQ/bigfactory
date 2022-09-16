package com.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Aspect此注解描述的类为一个切面对象，此对象中可以定义：
 * 1)切入点表达式(@Pointcut)
 * 2)通知方法(@Around,@Before,@After,@AfterReturning,@AfterThrowing)
 * 2.1)@Before 此注解描述的方法在目标方法执行之前执行
 * 2.2)@After 在@AfterReturning或@AfterThrowing执行之后执行，无论是否出现异常都会执行
 * 2.3)@AfterReturning 此注解描述的方法在目标方法正确结束之后执行。
 * 2.4)@AfterThrowing 此注解描述的方法在目标方法异常结束之后执行
 *
 */
@Order(1)
@Aspect
@Component //此注解描述的类为spring中一般组件对象
public class TimeAspect {
        //基于execution方式定义细粒度的切入点表达式
       //@Pointcut("execution(* com.spring.controller.ArticleController.*(Long,..))")
       //基于注解方式定义细粒度的切入点表达式(可以精确到具体方法)
      @Pointcut("@annotation(com.spring.annotation.RequiredTime)")
      //基于bean名称定义粗粒度切入点表达式(不能精确到具体方法)，指定bean名对应的类中所有方法
      //@Pointcut("bean(articleController)")
      //基于within方式定义粗粒度的切入点表达式，指定包中的，所有类内部的所有方法作为切入点方法
      //@Pointcut("within(com.spring.controller.*)")//不包括子包
      //@Pointcut("within(com.spring.controller..*)")//包括子包类中的方法
      public void doTime(){}

      @Before("doTime()")
      public void doBefore(JoinPoint jp){
          System.out.println("@Before");
      }
      @After("doTime()")
      public void doAfter(JoinPoint jp){
          System.out.println("@After");
      }
      @AfterReturning("doTime()")
      public void doAfterReturning(JoinPoint jp){
          System.out.println("@AfterReturning");
      }
      @AfterThrowing("doTime()")
      public void doAfterThrowing(JoinPoint jp){
          System.out.println("@AfterThrowing");
      }
    /**
     * @param joinPoint 只能写在@Around通知中，它的作用可以获取目标方法信息、调用目标方法
     * @return
     * @throws Throwable
     */
      @Around("doTime()")
      public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{
          System.out.println("@Around.Before");
          try {
              Object proceed = joinPoint.proceed();
              System.out.println("@Around.AfterReturning");
              return proceed;
          }catch (Throwable e){
              System.out.println("@Around.AfterThrowing");
              throw e;
          }finally{
              System.out.println("@Around.After");
          }
      }

}
