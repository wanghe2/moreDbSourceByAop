package com.wang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(propagation=Propagation.REQUIRED)
	public void dbOperateWithShiwu() {
		update();
		insert();
	}
	
	public void insert() {
		User user=new User();
		setValue(user);
		userDao.insertUser(user);
	}

	public void setValue(User user) {
		user.setId(100);
		user.setEmail("2290370311@qq.com");
		user.setPhone("17843081319");
		user.setPersonName("王贺");
	}
	
	public void update() {
		User user=userDao.queryById(308);
		user.setPersonName(user.getPersonName()+"--修改");
		userDao.updateUser(user);
		System.err.print("\n***********"+user.toString());
	}
}
