package com.wx.mall.service;

import com.wx.mall.entity.model.Address;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/4.
 */
public interface AddressService {

    int addAdredd(Address address);

    List<Address> getAddressList(Integer uid);

    Address getAddressDeatil(Integer uid, Integer addressId);

    int deleteAddress(Integer addressId);

    int setDefault(Integer uid, Integer addressId);
}
