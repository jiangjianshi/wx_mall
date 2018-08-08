package com.wx.mall.entity.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jiangjianshi on 18/8/8.
 */

@Data
@ToString
public class Goods {

    private Integer id; //
    private String name; //
    private Integer categoryId; //类别
    private Integer storeAmount; //库存量
    private double minPrice; //最低价格
    private double originalPrice; //原始价格
    private String brand; //品牌
    private Date marketTime; //上市时间
    private String goodsDesc; //
    private Integer status; //状态： 1 上架 , 0下架
    private Date createTime; //
    private Date updateTime; //

}
