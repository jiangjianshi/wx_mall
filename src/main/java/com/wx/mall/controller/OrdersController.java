package com.wx.mall.controller;

import com.wx.mall.common.RespMsg;
import com.wx.mall.entity.dto.OrderStatusCount;
import com.wx.mall.entity.model.Orders;
import com.wx.mall.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by jiangjianshi on 18/8/5.
 */
@RestController
@RequestMapping("/orders/")
public class OrdersController extends BaseController {

    @Autowired
    private OrdersService ordersService;


    @RequestMapping("listOrders")
    public RespMsg<List<Orders>> listOrders(Integer uid, Integer status) {
        List<Orders> list = ordersService.listOrders(uid, status);
        return success("获取成功", list);
    }


    @RequestMapping("closeOrder")
    public RespMsg<Integer> closeOrder(Integer orderId) {
        int cnt = ordersService.closeOrder(orderId);
        if (cnt == 1) {
            return success("关闭成功", cnt);
        } else {
            return fail("操作失败");
        }
    }


    @RequestMapping("statistics")
    public RespMsg<OrderStatusCount> statistics(Integer uid) {
        OrderStatusCount statusCount = ordersService.statisticsOrders(uid);
        return success("获取成功", statusCount);
    }
}
