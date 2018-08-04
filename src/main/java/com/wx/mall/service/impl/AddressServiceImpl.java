package com.wx.mall.service.impl;

import com.wx.mall.entity.model.Address;
import com.wx.mall.mapper.AddressMapper;
import com.wx.mall.service.AddressService;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/4.
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;


    @Override
    public int addAdredd(Address address) {

        if (address.getId() != null && address.getId() != 0) {
            return addressMapper.updateSelective(address);
        }
        address.setStatus(1); //有效
        return addressMapper.insert(address);
    }

    @Override
    public List<Address> getAddressList(Integer uid) {
        List<Address> list = addressMapper.selectByUid(uid);
        return list;
    }

    @Override
    public Address getAddressDeatil(Integer uid, Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public int deleteAddress(Integer addressId) {

        Address address = new Address();
        address.setStatus(0);
        address.setId(addressId);

        return addressMapper.updateSelective(address);
    }

    @Override
    public int setDefault(Integer uid, Integer addressId) {

        addressMapper.updateDefaultByUid(0 ,uid);

        Address ads = new Address();
        ads.setId(addressId);
        ads.setIsDefault(1);
       int cnt =  addressMapper.updateSelective(ads);
        return cnt;
    }
}
