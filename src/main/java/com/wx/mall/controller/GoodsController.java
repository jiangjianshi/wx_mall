package com.wx.mall.controller;

import com.wx.mall.common.RespMsg;
import com.wx.mall.entity.dto.GoodsDetailDto;
import com.wx.mall.entity.dto.GoodsDto;
import com.wx.mall.entity.dto.PriceDto;
import com.wx.mall.entity.model.Banner;
import com.wx.mall.entity.model.Goods;
import com.wx.mall.entity.model.GoodsCategory;
import com.wx.mall.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/7.
 */
@Slf4j
@RestController
@RequestMapping("/goods/")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;


    @RequestMapping("listCategory")
    public RespMsg<List<GoodsCategory>> listCategory() {
        List<GoodsCategory> list = goodsService.listAllCat();
        return success("获取成功", list);
    }

    @RequestMapping("listBanner")
    public RespMsg<List<Banner>> listBanner() {
        List<Banner> list = goodsService.listBanner();
        return success("获取成功", list);
    }


    @RequestMapping("listGoods")
    public RespMsg<List<GoodsDto>> listGoods(Integer categoryId, String nameLike) {
        List<GoodsDto> list = goodsService.listGoods(categoryId, nameLike);
        return success("获取成功", list);
    }


    @RequestMapping("getGoodsDetail")
    public RespMsg<GoodsDetailDto> getGoodsDetail(Integer goodsId) {

        try {
            GoodsDetailDto detail = goodsService.getGoodsDetail(goodsId);
            return success("获取成功", detail);
        } catch (Exception e) {
            log.error("获取详情失败.", e);
            return fail("获取详情失败.");
        }
    }

    @RequestMapping("calSelectedPrice")
    public RespMsg<PriceDto> calSelectedPrice(Integer goodsId, String propertyChildIds) {

        try {
            PriceDto PriceDto = goodsService.calSelectedPrice(goodsId, propertyChildIds);
            return success("获取成功", PriceDto);
        } catch (Exception e) {
            log.error("获取失败.", e);
            return fail("获取失败.");
        }
    }

}
