package com.mami.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageHelper;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(dataSource);
		return dstm;
	}
	@Bean(name="sqlSessionFactoryBean")
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException{
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource);
		
		//设置mapping的位置
		ssfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/mami/mapping/*.xml"));
		Configuration configuration = new Configuration();
		configuration.setLogImpl(StdOutImpl.class);
		ssfb.setConfiguration(configuration);
		//配置pageHelper分页
		PageHelper ph = new PageHelper();
		Properties p = new Properties();
		p.put("dialect", "mysql"); //数据库类型，缺省为自动判断
		ph.setProperties(p);
		ssfb.setPlugins(new Interceptor[]{ph});
		return ssfb;
	}
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(DataSource dataSource, SqlSessionFactoryBean sqlSessionFactoryBean){
		MapperScannerConfigurer mscf = new MapperScannerConfigurer();
		mscf.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		mscf.setBasePackage("com.mami.mapper");
		return mscf;
	}
}
