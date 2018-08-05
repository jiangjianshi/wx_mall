package com.wx.mall.service;

import com.wx.mall.entity.dto.OrderStatusCount;
import com.wx.mall.entity.model.Orders;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangjianshi on 18/8/5.
 */
public interface OrdersService {


    List<Orders> listOrders(Integer uid, Integer status);

    int closeOrder(Integer orderId);

    OrderStatusCount statisticsOrders(Integer uid);
}
