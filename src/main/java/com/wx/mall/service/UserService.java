package com.wx.mall.service;

import com.wx.mall.common.PagedList;
import com.wx.mall.common.RespMsg;
import com.wx.mall.entity.dto.RrightResultDto;
import com.wx.mall.entity.model.SysUser;

import javax.servlet.http.HttpServletRequest;


public interface UserService {
	
	RespMsg<SysUser> login(HttpServletRequest req);
	
	RrightResultDto getTreeMenus(Integer userId);
	
	PagedList<SysUser> queryAllUsers(String account);
	
	int addUsers(SysUser user);
	
	int updateUsers(SysUser user);
}
