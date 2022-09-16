package com.mall.controller;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.User;
import com.mall.pojo.dto.ShippingDTO;
import com.mall.pojo.vo.JsonResult;
import com.mall.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    @PostMapping("/shippings")
    public JsonResult add(@Valid @RequestBody ShippingDTO form,
                          HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(shippingService.add(user.getId(), form));
    }

    @DeleteMapping("/shippings/{shippingId}")
    public JsonResult delete(@PathVariable Integer shippingId,
                             HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        shippingService.delete(user.getId(), shippingId);
        return new JsonResult("delete ok");
    }

    @PutMapping("/shippings/{shippingId}")
    public JsonResult update(@PathVariable Integer shippingId,
                             @Valid @RequestBody ShippingDTO form,
                             HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        shippingService.update(user.getId(), shippingId, form);
        return new JsonResult("update ok");
    }

    @GetMapping("/shippings")
    public JsonResult list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                     HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        PageInfo pageInfo = shippingService.list(user.getId(), pageNum, pageSize);
        return new JsonResult(pageInfo);
    }
}
