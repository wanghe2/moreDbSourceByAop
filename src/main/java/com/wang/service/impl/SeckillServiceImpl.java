package com.wang.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wang.bean.Seckill;
import com.wang.dao.SeckillDao;
import com.wang.service.SeckillService;

@Service("seckillService")
public class SeckillServiceImpl implements SeckillService{
	
	@Resource
	private SeckillDao seckillDao;

	public List<Seckill> queryAll(){
		List<Seckill>seckills= seckillDao.queryAll();
		return seckills;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void dbOperateWithShiwu() {
		update();
		insert();
	}
	
	public void insert() {
		Seckill seckill=new Seckill();
		setValue(seckill);
		seckillDao.insertSeckill(seckill);
	}
	
	public void update() {
		Seckill seckill=seckillDao.queryById(1008L);
		seckill.setName(seckill.getName()+"--修改");
		seckillDao.updateSeckill(seckill);
	}
	
	private void setValue(Seckill seckill) {
		seckill.setSeckillId(8888);
		seckill.setCreateTime(new Date());
		seckill.setStartTime(getTime(Calendar.MONTH,-1));
		seckill.setEndTime(getTime(Calendar.MONTH,1));
		seckill.setName("5G华为荣耀10");
		seckill.setNumber(20);
	}
	
	private Date getTime(int feild,int amount){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(feild,amount);
		return calendar.getTime();
	}
}
