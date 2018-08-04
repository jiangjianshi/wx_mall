package com.wx.mall.controller;

import com.wx.mall.common.RespMsg;
import com.wx.mall.entity.model.Address;
import com.wx.mall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/4.
 */

@RestController
@RequestMapping("/address/")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;


    @RequestMapping("addAddress")
    public RespMsg<Integer> addAddress(Address ads) {

        int cnt = addressService.addAdredd(ads);

        return success("添加成功", cnt);
    }


    @RequestMapping("listAddress")
    public RespMsg<List<Address>> listAddress(Integer uid) {
        try {
            List<Address> list = addressService.getAddressList(uid);
            return success("获取成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("获取失败");
        }
    }

    @RequestMapping("getAddressDetail")
    public RespMsg<Address> getAddressDetail(Integer uid, Integer id) {
        try {
            Address ads = addressService.getAddressDeatil(uid, id);
            return success("获取成功", ads);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("获取失败");
        }
    }

    @RequestMapping("delete")
    public RespMsg<Integer> delete(Integer uid, Integer id) {
        try {

            int cnt = addressService.deleteAddress(id);
            return success("删除成功", cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("删除失败");
        }
    }

    @RequestMapping("setDefault")
    public RespMsg<Integer> setDefault(Integer uid, Integer id) {
        int cnt = addressService.setDefault(uid, id);
        if(cnt == 1){
            return success("设置成功", cnt);

        }else {
            return fail("设置默认失败");
        }
    }
}
