package com.wx.mall.controller;

import com.wx.mall.common.CodeType;
import com.wx.mall.entity.model.SysUser;
import com.wx.mall.mapper.CodeTypeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
public class CommonController {

	@Resource
	private CodeTypeMapper codeTypeMapper;

	@ResponseBody
	@RequestMapping(path = "/IncJs.a")
	public void CommsJsController(HttpServletRequest request, HttpServletResponse response) throws IOException {

		SysUser user = (SysUser) request.getSession().getAttribute(request.getSession().getId());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript");
		StringBuffer wsbf = new StringBuffer();

		if (user == null) {
			wsbf.append(
					"var auto_js_codes=function(){};var auto_js_codes_imp=new auto_js_codes();function get_js_codeText(a,b){}function has_js_codeText(a,b){}");
		} else {
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String day = String.valueOf(cal.get(Calendar.DATE));
			month = month.length() == 1 ? "0".concat(month) : month;
			day = day.length() == 1 ? "0".concat(day) : day;
			String curDate = year + "-" + month + "-" + day;
			wsbf.append("var curDateTime = '" + curDate + "';\n");

			wsbf.append("var auto_js_codes=function(){};\n");
			wsbf.append("auto_js_codes.prototype={ \n");
			Map<String, String[]> reqMap = request.getParameterMap();
			String c[] = (String[]) reqMap.get("c");
			if (c != null && c.length >= 1) {
				for (String ic : c) {
					List<CodeType> codeList = codeTypeMapper.selectByTypeCode(ic);
					CodeType codeTypeModel = codeList.get(0);
					if (codeTypeModel != null) {
						wsbf.append("	//");
						wsbf.append(codeTypeModel.getTypeName());
						wsbf.append("\n");
						wsbf.append("	");
						wsbf.append(codeTypeModel.getTypeCode());
						wsbf.append("s_js:[");
						Iterator<CodeType> its = codeList.iterator();
						while (its.hasNext()) {
							CodeType ct = its.next();
							wsbf.append("{'code_name':'");
							wsbf.append(ct.getCodeName());
							wsbf.append("',");
							wsbf.append("'code_value':'");
							wsbf.append(ct.getCodeValue());
							wsbf.append("'},");
						}
						wsbf.deleteCharAt(wsbf.length() - 1);
						// wsbf.delete(wsbf.length()-1, wsbf.length());
						wsbf.append("],\n");
					}
				}
				wsbf.delete(wsbf.length() - 2, wsbf.length());
			}
			wsbf.append("\n};\n");
			wsbf.append("\n");
			wsbf.append("var auto_js_codes_imp=new auto_js_codes();\n");
			wsbf.append("function get_js_codeText(a,c){\n");
			wsbf.append("	var codes=auto_js_codes_imp[a];\n");
			wsbf.append("	if(!codes){return '未知';};\n");
			wsbf.append("	for(var i=0;i<codes.length;i++){\n");
			wsbf.append("		if(codes[i].code_value==c){\n");
			wsbf.append("			return codes[i].code_name;\n");
			wsbf.append("		}\n");
			wsbf.append("	}\n");
			wsbf.append("	return '未知'+ c;\n");
			wsbf.append("}\n");

			wsbf.append("function has_js_codeText(a){\n");
			wsbf.append("	var codes=auto_js_codes_imp[a];\n");
			wsbf.append("	if(!codes){return false;};\n");
			wsbf.append("	return true;\n");
			wsbf.append("}\n");

		}
		response.getWriter().write(wsbf.toString());
	}

	@GetMapping(path = "/toPage")
	public String toPage(String subPath, String file) {

		return subPath + "/" + file;
	}
}
