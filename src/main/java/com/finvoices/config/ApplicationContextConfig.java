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
import com.finvoices.service.BuyerPartyDetailsService;
import com.finvoices.service.BuyerPostalAddressDetailsService;
import com.finvoices.service.DefinitionDetailsService;
import com.finvoices.service.InvoiceDetailsService;
import com.finvoices.service.ResourceReaderService;
import com.finvoices.service.ResourceReaderServiceImpl;
import com.finvoices.service.XmlPaserService;
import com.finvoices.service.XmlPaserServiceImpl;


@Configuration
@ComponentScan("com.finvoices")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class ApplicationContextConfig {
  
	@Resource
	private Environment env;
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		  registry.addResourceHandler("/assets/**")
		    .addResourceLocations("classpath:/assets/");
		  registry.addResourceHandler("/css/**")
		    .addResourceLocations("/css/");
		  registry.addResourceHandler("/img/**")
		    .addResourceLocations("/img/");
		  registry.addResourceHandler("/resources/**")
		    .addResourceLocations("/resources/");
		}	
	
	@Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxInMemorySize(100000);
        return multipartResolver;
    }
	
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
    
        
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	return properties;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(BuyerPostalAddressDetails.class);
    	sessionBuilder.addAnnotatedClasses(BuyerPartyDetails.class);
    	sessionBuilder.addAnnotatedClasses(InvoiceDetails.class);
    	sessionBuilder.addAnnotatedClasses(DefinitionDetails.class);
    	//sessionBuilder.addAnnotatedClasses(User2.class);
    	//sessionBuilder.addAnnotatedClasses(Shop.class);
    	//sessionBuilder.addAnnotatedClasses(Stock.class);
    	//sessionBuilder.addAnnotatedClasses(StockDetail.class);
    	//sessionBuilder.addAnnotatedClasses(StockDailyRecord.class);
    	
    	return sessionBuilder.buildSessionFactory();
    }
    
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}
	
    /*
    @Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
    	return new UserDAOImpl(sessionFactory);
    }	
	*/
    @Autowired
    @Bean(name = "buyerPostalAddressDetailsDAO")
    public BuyerPostalAddressDetailsDAO getBuyerPostalAddressDetailsDAO(SessionFactory sessionFactory) {
    	return new BuyerPostalAddressDetailsService(sessionFactory);
    }
    
    
    
    @Autowired
    @Bean(name = "buyerPartyDetailsDAO")
    public BuyerPartyDetailsDAO getBuyerPartyDetailsDAO(SessionFactory sessionFactory) {
    	return new BuyerPartyDetailsService(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "invoiceDetailsDAO")
    public InvoiceDetailsDAO getInvoiceDetails(SessionFactory sessionFactory) {
    	return new InvoiceDetailsService(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "definitionDetailsDAO")
    public DefinitionDetailsDAO getDefinitionDetails(SessionFactory sessionFactory) {
    	return new DefinitionDetailsService(sessionFactory);
    }    
    
    
    @Autowired
    @Bean(name = "xmlPaserService")
    public XmlPaserService getXmlPaserService(SessionFactory sessionFactory) {
    	return new XmlPaserServiceImpl();
    }      
    
    @Autowired
    @Bean(name = "resourceReaderService")
    public ResourceReaderService getResourceReaderService(SessionFactory sessionFactory) {
    	return new ResourceReaderServiceImpl();
    }        
    
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(env.getRequiredProperty("message.source.basename"));
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}      
    
    
  /*  
    @Autowired
    @Bean(name = "shopDao")
    public ShopDAO getShopDAO(SessionFactory sessionFactory) {
    	return new ShopService(sessionFactory);
    }       
   
    @Autowired
    @Bean(name = "stockDao")
    public StockDAO getStockDAO(SessionFactory sessionFactory) {
    	return new StockService(sessionFactory);
    }  
    
    @Autowired
    @Bean(name = "StockDetailDao")
    public StockDetailDAO getStockDetailDAO(SessionFactory sessionFactory) {
    	return new StockDetailService(sessionFactory);
    }       
   
    @Autowired
    @Bean(name = "stockDailyRecordDao")
    public StockDailyRecordDAO getStockDailyRecordDAO(SessionFactory sessionFactory) {
    	return new StockDailyRecordService(sessionFactory);
    }   
	*/
  
}
