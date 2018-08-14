package com.wx.mall.entity.dto;

import com.wx.mall.entity.model.GoodsProperty;
import com.wx.mall.entity.model.GoodsPropertyType;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/14.
 */
@Data
@ToString
public class PropertiesDto extends GoodsPropertyType{

    private List<GoodsProperty> childsCurGoods;
}
