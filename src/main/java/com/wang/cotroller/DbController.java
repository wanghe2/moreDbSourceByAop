package com.wang.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wang.annotation.WangAnnotation;
import com.wang.bean.Seckill;
import com.wang.bean.User;
import com.wang.service.SeckillService;
import com.wang.service.UserService;

@RestController
public class DbController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SeckillService seckillService;
	
	@RequestMapping("/getUserList")
	public String getAllUser() {
		List<User> users=userService.queryAll();
		StringBuilder userstr=new StringBuilder();
		for(User user: users) {
			userstr.append(user.toString());
			userstr.append("***********");
		}
		return userstr.toString();
	}
	
	@RequestMapping("getSeckillList")
	public String getSeckillList() {
		List<Seckill>seckills=seckillService.queryAll();
		StringBuilder seckillstr=new StringBuilder();
		for(Seckill seckill: seckills) {
			seckillstr.append(seckill.toString());
			seckillstr.append("***********");
		}
		return seckillstr.toString();
	}
	
	
	@RequestMapping("test")
	@WangAnnotation
	public String test(@RequestParam String name) {//切面拦截注解
		return "good";
	}
	
	
}
