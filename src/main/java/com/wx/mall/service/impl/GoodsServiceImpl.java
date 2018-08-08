package com.wx.mall.service.impl;

import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.model.Banner;
import com.wx.mall.entity.model.Goods;
import com.wx.mall.entity.model.GoodsCategory;
import com.wx.mall.mapper.BannerMapper;
import com.wx.mall.mapper.GoodsCategoryMapper;
import com.wx.mall.mapper.GoodsMapper;
import com.wx.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/7.
 */

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsCategory> listAllCat() {
        return goodsCategoryMapper.selectAll();
    }


    @Override
    public List<Banner> listBanner() {
        return bannerMapper.selectAll();
    }


    @Override
    public List<GoodsDto> listGoods(Integer catId, String name) {

        return goodsMapper.selectByCategoryAndName(catId, name);
    }
}
