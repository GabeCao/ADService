package com.zytx;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.dao.UserDao;
import com.zytx.entity.User;

public class UserDaoTEst extends BaseTest{
	@Autowired
	private UserDao userDao;
	
	@Test
	public void TEset() {
		User user = userDao.getUserByusername("a");
		System.out.println(user);
	}

}
