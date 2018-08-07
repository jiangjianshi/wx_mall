package com.wx.mall.service;

import com.wx.mall.entity.model.Banner;
import com.wx.mall.entity.model.GoodsCategory;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/7.
 */
public interface GoodsCategoryService {

    List<GoodsCategory> listAllCat();

    List<Banner> listBanner();
}
