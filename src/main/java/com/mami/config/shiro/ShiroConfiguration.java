package com.mami.config.shiro;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
/**
 * shiro拦截器配置
 * @author jjweng@ibenben.com
 *
 */
@Configuration
public class ShiroConfiguration
{

    private static final Map<String, String> FILTER_CHAIN_DEFINITION_MAP = new LinkedHashMap<>();

    @Autowired
    DataSource dataSource;
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }
//
//    @Bean(name = "jdbcRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public JdbcRealm jdbcRealm()
//    {
//        JdbcRealm jdbcRealm = new JdbcRealm();
//        HashedCredentialsMatcher hashedCredentialMatcher = new HashedCredentialsMatcher("MD5");
//        hashedCredentialMatcher.setHashIterations(1);
//        jdbcRealm.setCredentialsMatcher(hashedCredentialMatcher);
//        jdbcRealm.init();
//        jdbcRealm.setDataSource(dataSource);
//        return jdbcRealm;
//    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager()
    {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setCacheManager(cacheManager);
//        defaultWebSecurityManager.setRealm(jdbcRealm());
        
        return defaultWebSecurityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());

        shiroFilterFactoryBean.setLoginUrl("login");
        shiroFilterFactoryBean.setUnauthorizedUrl("login");
        FILTER_CHAIN_DEFINITION_MAP.put("/admin/login", "anon");
        FILTER_CHAIN_DEFINITION_MAP.put("/admin/logout", "anon");
        FILTER_CHAIN_DEFINITION_MAP.put("/static/**", "anon");
        FILTER_CHAIN_DEFINITION_MAP.put("/user/**", "anon");
        FILTER_CHAIN_DEFINITION_MAP.put("/**", "anon");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(FILTER_CHAIN_DEFINITION_MAP);
        Map<String, Filter> servletFilters = new HashMap<>();
        servletFilters.put("anon", new AnonymousFilter());
        servletFilters.put("authc", new FormAuthenticationFilter());
        servletFilters.put("roles", new RolesAuthorizationFilter());
        servletFilters.put("user", new UserFilter());
        shiroFilterFactoryBean.setFilters(servletFilters);

        return shiroFilterFactoryBean;
    }
}
