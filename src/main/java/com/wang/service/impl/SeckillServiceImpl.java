package com.wang.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wang.bean.Seckill;
import com.wang.dao.SeckillDao;
import com.wang.service.SeckillService;

@Service("seckillService")
public class SeckillServiceImpl implements SeckillService{
	
	@Resource
	private SeckillDao seckillDao;
	
	@Override
	public List<Seckill> queryAll(){
		List<Seckill>seckills= seckillDao.queryAll();
		return seckills;
	}
	
}
