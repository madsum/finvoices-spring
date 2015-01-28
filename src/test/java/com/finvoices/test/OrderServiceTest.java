package com.finvoices.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.finvoices.config.ApplicationContextConfig;
import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.serviceImpl.BuyerPartyDetailsServiceImpl;

//@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from the static inner ContextConfiguration class
@ContextConfiguration(classes = { ApplicationContextConfig.class }, loader = AnnotationConfigContextLoader.class)
public class OrderServiceTest {

	@Configuration
	static class ContextConfiguration {

		// this bean will be injected into the OrderServiceTest class
		@Bean
		public BuyerPartyDetailsDAO orderService() {
			BuyerPartyDetailsDAO orderService = new BuyerPartyDetailsServiceImpl();
			// set properties, etc.
			return orderService;
		}
	}

	@Autowired
	private BuyerPartyDetailsDAO orderService;

	@Test
	public void testOrderService() {
		// test the orderService
		Assert.assertTrue(true);
		System.out.println("Null check: "+orderService.toString());

	}
}