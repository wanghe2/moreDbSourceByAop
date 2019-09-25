package com.wang.bean;
/**
 * 数据源提供者，
 * 其实为了配合MultipleDataSource的determineCurrentLookupKey（）方法使用
 * 这里的数据源的键设置成哪个数据源，在执行数据库操作时就使用对应的数据源（细心观察会发现变量是static类型的）
 * （注：不同用户访问系统，会存在多线程的情况，为保证各自线程的数据源不受干扰，使用ThreadLocal）
 * 
 * @author wanghe
 *
 */
public class DataSouceProvide {
	private static ThreadLocal<String>datasouceKey=new ThreadLocal<String>();
	
	public static String getDataSource() {
		return datasouceKey.get();
	}
	
	public static void setDataSource(String dbKey) {
		datasouceKey.set(dbKey);
	}
	/**
	 * 清除数据源，使用默认数据源
	 */
	public static void clear() {
		datasouceKey.remove();
    }
}
