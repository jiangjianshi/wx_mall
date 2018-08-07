package com.wx.mall.service.impl;

import com.wx.mall.entity.model.Banner;
import com.wx.mall.entity.model.GoodsCategory;
import com.wx.mall.mapper.BannerMapper;
import com.wx.mall.mapper.GoodsCategoryMapper;
import com.wx.mall.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/7.
 */

@Service
@Transactional
public class GoodsCategoryServiceImpl implements GoodsCategoryService{

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<GoodsCategory> listAllCat() {
        return goodsCategoryMapper.selectAll();
    }


    @Override
    public List<Banner> listBanner() {
        return bannerMapper.selectAll();
    }
}
