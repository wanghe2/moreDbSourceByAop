package com.wang.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAnnotationAspect {
	
	@Before("@annotation(com.wang.annotation.WangAnnotation)")
	public String beforeAnno(JoinPoint joinPoint) {
		System.err.println("***********这是一个切面，在执行方法之前");
		Object[] args=joinPoint.getArgs();
		if(args.length>0) {
			System.err.println("----第一个参数"+args[0]);
		}
		return "这是一个切面，在执行方法之前";
	}
	
	@After("@annotation(com.wang.annotation.WangAnnotation)")
	public void afterAnno(JoinPoint joinPoint) {
		System.err.println("***********这是一个切面，在执行方法之后");
	}
}
