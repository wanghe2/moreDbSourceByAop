package com.wang.dao;

import java.util.List;

import com.wang.bean.Seckill;
/**
 * 其实可以用  @MoreDataSourceAnnotation(datasource=DataSourceType.MysqlDataSouce)去修饰，
 * 但是默认数据源就是mysqlsouce,所以没这必要了
 * @author wanghe
 *
 */
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

}
