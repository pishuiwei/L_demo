package com.dao;

import com.abstractDao.AbstractDao;
import com.domain.User;

public interface UserDao extends AbstractDao<User>{

	public void deleteUser(User  user);
	
	public void updateUser(User user);

}
