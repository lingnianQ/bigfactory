package com.spring.controller;

import com.spring.pojo.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice 注解描述的类为spring web模块的全局异常处理对象。
 * 在此对象中我们可以定义多个异常处理方法，每个异常处理方法都要使用@ExceptionHandler进行描述
 */
//@ResponseBody
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * @ExceptionHandler 注解描述的方法为异常处理方法，此注解中
     * 定义的异常类型，为方法可以处理的异常类型，方法中异常类型
     * 应与@ExceptionHandler注解中定义的异常类型相同，或者是其
     * @ExceptionHandler注解中定义的异常类型的父类类型。
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult doHandleRuntimeException(RuntimeException e){
        log.error("exception {}",e.getMessage());
        return new JsonResult(e);
    }

}
