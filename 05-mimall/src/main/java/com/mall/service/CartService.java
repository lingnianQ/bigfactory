package com.mall.service;

import com.mall.pojo.Cart;
import com.mall.pojo.vo.CartVo;

import java.util.List;

public interface CartService {
    CartVo add(Integer uid, Integer productId, Boolean selected);
    CartVo list(Integer uid);
    CartVo update(Integer uid,
                  Integer productId,
                  Integer quantity,
                  Boolean selected);
    CartVo delete(Integer uid, Integer productId);
    CartVo selectAll(Integer uid);
    CartVo unSelectAll(Integer uid);
    Integer sum(Integer uid);
    public List<Cart> listForCart(Integer uid);
}
