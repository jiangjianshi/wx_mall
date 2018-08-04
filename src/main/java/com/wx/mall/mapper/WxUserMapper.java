package com.wx.mall.mapper;

import com.wx.mall.entity.model.WxUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangjianshi on 18/8/1.
 */
@Repository
public interface WxUserMapper {

    WxUser selectByPrimaryKey(Integer uid);

    int insert(@Param("vo") WxUser vo);
}
