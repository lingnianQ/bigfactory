package com.blog.aspect;

import com.blog.annotation.RequiredLog;
import com.blog.pojo.Log;
import com.blog.pojo.User;
import com.blog.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Aspect 注解描述的类为一个切面对象
 */
@Order(-1)
@Aspect
@Component
public class LogAspect {
    private static Logger logger= LoggerFactory.getLogger(LogAspect.class);
    /**
     * 切入点表达式，告诉底层表达式定义方法为功能增强的切入点方法。
     */
    @Pointcut("@annotation(com.blog.annotation.RequiredLog)")
    public void doLog(){}//此方法不写方法体具体内容，它只负责承载切入点表达式的定义

    /**
     *  @Around 描述方法是一个功能增强方法，我们可以在此方法中
     *  通过连接点对象调用目标执行链，在目标执行链执行之前或之后
     *  都可以添加拓展业务逻辑。
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    //@Around("@annotation(com.spring.annotation.RequiredLog)")
    @Around("doLog()")
    public Object doLogAround(ProceedingJoinPoint joinPoint)throws Throwable{
        long time=0;
        Integer status=1;//ok
        String error="";
        long t1=System.currentTimeMillis();
        //调用目标执行链(这个执行链中可以包含当前类中的其它通知方法，其它切面方法，目标方法)
        try {
            Object result = joinPoint.proceed();
            long t2 = System.currentTimeMillis();
            time=t2-t1;
            return result;
        }catch(Throwable e){
            e.printStackTrace();
            long t3 = System.currentTimeMillis();
            time=t3-t1;
            status=0;
            error=e.getMessage();
            throw e;
        }finally {
            saveUserLog(joinPoint,time,status,error);
        }
    }
    //记录用户行为日志
    private void saveUserLog(ProceedingJoinPoint joinPoint,long time,int status,String error) throws NoSuchMethodException, JsonProcessingException {
        //1.获取用户行为日志
        //1.1获取谁在执行操作(用户ip地址和用户名)
        ServletRequestAttributes requestAttributes=
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        String ip=request.getRemoteAddr();//通过请求对象去获取
        User user=(User)request.getSession().getAttribute("loginUser");
        String username="";
        if(user!=null){
            username=user.getUsername();
        }
        //1.2获取用户执行的操作(RequiredLog注解中operation属性的值)
        Class<?> targetCls = joinPoint.getTarget().getClass();
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
        Method targetMethod =
                targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
        RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
        String operation=requiredLog.operation();
        //1.3获取方法信息
        String method=targetCls.getName()+"."+targetMethod.getName();
        //1.4获取方法实际参数
        String params=new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        //2.封装用户行为日志
        Log userLog=new Log();
        userLog.setIp(ip);
        userLog.setUsername(username);
        userLog.setCreatedTime(new Date());
        userLog.setOperation(operation);
        userLog.setMethod(method);
        userLog.setParams(params);
        userLog.setTime(time);
        userLog.setStatus(status);
        userLog.setError(error);
        //3.记录用户行为日志
        logger.info("user log {}",userLog);
        System.out.println(Thread.currentThread().getName()+"-->logAspect.insert");
        logService.insert(userLog);
    }
    @Autowired
    private LogService logService;

}
