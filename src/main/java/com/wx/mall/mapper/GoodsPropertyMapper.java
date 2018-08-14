package com.wx.mall.mapper;

import com.wx.mall.entity.model.GoodsProperty;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bairong on 2018/8/8.
 */
@Repository
public interface GoodsPropertyMapper {

    List<GoodsProperty> selectByGoodsId(Integer goodsId);
}
