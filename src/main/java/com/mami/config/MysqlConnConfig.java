package com.mami.config;


import java.io.IOException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value="classpath:jdbc.properties",ignoreResourceNotFound=false)

public class MysqlConnConfig {
    @Resource  
    private Environment env; 
	@Bean(name="dataSource")
	public DataSource dataSource(Environment env) throws IOException{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("mysql.driver"));
		ds.setUrl(env.getProperty("mysql.url"));
		ds.setUsername(env.getProperty("mysql.username"));
		ds.setPassword(env.getProperty("mysql.pwd"));
		ds.setInitialSize(Integer.valueOf(env.getProperty("mysql.initSize")));
		ds.setMaxIdle(Integer.valueOf(env.getProperty("mysql.maxSize")));
		return ds;
	}

}
