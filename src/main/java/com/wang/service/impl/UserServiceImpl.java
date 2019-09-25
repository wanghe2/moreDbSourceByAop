package com.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.bean.User;
import com.wang.dao.UserDao;
import com.wang.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	public List<User> queryAll() {
		List<User>users=userDao.queryAll();
		return users;
	}

}
