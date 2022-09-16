package com.mall.controller;
import com.github.pagehelper.PageInfo;
import com.mall.pojo.vo.JsonResult;
import com.mall.pojo.vo.ProductDetailVo;
import com.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public JsonResult list(@RequestParam(required = false) Integer categoryId,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return new JsonResult(productService.list(categoryId, pageNum, pageSize));
    }

    @GetMapping("/products/{productId}")
    public JsonResult detail(@PathVariable Integer productId) {
        return new JsonResult(productService.detail(productId));
    }

}
