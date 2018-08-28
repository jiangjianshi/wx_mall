package com.wx.mall.mapper;

import com.wx.mall.entity.model.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper {
	
	
	List<SysMenu> selectUserAllRight(Integer userId);
}
