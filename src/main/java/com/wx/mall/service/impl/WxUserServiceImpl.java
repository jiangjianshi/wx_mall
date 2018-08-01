package com.wx.mall.service.impl;

import com.wx.mall.entity.model.WxUser;
import com.wx.mall.mapper.WxUserMapper;
import com.wx.mall.service.WxUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by bairong on 2018/7/29.
 */
@Service
@Transactional
public class WxUserServiceImpl implements WxUserService{

    @Resource
    private WxUserMapper wxUserMapper;


    @Override
    public int saveUser(WxUser user) {

        wxUserMapper.selectByPrimaryKey(1);
        return  wxUserMapper.insert(user);
    }
}
