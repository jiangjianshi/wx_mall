package com.wx.mall.service;

import com.wx.mall.entity.model.WxUser;

/**
 * Created by bairong on 2018/7/29.
 */
public interface WxUserService {

    int saveUser(String code, WxUser user);

    WxUser checkLogin(String uid);
}
