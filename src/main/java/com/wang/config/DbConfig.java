package com.wang.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.wang.bean.MultipleDataSource;
import com.wang.util.DataSourceType;
import com.wang.util.DbPropertiesByAuto;

@Configuration
@MapperScan("com.wang.dao")
public class DbConfig {
	@Autowired
	DbPropertiesByAuto dbPropertiesByAuto;
	
	@Bean("mysqlSource")
	public DataSource mysqlSource() {
		DruidDataSource mysqlSource=new DruidDataSource();
		mysqlSource.setDriverClassName(dbPropertiesByAuto.mysqldirver);
		mysqlSource.setUrl(dbPropertiesByAuto.mysqlurl);
		mysqlSource.setUsername(dbPropertiesByAuto.mysqlusername);
		mysqlSource.setPassword(dbPropertiesByAuto.mysqlpwd);
		mysqlSource.setMaxActive(dbPropertiesByAuto.maxActive);
		return mysqlSource;
	}
	
	@Bean("oracleSource")
	public DataSource oracleSource() {
		DruidDataSource oracleDataSource=new DruidDataSource();
		oracleDataSource.setDriverClassName(dbPropertiesByAuto.oracledirver);
		oracleDataSource.setUrl(dbPropertiesByAuto.oracleurl);
		oracleDataSource.setUsername(dbPropertiesByAuto.oracleusername);
		oracleDataSource.setPassword(dbPropertiesByAuto.oraclepwd);
		oracleDataSource.setMaxActive(dbPropertiesByAuto.maxActive);
		return oracleDataSource;
	}
	
	@Bean("multipleDataSource")
    @Primary
    public DataSource multipleDataSource(@Qualifier("oracleSource") DataSource oracleSource,
                                         @Qualifier("mysqlSource") DataSource mysqlDataSource){
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map<Object,Object> targetDataSources = new HashMap<Object,Object>();
        targetDataSources.put(DataSourceType.OracleDataSouce,oracleSource);
        targetDataSources.put(DataSourceType.MysqlDataSouce,mysqlDataSource);
        multipleDataSource.setTargetDataSources(targetDataSources);
        multipleDataSource.setDefaultTargetDataSource(mysqlDataSource);
        return multipleDataSource;
    }

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource multipleSource) throws IOException {
		SqlSessionFactoryBean mysql_sqlSession=new SqlSessionFactoryBean();
		mysql_sqlSession.setDataSource(multipleSource);
		Resource configLocation = new ClassPathResource("mybatis-config.xml"); 
        mysql_sqlSession.setConfigLocation(configLocation);
		mysql_sqlSession.setTypeAliasesPackage("com.wang.bean");
		PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		Resource[] mapperLocations=resolver.getResources("classpath:com/wang/mapper/*.xml");
		mysql_sqlSession.setMapperLocations(mapperLocations);
		return mysql_sqlSession;
		
	}
	
	
	@Bean
	@Primary
	public PlatformTransactionManager multipleDataSourceTransactionManager(DataSource multipleDataSource) {
		DataSourceTransactionManager mysqlTransactionManager=new DataSourceTransactionManager() ;
		mysqlTransactionManager.setDataSource(multipleDataSource);
		return mysqlTransactionManager;
	}
	
	
}
