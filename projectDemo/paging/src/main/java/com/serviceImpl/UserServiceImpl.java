package com.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.PageRequest;
import com.common.utils.PageResponse;
import com.dao.UserMapper;
import com.domian.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper uerMapper;

	@Override
	public PageResponse<User> getUserPaging(PageRequest pageRequest, Map<String, Object> condition) {
		PageResponse<User> pageResponse = new PageResponse<User>(pageRequest);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("pageSize", pageRequest.getPageSize());
		param.put("offset", pageRequest.getOffset());
		param.putAll(condition);
		try {
			pageResponse.setRecords(uerMapper.findUser(param));
			pageResponse.setTotalCount(uerMapper.countUser());
		} catch (Exception e) {
			System.out.println("获取用户分页数据异常"+e);
		}
		return pageResponse;
	}

	@Override
	public void deleteUser(int id) {
		uerMapper.deleteUser(id);
	}

	@Override
	public void updateUser(Map<String, Object> map) {
		uerMapper.updateUser(map);
	}

	@Override
	public Map<String, Object> test() {
		return uerMapper.test();
	}
}








