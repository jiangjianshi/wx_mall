package com.wx.mall.mapper;

import com.wx.mall.entity.model.SysMenu;

import java.util.List;


public interface SysMenuMapper {
	
	
	List<SysMenu> selectUserAllRight(Integer userId);
}
