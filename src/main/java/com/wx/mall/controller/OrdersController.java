package com.wx.mall.controller;

import com.wx.mall.common.RespMsg;
import com.wx.mall.entity.dto.OrderDto;
import com.wx.mall.entity.dto.OrderStatusCount;
import com.wx.mall.entity.model.Orders;
import com.wx.mall.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/5.
 */
@Slf4j
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


    @RequestMapping("createOrder")
    public RespMsg<OrderDto> createOrder(Integer uid, String goodsJsonStr, String remark, String calculate) {

        try {
            if ("calculate".equals(calculate)) {
                OrderDto dto = ordersService.calOrder(uid, goodsJsonStr, remark);
                return success("下单成功", dto);
            } else {
                OrderDto dto = ordersService.createOrder(uid, goodsJsonStr, remark);
                return success("下单成功", dto);
            }


        } catch (Exception e) {
            log.error("下单失败", e);
            return fail("下单失败");
        }
    }


}
