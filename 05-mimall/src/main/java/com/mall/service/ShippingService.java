package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.pojo.dto.ShippingDTO;

import java.util.Map;

public interface ShippingService {
    Map<String, Integer> add(Integer uid, ShippingDTO form);
    void delete(Integer uid, Integer shippingId);
    void update(Integer uid, Integer shippingId, ShippingDTO form);
    PageInfo list(Integer uid, Integer pageNum, Integer pageSize);
}
