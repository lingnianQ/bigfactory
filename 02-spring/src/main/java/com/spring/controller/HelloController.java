package com.spring.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Controller注解描述的类为Spring MVC中的一个处理器对象(Handler)，
 * 此对象会交给spring管理(创建对象，初始化对象，存储对象，应用对象、销毁对象)
 */

//@Scope("prototype")//多例对象，每次请求都会创建新对象，适合应用频度比较低的对象。
@Scope("singleton") //默认，单例作用域对象，相同名字、相同类型的对象会在容器中只有一份。
@Lazy //延迟加载(实例化)，配合singleton作用域使用。
@Controller
public class HelloController {
    public HelloController(){
        System.out.println("HelloContorller(){}");
    }

    /**
     * @RequestMapping描述方法为@Controller注解描述的类中的请求处理方法，
     * 基于这个方法处理客户端请求。
     * 通过此注解可以定义请求url、请求处理方式等
     * @return
     * http://localhost:8080/hello
     */
    //@RequestMapping(value="/hello",method= RequestMethod.GET)
    @RequestMapping("/hello")
    public String doHelloUI(Model model) {
        model.addAttribute("msg", "Hello Thymeleaf");
        return "hello";//当方法或类上没有@ResponseBody注解描述时，这里的字符串是一个view，
    }//prefix+hello+suffix=/templates/hello.html

    /**
     * @ResponseBody 注解可以描述类和方法，描述类时表示所有方法上都添加此注解。
     * 其含义是告诉底层系统此方法返回值不是view，会将返回值写入到响应体，再响应
     * 到客户端。假如方法的返回值可以转换为json,系统底层还会先将其转成json，再进行
     * 响应。
     * @return
     */

    @ResponseBody
    @RequestMapping("/doSayHello")
    public String doSayHello() {
        return "hello spring mvc";
    }



    /**初始化方法：对象构建后初始化时执行，构造方法之后执行*/
    @PostConstruct
    public void init(){
        System.out.println("init(){}");
    }
    /**销毁方法：对象销毁之前可以执行，可以在这里释放一些资源*/
    @PreDestroy
    public void destroy(){
        System.out.println("destroy(){}");
    }

}
