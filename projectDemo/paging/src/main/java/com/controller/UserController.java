package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.utils.PageRequest;
import com.common.utils.PageResponse;
import com.common.utils.ResponseMsg;
import com.domian.User;
import com.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/index")
	public ModelAndView getUser(ModelMap map){
		Map<String, Object> ma = userService.test();
		System.err.println("pwx494077: " + ma);
		return new ModelAndView("index",map);
	}

	@RequestMapping("/paging")
	@ResponseBody
	public ResponseMsg<PageResponse<User>> getPage(String currentPage, String pageSize, int deleteDataId, String userName, String userAge, int id){
		if (deleteDataId!=0) {
			userService.deleteUser(deleteDataId);
		}
		if(userName!="" || userAge!=""){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("userName", userName);
			param.put("userAge", Integer.parseInt(userAge));
			param.put("id", id);
			userService.updateUser(param);
		}
		ResponseMsg<PageResponse<User>> responseMsg = new ResponseMsg<PageResponse<User>>();
        if(currentPage == "" || pageSize == "" || currentPage.equals("") || pageSize.equals("") || currentPage == null || pageSize == null){
        	System.out.println("分页属性为空");
        }else {
        	Map<String,Object> condition = new HashMap<String,Object>();
        	PageRequest pageRequest = new PageRequest(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        	PageResponse<User> repayment = userService.getUserPaging(pageRequest, condition);
        	responseMsg.setData(repayment);
        	responseMsg.setMsg("获取用户信息成功");
        	responseMsg.setStatus(ResponseMsg.STATUS_SUCCESS);
		}
		return responseMsg;
	}
	
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(int id){
		userService.deleteUser(id);
		return new ModelAndView("index");
	}
}















