package com.wx.mall.service;

import com.wx.mall.entity.dto.OrderDto;
import com.wx.mall.entity.dto.OrderStatusCount;
import com.wx.mall.entity.model.Orders;
import com.wx.mall.entity.vo.OrderListVo;

/**
 * Created by jiangjianshi on 18/8/5.
 */
public interface OrdersService {


    OrderListVo listOrders(Integer uid, Integer status);

    int closeOrder(Integer orderId);

    OrderStatusCount statisticsOrders(Integer uid);

    OrderDto calOrder(Integer uid, String goodsJsonStr, String remark);

    Orders createOrder(Integer uid, String goodsJsonStr, String remark, Integer addressId);


}
