package com.dao;

import org.springframework.stereotype.Repository;

import com.abstractDao.AbstractDaoImpl;
import com.domain.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao{

	public void deleteUser(User user) {
		delete(user);
	}

	public void updateUser(User user) {
		update(user);
	}
	
}
