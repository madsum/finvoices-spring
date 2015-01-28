package com.finvoices.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.finvoices.dao.BuyerPartyDetailsDAO;

//@Test
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:TestAppContext.xml" })
public class BuyerPartyDetailsServiceImplTest extends AbstractTestNGSpringContextTests{

	@Autowired(required=true)
	@Qualifier("buyerPartyDetails")
	private BuyerPartyDetailsDAO buyerPartyDetails;

	@Test
	void test(){
		Assert.assertTrue(true);
		System.out.println("Test 1 called");
		System.out.println("check null: "+buyerPartyDetails.toString());
	}
	
	@BeforeTest
	public void initFramework() 
	{
		System.out.println("Test 1 before calledß");
		System.out.println("check null: "+buyerPartyDetails.toString());
		Assert.assertTrue(true);
	}	
}
