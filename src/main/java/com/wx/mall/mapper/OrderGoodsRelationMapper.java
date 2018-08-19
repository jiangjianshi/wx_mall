package com.wx.mall.mapper;

import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.model.OrderGoodsRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/19.
 */

@Repository
public interface OrderGoodsRelationMapper {


    int insert(@Param("vo") OrderGoodsRelation odr);

    List<GoodsDto> selectGoodsByOrderIds(@Param("idsList") List<Integer> ids);

}
