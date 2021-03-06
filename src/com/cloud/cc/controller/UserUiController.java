package com.cloud.cc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.cc.service.UiTableService;
import com.cloud.cc.tools.StringUnits;
import com.cloud.cc.vo.UiTable;
import com.cloud.cc.vo.Users;

@Controller
public class UserUiController {

	@Autowired
	private UiTableService uiTableService;
	
	/**
	 * 获取ui列表
	 * @param request cloudId-云控项目Id
	 * @return
	 */
	@RequestMapping("/getUiTableList")
	@ResponseBody
	public Map<String,Object> getUiTableList(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Users user=(Users)request.getSession().getAttribute("user");
		String cloudId=request.getParameter("cloudId");
		if(StringUnits.isEmpty(cloudId) || !StringUnits.isInteger(cloudId)){
			resultMap.put("code", 2);	//参数错误
			return resultMap;
		}
		resultMap.put("code", 1);
		resultMap.put("data",uiTableService.selectByUserId(user.getUserid(), Integer.parseInt(cloudId)));
		return resultMap;
	}
	
	/**
	 * 添加Ui表
	 * @param request addUiTable.action
	 * @return
	 */
	@RequestMapping("/addUiTable")
	@ResponseBody
	public Map<String,Object> addUiTable(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		String uitName=request.getParameter("uitName");
		String uiJson=request.getParameter("uiJson");
		String cloudId=request.getParameter("cloudId");
		Users user=(Users)request.getSession().getAttribute("user");
		if(StringUnits.isEmpty(uitName)){
			resultMap.put("code", 2);	//参数错误
			resultMap.put("msg", "表名称必须要填");
			return resultMap;
		}
		if(StringUnits.isEmpty(uiJson)){
			resultMap.put("code", 2);	//参数错误
			resultMap.put("msg", "表内容必须要填");
			return resultMap;
		}
		if(StringUnits.isEmpty(cloudId) || !StringUnits.isInteger(cloudId)){
			resultMap.put("code", 2);
			resultMap.put("msg","请选择一个正确的项目");
			return resultMap;
		}
		UiTable uiTable=new UiTable();
		uiTable.setCloudid(Integer.parseInt(cloudId));
		uiTable.setCreatetime(new Date());
		uiTable.setUijson(uiJson);
		uiTable.setUitname(uitName);
		uiTable.setUserid(user.getUserid());
		resultMap.put("code",uiTableService.addUiData(uiTable));
		return resultMap;
	}
	
	/**
	 * 获取Ui表信息
	 * @param request uitId=Ui表Id
	 * @return
	 */
	@RequestMapping("/selectUiTableFiled")
	@ResponseBody
	public Map<String,Object> selectUiTableFiled(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		String uitId=request.getParameter("uitId");
		if(StringUnits.isEmpty(uitId) || !StringUnits.isInteger(uitId)){
			resultMap.put("code", 2);	//	参数错误
			return resultMap;
		}
		resultMap.put("code", 1);
		resultMap.put("data", uiTableService.selectById(Integer.parseInt(uitId)));
		return resultMap;
	}
	
	
	/**
	 * 删除Ui表数据
	 * @param request uitId=Ui表Id
	 * @return
	 */
	@RequestMapping("/delUiTable")
	@ResponseBody
	public Map<String,Object> delUiTable(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		String uitId=request.getParameter("uitId");
		if(StringUnits.isEmpty(uitId) || !StringUnits.isInteger(uitId)){
			resultMap.put("code", 2);	//	参数错误
			return resultMap;
		}
		resultMap.put("code", uiTableService.delUiData(Integer.parseInt(uitId)));
		return resultMap;
	}
	
	/**
	 * 修改UI表
	 * @param request uitName-表名称 uiJson-json字符串 uitId-Ui表数据id
	 * @return
	 */
	@RequestMapping("/updateUiTable")
	@ResponseBody
	public Map<String,Object> updateUiTable(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String, Object>();
		String uitName=request.getParameter("uitName");
		String uiJson=request.getParameter("uiJson");
		String uitId=request.getParameter("uitId");
		Users user=(Users)request.getSession().getAttribute("user");
		if(StringUnits.isEmpty(uitName)){
			resultMap.put("code", 2);	//参数错误
			resultMap.put("msg", "表名称必须要填");
			return resultMap;
		}
		if(StringUnits.isEmpty(uiJson)){
			resultMap.put("code", 2);	//参数错误
			resultMap.put("msg", "表内容必须要填");
			return resultMap;
		}
		if(StringUnits.isEmpty(uitId) || !StringUnits.isInteger(uitId)){
			resultMap.put("code", 2);
			resultMap.put("msg","请填写正确的uitId");
			return resultMap;
		}
		UiTable uiTable=new UiTable();
		uiTable.setUitid(Integer.parseInt(uitId));
		uiTable.setUijson(uiJson);
		uiTable.setUitname(uitName);
		uiTable.setUserid(user.getUserid());
		resultMap.put("code",uiTableService.updateUiData(uiTable));
		return resultMap;
	}
	
}
