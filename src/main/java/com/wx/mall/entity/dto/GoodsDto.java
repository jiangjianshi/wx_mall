package com.wx.mall.entity.dto;

import com.wx.mall.entity.model.Goods;
import lombok.Data;
import lombok.ToString;

/**
 * Created by jiangjianshi on 18/8/8.
 */
@Data
@ToString
public class GoodsDto extends Goods {

    private String picUrl;
}
