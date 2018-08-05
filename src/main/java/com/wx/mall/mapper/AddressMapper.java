package com.wx.mall.mapper;

import com.wx.mall.entity.model.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangjianshi on 18/8/4.
 */

@Repository
public interface AddressMapper {

    Address selectByPrimaryKey(Integer id);

    List<Address> selectByUid(Integer uid);

    int insert(@Param("vo") Address vo);

    int updateSelective(@Param("vo") Address vo);

    int updateDefaultByUid(@Param("isDefault") Integer isDefault, @Param("uid") Integer uid);

    Address selectDefaultAddress(@Param("uid") Integer uid);

}
