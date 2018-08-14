package com.wx.mall.service.impl;

import com.google.common.collect.Lists;
import com.wx.mall.entity.dto.GoodsDetailDto;
import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.dto.PropertiesDto;
import com.wx.mall.entity.model.*;
import com.wx.mall.mapper.*;
import com.wx.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        for (Integer type : groupMap.keySet()) {
            PropertiesDto dto = new PropertiesDto();
            dto.setTypeId(type);
            if(type == 1){
                dto.setTypeName("颜色");
            }else {
                dto.setTypeName("尺寸");
            }
            dto.setChildsCurGoods(groupMap.get(type));
            properties.add(dto);
        }

        GoodsDetailDto detailDto = new GoodsDetailDto();
        detailDto.setBasicInfo(baseInfo);
        detailDto.setPics(pics);
        detailDto.setProperties(properties);
        return detailDto;
    }
}
