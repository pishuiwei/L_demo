package com.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.domian.User;

@Repository
public interface UserMapper {
	
	/**
	 * <p>分页查询用户
	 * @param condition 分页条件
	 * @return
	 */
	public List<User> findUser(Map<String, Object> condition);
	
	/**
	 * <p>总记录数
	 * @return
	 */
	public int countUser();
	
	/**
	 * <p>通过id删除
	 * @param id
	 */
	public void deleteUser(int id);
	
	/**
	 * <p>通过id修改
	 * @param map
	 */
	public void updateUser(Map<String, Object> map);
	
	public Map<String, Object> test();
}




