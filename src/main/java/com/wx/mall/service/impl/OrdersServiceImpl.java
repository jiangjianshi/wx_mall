package com.wx.mall.service.impl;

import com.wx.mall.entity.model.Orders;
import com.wx.mall.mapper.OrdersMapper;
import com.wx.mall.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/5.
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public List<Orders> listOrders(Integer uid, Integer status) {

        return ordersMapper.selectByUidAndStatus(uid, status);
    }

    @Override
    public int closeOrder(Integer orderId) {

        Orders order = new Orders();
        order.setId(orderId);
        order.setStatus(-1);
        return ordersMapper.updateSelective(order);
    }
}
