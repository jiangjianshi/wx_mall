package com.wx.mall.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SysUser {
	
	private Integer id; //主键
	private String account; //用户名
	private String role; //角色
	private String password; //密码
	private String confirmPwd; //密码
	private String realName; //真实姓名
	private String salt; //加密盐值
	private String token; //登录token
	private Integer status; //账号状态  1 可用，2 不可用
	private String loginIp; //登录IP
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime; //最后登录时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime; //创建时间
	private Date updateTime; //修改时间
	
}
