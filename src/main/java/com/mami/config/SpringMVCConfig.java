package com.mami.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.mami.controller")
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/jsp/");
		irvr.setSuffix(".jsp");
		registry.viewResolver(irvr);
	}


	// 设置StringHttpMessageConverter
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		HttpMessageConverter<Object> json = new MappingJackson2HttpMessageConverter();
		HttpMessageConverter<String> shm = new StringHttpMessageConverter(Charset.forName("utf-8"));
		converters.add(shm);
		converters.add(json);
	}

}
