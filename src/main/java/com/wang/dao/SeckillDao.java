package com.wang.dao;

import java.util.List;

import com.wang.bean.Seckill;

public interface SeckillDao {

	/**
	 * 根据id查询秒杀商品
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	/**
	 * 查询全部
	 * @return
	 */
	List<Seckill>queryAll();
	
	void insertSeckill(Seckill seckill);
	
	void updateSeckill(Seckill seckill);
}
