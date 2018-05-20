package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.UserDao;
import com.domain.User;
import com.log.Log;

@Controller
public class UserController extends Log{
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/pp")
	public ModelAndView name(){
		ModelMap modelMap = new ModelMap();
		User user = userDao.findById(1);
		modelMap.put("user", user.getUserName());
		return new ModelAndView("index",modelMap);
	}
	
	@RequestMapping("/testJson")
	@ResponseBody
	public List<User> getJson(){
		return userDao.findByHql("from User");
	}
	
}





