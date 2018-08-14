package com.wx.mall.entity.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jiangjianshi on 18/8/14.
 */
@Data
@ToString
public class GoodsPropertyType {

    private Integer id; //
    private Integer typeId; //
    private String typeName; //名称
    private Date createTime; //
    private Date updateTime; //
}
