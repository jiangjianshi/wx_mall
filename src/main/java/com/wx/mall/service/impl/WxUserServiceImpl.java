package com.wx.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.mall.entity.dto.WxSession;
import com.wx.mall.entity.model.WxUser;
import com.wx.mall.mapper.WxUserMapper;
import com.wx.mall.service.WxUserService;
import com.wx.mall.util.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bairong on 2018/7/29.
 */
@Service
@Transactional
public class WxUserServiceImpl implements WxUserService {

    @Resource
    private WxUserMapper wxUserMapper;


    @Override
    public int saveUser(String code, WxUser user) {

//        https://api.weixin.qq.com/sns/jscode2session?appid=wx20452b87603c728d&secret=e5e1887306ee8ea0aad9fd6f174d434a&js_code=071FQsy60RS7sJ1Du7y60d5By60FQsy-&grant_type=authorization_code

        Map<String, String> param = new HashMap<>();
        param.put("appid", "wx20452b87603c728d");
        param.put("secret", "e5e1887306ee8ea0aad9fd6f174d434a");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        WxSession session = null;
        try {
            String result = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", param);
            session = JSON.parseObject(result, WxSession.class);

            WxUser wxUser = wxUserMapper.selectByOpenId(session.getOpenid());
            if (wxUser != null) {
                return wxUser.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("用户注册异常");
        }
        if(session != null){
            user.setOpenId(session.getOpenid());
        }
        wxUserMapper.insert(user);
        return user.getId();
    }


    @Override
    public WxUser checkLogin(String uid) {

        return wxUserMapper.selectByPrimaryKey(Integer.parseInt(uid));
    }
}
