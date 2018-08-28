package com.wx.mall.mapper;


import com.wx.mall.common.CodeType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CodeTypeMapper {
	
	
	List<CodeType> selectByTypeCode(String typeCode);
}
