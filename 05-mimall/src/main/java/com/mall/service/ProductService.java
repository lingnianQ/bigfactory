package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.vo.ProductDetailVo;

public interface ProductService {

    PageInfo list(Integer categoryId,Integer pageNum,Integer pageSize);

    ProductDetailVo detail(Integer productId);
}
