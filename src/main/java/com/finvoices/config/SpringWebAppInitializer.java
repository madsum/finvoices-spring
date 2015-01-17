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
        
        try
        {
			File rootDir = new File(servletContext.getRealPath("/WEB-INF/resources/20140319_ESPOO228.xml") );
			long l = rootDir.getTotalSpace();
			System.out.println("Size: "+l);
        }
        catch(Exception io)
        {
        	System.out.println("io exception: "+io.getMessage());
        }
	}
	
    // equivalents for <mvc:resources/> tags
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/WEB-INF/**").setCachePeriod(31556926);
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/**").setCachePeriod(31556926);
    }
	

}