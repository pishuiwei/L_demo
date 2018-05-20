package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.domian.User;
import com.dto.UserDto;

@Repository
public interface UserMapper {
	
	public User findById(int id);
	
	public void deleteUser(int id);
	
	public void addUser(Map<String, Object> map);
	
	public void updateUser(Map<String, Object> map);
	
	public List<UserDto> userAll();
	
}
