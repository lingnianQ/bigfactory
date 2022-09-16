package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.vo.OrderVo;

public interface OrderService {
    OrderVo create(Integer uid, Integer shippingId);
    PageInfo list(Integer uid, Integer pageNum, Integer pageSize);
    OrderVo detail(Integer uid, Long orderNo);
    void cancel(Integer uid, Long orderNo);
    void paid(Long orderNo);
}
