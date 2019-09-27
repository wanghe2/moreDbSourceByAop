package com.wang.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.wang.annotation.MoreDataSourceAnnotation;
import com.wang.bean.DataSouceProvide;
/**
 * 数据源切换的切面处理（配合注解使用）
 * @author wanghe
 * 
 * 切面无法拦截接口上的注解方法（springboot 1.x版本的问题）
 * 解决办法：不用注解方式启动aop，采用扫描mapper下所有接口任意方法来启动aop切面，再扫描mapper接口方法上注解获取采用的数据源
 *
 */
@Aspect
@Order(2)
@Component
public class DataSouceChangeAspect {

	//屏蔽的写法，以注解方式做切点，无法拦截接口上的方法，（据说springboot2.x版本可以）	
	//@Pointcut("@annotation(com.wang.annotation.MoreDataSourceAnnotation)")
	@Pointcut("execution(* com.wang..*.*(..))")
	public void changeDatabase() {}
	
	@Before("changeDatabase()")
    public void changeBefore(JoinPoint joinPoint)
    {
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
		//取得切点方法上的注解，根据值去判断该加载哪个数据源
		MoreDataSourceAnnotation annotationClass = method.getAnnotation(MoreDataSourceAnnotation.class);
        if(annotationClass == null){
//        	获取类上面的注解(我把注解加在的是接口上，所以有所调整)
//            annotationClass = joinPoint.getTarget().getClass().getAnnotation(MoreDataSourceAnnotation.class);
        	Class<?>[]interface_list= joinPoint.getTarget().getClass().getInterfaces();
        	if(interface_list.length>0) {
        		annotationClass=interface_list[0].getAnnotation(MoreDataSourceAnnotation.class);
        	}
        	if(annotationClass == null) return;
        }
        //获取注解上的数据源的值的信息
        String dataSourceKey = annotationClass.datasource();
        if(dataSourceKey !=null){
            //给当前的执行SQL的操作设置特殊的数据源的信息
            DataSouceProvide.setDataSource(dataSourceKey);
        }
        String current_dataSourceKey=dataSourceKey==null?"默认数据源":dataSourceKey;
        System.err.println(joinPoint.getTarget().getClass().getName()+"--"+method.getName()+"通过切面切换数据源"+current_dataSourceKey);
    
    }
	
	
	@After("changeDatabase()")
	public void changeAfter() {
		DataSouceProvide.clear();
	}
	
}
