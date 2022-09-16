package com.mall.controller;

import com.mall.pojo.User;
import com.mall.pojo.dto.CartAddDTO;
import com.mall.pojo.dto.CartUpdateDTO;
import com.mall.pojo.vo.CartVo;
import com.mall.pojo.vo.JsonResult;
import com.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/carts")
    public JsonResult list(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(cartService.list(user.getId()));
    }

    @PostMapping("/carts")
    public JsonResult add(@RequestBody CartAddDTO cartAddDTO,
                          HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(cartService.add(user.getId(), cartAddDTO.getProductId(),cartAddDTO.getSelected()));
    }

    @PutMapping("/carts/{productId}")
    public JsonResult update(@PathVariable Integer productId,
                             @RequestBody CartUpdateDTO cartUpdateDTO,
                                     HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(cartService.update(user.getId(), productId,cartUpdateDTO.getQuantity(),cartUpdateDTO.getSelected()));
    }

    @DeleteMapping("/carts/{productId}")
    public JsonResult delete(@PathVariable Integer productId,
                       HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(cartService.delete(user.getId(), productId));
    }

    @PutMapping("/carts/selectAll")
    public JsonResult selectAll(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(cartService.selectAll(user.getId()));
    }

    @PutMapping("/carts/unSelectAll")
    public JsonResult unSelectAll(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(cartService.unSelectAll(user.getId()));
    }

    @GetMapping("/carts/products/sum")
    public JsonResult sum(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new JsonResult(cartService.sum(user.getId()));
    }
}
