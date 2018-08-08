package com.wx.mall.mapper;

import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.model.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/8.
 */
@Repository
public interface GoodsMapper {


    List<GoodsDto> selectByCategoryAndName(@Param("categoryId") Integer categoryId, @Param("name") String name);
}
