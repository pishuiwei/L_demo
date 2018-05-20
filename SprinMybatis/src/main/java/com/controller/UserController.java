package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.UserMapper;
import com.dto.UserDto;

@Controller
@RequestMapping("/test")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/user.html")
	public ModelAndView getUser(ModelMap map){
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userName", "zzzs");
		m.put("userAge", 30);
		m.put("id", 1);
		
		List<UserDto> list = userMapper.userAll();
		for (UserDto user:list) {
			System.out.println(user.getName()+"----"+user.getAge());
		}
		
		System.out.println("-----ִ�гɹ�----");
		return new ModelAndView("index",map);
	}


}









