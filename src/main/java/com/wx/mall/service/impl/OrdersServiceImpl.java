package com.wx.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.wx.mall.entity.dto.OrderDto;
import com.wx.mall.entity.dto.OrderStatusCount;
import com.wx.mall.entity.dto.UserOrderDto;
import com.wx.mall.entity.model.OrderGoodsRelation;
import com.wx.mall.entity.model.Orders;
import com.wx.mall.mapper.OrderGoodsRelationMapper;
import com.wx.mall.mapper.OrdersMapper;
import com.wx.mall.service.OrdersService;
import com.wx.mall.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by jiangjianshi on 18/8/5.
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderGoodsRelationMapper orderGoodsRelationMapper;


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
        dto.setAmountLogistics(12);
        dto.setAmountTotle(100);
        dto.setIsNeedLogistics(1);
        dto.setScore(12);
        return dto;
    }


    @Override
    public Orders createOrder(Integer uid, String goodsJsonStr, String remark, Integer addressId) {

        Orders order = new Orders();
        order.setUid(uid);
        order.setAddressId(addressId);
        order.setOrderCode(DateUtil.formatDateTime(new Date()) + new Random().nextInt(10000));
        order.setRealAmount(new BigDecimal("99"));
        order.setStatus(0);
        order.setRemark(remark);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int cnt = ordersMapper.insert(order);

        List<UserOrderDto> orders = JSON.parseArray(goodsJsonStr, UserOrderDto.class);
        for(UserOrderDto orderDto : orders){

            OrderGoodsRelation odr = new OrderGoodsRelation();
            odr.setGoodsId(orderDto.getGoodsId());
            odr.setOrderId(order.getId());
            odr.setNumber(orderDto.getNumber());
            odr.setGoodsProperties(orderDto.getPropertyChildIds());
            odr.setLogisticsType(orderDto.getLogisticsType());
            odr.setInviterId(orderDto.getInviter_id());
            odr.setStatus(1);
            orderGoodsRelationMapper.insert(odr);
        }

        return order;
    }

}
