package com.wang.service;

import java.util.List;

import com.wang.bean.User;

public interface UserService {
	 List<User> queryAll();
	 
	 void dbOperateWithShiwu();
}
