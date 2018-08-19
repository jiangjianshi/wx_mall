package com.wx.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.wx.mall.entity.dto.OrderDto;
import com.wx.mall.entity.dto.OrderStatusCount;
import com.wx.mall.entity.dto.UserOrderDto;
import com.wx.mall.entity.model.Orders;
import com.wx.mall.mapper.OrdersMapper;
import com.wx.mall.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public OrderStatusCount statisticsOrders(Integer uid) {

        List<Orders> list = ordersMapper.selectByUidAndStatus(uid, null);
        Map<Integer, Long> groupMap = list.stream().collect(
                Collectors.groupingBy(x -> x.getStatus(), Collectors.counting()));

        OrderStatusCount statusCount = new OrderStatusCount();
        statusCount.setNoPay(groupMap.get(0) == null ? 0 : groupMap.get(0));
        statusCount.setNoSend(groupMap.get(1) == null ? 0 : groupMap.get(1));
        statusCount.setNoReceive(groupMap.get(2) == null ? 0 : groupMap.get(2));
        statusCount.setNoComment(groupMap.get(3) == null ? 0 : groupMap.get(3));
        statusCount.setDone(groupMap.get(4) == null ? 0 : groupMap.get(4));

        return statusCount;
    }

    @Override
    public OrderDto calOrder(Integer uid, String goodsJsonStr, String remark) {

        OrderDto dto = new OrderDto();
        dto.setAmountLogistics(10);
        dto.setAmountTotle(100);
        dto.setIsNeedLogistics(1);
        dto.setScore(12);
        return dto;
    }


    @Override
    public OrderDto createOrder(Integer uid, String goodsJsonStr, String remark) {

        List<UserOrderDto> orders = JSON.parseArray(goodsJsonStr, UserOrderDto.class);
        return null;
    }

}
