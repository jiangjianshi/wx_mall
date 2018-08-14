package com.wx.mall.mapper;

import com.wx.mall.entity.model.GoodsPics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/14.
 */
@Repository
public interface GoodsPicsMapper {

   List<GoodsPics> selectByGoodsId(@Param("goodsId") Integer goodsId);
}
