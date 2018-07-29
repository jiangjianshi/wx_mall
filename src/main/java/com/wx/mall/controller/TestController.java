package com.wx.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bairong on 2018/7/29.
 */
@Controller
public class TestController {

    @RequestMapping("index")
    public String index(){
        return "login";
    }


}
