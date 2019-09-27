package com.wang.dao;

import java.util.List;

import com.wang.annotation.MoreDataSourceAnnotation;
import com.wang.bean.User;
import com.wang.util.DataSourceType;
@MoreDataSourceAnnotation(datasource=DataSourceType.OracleDataSouce)
public interface UserDao {
	public List<User> queryAll();
	void insertUser(User user);
	User queryById(long id);
	void updateUser(User user);
}
