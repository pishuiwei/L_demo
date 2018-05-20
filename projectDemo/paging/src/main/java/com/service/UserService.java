package com.service;

import java.util.Map;

import com.common.utils.PageRequest;
import com.common.utils.PageResponse;
import com.domian.User;

public interface UserService {
	
	public PageResponse<User> getUserPaging(PageRequest pageRequest,Map<String, Object> condition);
	
	public void deleteUser(int id);
	
	public void updateUser(Map<String, Object> map);
	
	public Map<String, Object> test();
}
