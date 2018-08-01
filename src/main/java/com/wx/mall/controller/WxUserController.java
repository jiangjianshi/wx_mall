package com.wx.mall.controller;

import com.wx.mall.common.RespMsg;
import com.wx.mall.entity.model.WxUser;
import com.wx.mall.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bairong on 2018/7/29.
 */
@Slf4j
@RestController
@RequestMapping("/user/")
public class WxUserController extends BaseController {

    @Autowired
    private WxUserService wxUserService;


    @RequestMapping("saveUser")
    public RespMsg<Integer> saveUser(WxUser user) {

        try {
            int count = wxUserService.saveUser(user);
            if (count == 1) {
                return success("保存成功");
            } else {
                return fail("保存失败");
            }
        } catch (Exception e) {
            log.error("保存异常", e);
            return fail("保存异常");
        }

    }




}
