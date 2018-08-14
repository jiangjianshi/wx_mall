package com.wx.mall.entity.dto;

import com.wx.mall.entity.model.Goods;
import com.wx.mall.entity.model.GoodsPics;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/14.
 */
@Data
@ToString
public class GoodsDetailDto {

    private List<GoodsPics> pics;
    List<PropertiesDto> properties;
    private String content;
    private Goods basicInfo;
}
