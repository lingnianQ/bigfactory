package com.mall.pojo.vo;

import lombok.Data;

/**
 * 定义响应标准对象，通过此对象封装服务端响应到客户端的数据
 */
@Data
public class JsonResult {

    /**响应状态(例如1表示ok，0表示error)*/
    private Integer status=1;
    /**响应消息(例如update ok,delete ok)*/
    private String message="ok";
    /**具体的业务数据(例如一个查询结果)*/
    private Object data;

    public JsonResult(){}
    public JsonResult(String message){
        this.message=message;
    }
    public JsonResult(Object data){
        this.data=data;
    }
    public JsonResult(Throwable e){
        this.status=0;
        this.message=e.getMessage();
    }



    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
