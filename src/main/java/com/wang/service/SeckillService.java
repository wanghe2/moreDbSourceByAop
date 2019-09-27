package com.wang.service;

import java.util.List;

import com.wang.bean.Seckill;

public interface SeckillService {
	public List<Seckill> queryAll();  
	
	void dbOperateWithShiwu();
	
}
