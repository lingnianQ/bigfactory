package com.mall.controller;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.User;
import com.mall.pojo.vo.JsonResult;
import com.mall.pojo.vo.OrderVo;
import com.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders/{shippingId}")
    public JsonResult create(@PathVariable Integer shippingId,
                             HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        OrderVo orderVo=orderService.create(user.getId(),shippingId);
        return new JsonResult(orderVo);
    }

    @GetMapping("/orders")
    public JsonResult list(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        PageInfo pageInfo = orderService.list(user.getId(), pageNum, pageSize);
        return new JsonResult(pageInfo);
    }

    @GetMapping("/orders/{orderNo}")
    public JsonResult detail(@PathVariable Long orderNo,
                       HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(orderService.detail(user.getId(), orderNo));
    }

    @PutMapping("/orders/{orderNo}")
    public JsonResult cancel(@PathVariable Long orderNo,
                             HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        orderService.cancel(user.getId(), orderNo);
        return new JsonResult("订单取消成功");
    }
}
