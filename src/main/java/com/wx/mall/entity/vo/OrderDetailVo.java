package com.wx.mall.entity.vo;

import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.model.Logistics;
import com.wx.mall.entity.model.Orders;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/19.
 */
@Data
@ToString
public class OrderDetailVo {

    private Orders orderInfo;
    private Logistics logistics;
    private List<GoodsDto> goods;

}
