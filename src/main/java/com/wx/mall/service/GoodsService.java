package com.wx.mall.service;

import com.wx.mall.entity.dto.GoodsDetailDto;
import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.dto.PriceDto;
import com.wx.mall.entity.model.Banner;
import com.wx.mall.entity.model.Goods;
import com.wx.mall.entity.model.GoodsCategory;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/7.
 */
public interface GoodsService {

    List<GoodsCategory> listAllCat();

    List<Banner> listBanner();

    List<GoodsDto> listGoods(Integer catId, String name);

    GoodsDetailDto getGoodsDetail(Integer goodsId);

    PriceDto calSelectedPrice(Integer goodsId, String propertyChildIds);
}
