package com.wx.mall.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.wx.mall.entity.dto.GoodsDetailDto;
import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.dto.PriceDto;
import com.wx.mall.entity.dto.PropertiesDto;
import com.wx.mall.entity.model.*;
import com.wx.mall.mapper.*;
import com.wx.mall.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

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

    @Autowired
    private GoodsPicsMapper goodsPicsMapper;

    @Autowired
    private GoodsPropertyMapper goodsPropertyMapper;

    @Autowired
    private GoodsPropertyTypeMapper goodsPropertyTypeMapper;

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

    @Override
    public GoodsDetailDto getGoodsDetail(Integer goodsId) {


        Goods baseInfo = goodsMapper.selectByPrimaryKey(goodsId);
        List<GoodsPics> pics = goodsPicsMapper.selectByGoodsId(goodsId);

        List<GoodsProperty> propertiesList = goodsPropertyMapper.selectByGoodsId(goodsId);
        Map<Integer, List<GoodsProperty>> groupMap = propertiesList.stream().collect(groupingBy(GoodsProperty::getPropTypeId));

        List<PropertiesDto> properties = Lists.newArrayList();
        for (Integer typeId : groupMap.keySet()) {
            GoodsPropertyType propType = goodsPropertyTypeMapper.selectByPrimaryKey(typeId);

            PropertiesDto dto = new PropertiesDto();
            BeanUtils.copyProperties(propType, dto);

            dto.setChildsCurGoods(groupMap.get(typeId));
            properties.add(dto);
        }

        GoodsDetailDto detailDto = new GoodsDetailDto();

        GoodsDto dto = new GoodsDto();
        BeanUtils.copyProperties(baseInfo, dto);
        for (GoodsPics pic : pics) {
            if (pic.getIsDefault() == 1) {
                dto.setPicUrl(pic.getPicUrl());
            }
        }
        detailDto.setBasicInfo(dto);
        detailDto.setPics(pics);
        detailDto.setProperties(properties);
        return detailDto;
    }


    @Override
    public PriceDto calSelectedPrice(Integer goodsId, String propertyChildIds) {

        Goods gd = goodsMapper.selectByPrimaryKey(goodsId);
        Map<String, String> map = Splitter.on(",").omitEmptyStrings().trimResults().withKeyValueSeparator(":").split(propertyChildIds);
        Collection<String> values = map.values();

        double selectedPrice = gd.getMinPrice();
        for (String id : values) {
            GoodsProperty prop = goodsPropertyMapper.selectByPrimaryKey(Integer.parseInt(id));
            selectedPrice += prop.getAddedPrice();
        }

        PriceDto price = new PriceDto();
        price.setPrice(selectedPrice);
        price.setScore(0);
        price.setStores(gd.getStoreAmount());
        return price;
    }
}
