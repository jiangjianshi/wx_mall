package com.wx.mall.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by jiangjianshi on 18/8/18.
 */

@Data
@ToString
public class UserOrderDto {

    private Integer goodsId;
    private Integer number;
    private String propertyChildIds;
    private Integer logisticsType;
    private Integer inviter_id;
}
