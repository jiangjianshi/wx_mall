package com.wx.mall.mapper;

import com.wx.mall.entity.model.GoodsProperty;
import com.wx.mall.entity.model.GoodsPropertyType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bairong on 2018/8/8.
 */
@Repository
public interface GoodsPropertyTypeMapper {
    
    GoodsPropertyType selectByPrimaryKey(Integer id);
}
