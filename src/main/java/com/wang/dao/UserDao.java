package com.wang.dao;

import java.util.List;

import com.wang.annotation.MoreDataSourceAnnotation;
import com.wang.bean.User;
import com.wang.util.DataSourceType;

public interface UserDao {
	@MoreDataSourceAnnotation(datasource=DataSourceType.OracleDataSouce)
	public List<User> queryAll();
}
