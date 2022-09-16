package com.spring.pojo.vo;

/**
 * 设计一个对象，封装响应到客户端的数据
 */
public class JsonResult {
    /**状态码*/
    private Integer state=1;//1 表示OK,0表示Error
    /**状态码信息*/
    private String message="ok";
    /**封装正确的查询结果*/
    private Object data;

    public JsonResult(){}
    public JsonResult(String message){
        this.message=message;
    }
    public JsonResult(Object data){
        this.data=data;
    }
    public JsonResult(Throwable e){
        this.state=0;
        this.message=e.getMessage();
    }
    public JsonResult(int state,String message){
        this.state=state;
        this.message=message;
    }
    //...

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
