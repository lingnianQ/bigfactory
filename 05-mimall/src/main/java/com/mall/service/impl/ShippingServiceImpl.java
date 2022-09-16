package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.dao.ShippingMapper;
import com.mall.pojo.Shipping;
import com.mall.pojo.dto.ShippingDTO;
import com.mall.service.ShippingService;
import com.mall.service.exp.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public Map<String, Integer> add(Integer uid, ShippingDTO form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        int row = shippingMapper.insertSelective(shipping);
        if (row == 0)
            throw new ServiceException("添加失败");
        Map<String, Integer> map = new HashMap<>();
        map.put("shippingId", shipping.getId());

        return map;
    }

    @Override
    public void delete(Integer uid, Integer shippingId) {
        int row = shippingMapper.deleteByIdAndUid(uid, shippingId);
        if (row == 0)
           throw new ServiceException("删除失败");

    }

    @Override
    public void update(Integer uid, Integer shippingId, ShippingDTO form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        shipping.setId(shippingId);
        int row = shippingMapper.updateByPrimaryKeySelective(shipping);
        if (row == 0)
            throw new ServiceException("更新失败");
    }

    @Override
    public PageInfo list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippings = shippingMapper.selectByUid(uid);
        PageInfo pageInfo = new PageInfo(shippings);
        return pageInfo;
    }
}
