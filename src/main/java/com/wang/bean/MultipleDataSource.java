package com.wang.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * AbstractRoutingDataSource spring的动态数据源，包含默认数据源和目标数据源
 * @author wanghe
 *
 */
public class MultipleDataSource extends AbstractRoutingDataSource{
	/**
	 * 通过查看源码注释，重写该方法，就是获取当前数据源的键值
	 * (因为AbstractRoutingDataSource 的targetDataSources是多组键值对，这里根据键值获取对应的数据源)
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSouceProvide.getDataSource();
	}

}
