package com.wang.bean;

import java.sql.Date;

public class Seckill {
	private long seckill_id;
	private String name;
	private Integer number;
	private Date create_time;
	private Date end_time;
	public long getSeckill_id() {
		return seckill_id;
	}
	public void setSeckill_id(long seckill_id) {
		this.seckill_id = seckill_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	@Override
	public String toString() {
		return seckill_id+"--"+name+"--"+number+"--"+create_time+"--"+end_time;
	}
}
