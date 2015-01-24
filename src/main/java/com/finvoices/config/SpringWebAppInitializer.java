package com.finvoices.config;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

public class SpringWebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(ApplicationContextConfig.class);
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "SpringDispatcher", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
	}
	
    // equivalents for <mvc:resources/> tags
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/WEB-INF/resources/**").addResourceLocations("/WEB-INF/resources/**").setCachePeriod(31556926);
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/**").setCachePeriod(31556926);
    }
	

}