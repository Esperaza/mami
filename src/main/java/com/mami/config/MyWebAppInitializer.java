package com.mami.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("webAppRootKey", "webapp.root");
		super.onStartup(servletContext);
	    servletContext
	            .addFilter( "shiroFilter", new DelegatingFilterProxy( "shiroFilter") )
	            .addMappingForUrlPatterns( null, false, "/*" );
	    servletContext
	    		.addFilter("encodingFilter", new CharactorEncodingFilter())
	    		.addMappingForUrlPatterns( null, false, "/*" );
	}
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SpringMVCConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
}
