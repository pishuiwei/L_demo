package com.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.abstractDao.AbstractEntity;

@Entity
@Table(name="user")
public class User extends AbstractEntity {   
	private static final long serialVersionUID = -7470745741049254741L;

	@Id
	@GenericGenerator(name = "userId", strategy = "native")
	@GeneratedValue(generator = "userId")
	@Column(length = 32)
	private int id;
	
	@Column(length=32)
	private String  userName;
	
	@Column(length=32)
	private int userAge;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}
}
