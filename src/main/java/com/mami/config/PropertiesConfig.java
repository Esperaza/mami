package com.mami.config;

import java.net.MalformedURLException;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
public class PropertiesConfig {
	@Bean(name="jdbcConfig")
	public PropertiesFactoryBean jdbcConfig() throws MalformedURLException{
		PropertiesFactoryBean pfb = new PropertiesFactoryBean();
		pfb.setLocation(new UrlResource("classpath:jdbc.properties"));
		return pfb;
	}
    @Bean
    public FormattingConversionService conversionService() {

        // Use the DefaultFormattingConversionService but do not register defaults
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        // Ensure @NumberFormat is still supported
        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        // Register date conversion with a specific global format
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyy-MM-dd hh:mm:ss"));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
    
    @Bean
    public MultipartResolver multipartResolver(){
    	CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    	resolver.setMaxUploadSize(104857600L);
    	resolver.setMaxInMemorySize(4096);
    	resolver.setDefaultEncoding("UTF-8");
    	return resolver;
    }
    
}
