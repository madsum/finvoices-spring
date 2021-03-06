package com.finvoices.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.dao.BuyerPostalAddressDetailsDAO;
import com.finvoices.dao.DefinitionDetailsDAO;
import com.finvoices.dao.InvoiceDetailsDAO;
import com.finvoices.model.BuyerPartyDetails;
import com.finvoices.model.BuyerPostalAddressDetails;
import com.finvoices.model.DefinitionDetails;
import com.finvoices.model.InvoiceDetails;
import com.finvoices.service.DatabaseRreaderWritter;
import com.finvoices.service.ViewControllerService;
import com.finvoices.service.XmlPaserService;
import com.finvoices.serviceImpl.BuyerPartyDetailsServiceImpl;
import com.finvoices.serviceImpl.BuyerPostalAddressDetailsServiceImpl;
import com.finvoices.serviceImpl.DatabaseRreaderWritterImpl;
import com.finvoices.serviceImpl.DefinitionDetailsServiceImpl;
import com.finvoices.serviceImpl.InvoiceDetailsServiceImpl;
import com.finvoices.serviceImpl.ViewControllerServiceImpl;
import com.finvoices.serviceImpl.XmlPaserServiceImpl;


@Configuration
@ComponentScan("com.finvoices")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class ApplicationContextConfig {
  
	@Resource
	private Environment env;
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		  registry.addResourceHandler("/css/**")
		    .addResourceLocations("/css/");
		  registry.addResourceHandler("/resources/**")
		    .addResourceLocations("/resources/");
		}	
	
	// for file upload
	@Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxInMemorySize(100000);
        return multipartResolver;
    }
	
	// for view resolver
	@Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    
    // dataSource for mysql
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/finvoices");
    	dataSource.setUsername("dev");
    	dataSource.setPassword("dev");    	
    	return dataSource;
    }
    
    // hibernate config    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	return properties;
    }
    
    // hibernate session Factory
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(BuyerPostalAddressDetails.class);
    	sessionBuilder.addAnnotatedClasses(BuyerPartyDetails.class);
    	sessionBuilder.addAnnotatedClasses(InvoiceDetails.class);
    	sessionBuilder.addAnnotatedClasses(DefinitionDetails.class);
    	return sessionBuilder.buildSessionFactory();
    }
    
    // transaction Manager
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);
		return transactionManager;
	}
	
    
    @Autowired
    @Bean(name = "buyerPostalAddressDetailsDAO")
    public BuyerPostalAddressDetailsDAO getBuyerPostalAddressDetailsDAO(SessionFactory sessionFactory) {
    	return new BuyerPostalAddressDetailsServiceImpl(sessionFactory);
    }
      
    @Autowired
    @Bean(name = "buyerPartyDetailsDAO")
    public BuyerPartyDetailsDAO getBuyerPartyDetailsDAO(SessionFactory sessionFactory) {
    	return new BuyerPartyDetailsServiceImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "invoiceDetailsDAO")
    public InvoiceDetailsDAO getInvoiceDetails(SessionFactory sessionFactory) {
    	return new InvoiceDetailsServiceImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "definitionDetailsDAO")
    public DefinitionDetailsDAO getDefinitionDetails(SessionFactory sessionFactory) {
    	return new DefinitionDetailsServiceImpl(sessionFactory);
    }    
    
    
    @Autowired
    @Bean(name = "xmlPaserService")
    public XmlPaserService getXmlPaserService(SessionFactory sessionFactory) {
    	return new XmlPaserServiceImpl();
    }

    @Autowired
    @Bean(name = "databaseRreaderWritter")
    public DatabaseRreaderWritter getXmlDatabaseRreaderWritter(SessionFactory sessionFactory) {
    	return new DatabaseRreaderWritterImpl();
    }
    
    
    @Autowired
    @Bean(name = "viewControllerService")
    public ViewControllerService getViewControllerService(SessionFactory sessionFactory) {
    	return new ViewControllerServiceImpl();
    }    
    
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(env.getRequiredProperty("message.source.basename"));
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}        
}
