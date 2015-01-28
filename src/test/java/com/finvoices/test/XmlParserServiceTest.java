package com.finvoices.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.finvoices.model.Finvoices;
import com.finvoices.service.DatabaseRreaderWritter;
import com.finvoices.service.XmlPaserService;
import com.finvoices.serviceImpl.DatabaseRreaderWritterImpl;
import com.finvoices.serviceImpl.XmlPaserServiceImpl;

public class XmlParserServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(XmlParserServiceTest.class);

	private XmlPaserService xmlPaserService;	
	
	private DatabaseRreaderWritter databaseRreaderWritter;
	
	private String xmlFile;

	/**
	 * The annotated method will be run before any test method belonging to the classes 
	 * inside the <test> tag is run.
	 * 
	 */

	@BeforeTest
	@Parameters( {"config-file"} )
	public void initFramework(@Optional("src/test/resources/test.xml") String file) 
	{
		xmlPaserService = new XmlPaserServiceImpl();
		databaseRreaderWritter = new DatabaseRreaderWritterImpl();
		xmlFile = file;
	}
	public void beforeTest() {
		logger.info("BeforeTest method is run...");
	}

	/**
	 * The annotated method will be run before the first test method in the current class 
	 * is invoked. 
	 * 
	 */
	@BeforeClass
	public void beforeClass() throws IOException{
	}
	
	/**
	 * The annotated method will be run before each test method. 
	 * 
	 */
	@BeforeMethod
	public void beforeMethod() {
	}	

	/**
	 * Tests the process of adding user
	 * 
	 */
	@Test
	public void testParseXmlFile() {
		Assert.assertTrue(xmlPaserService != null);
		Finvoices fb = xmlPaserService.parseXmlFile(xmlFile);
		Assert.assertTrue(fb != null);
	}
	
	@Test
	public void testUploadXmlFile() {
		FileInputStream stream = null;
		try {
			File fh = new File(xmlFile);
			stream = new FileInputStream(fh);
			byte[] bytes = new byte[(int)fh.length()];
			stream.read(bytes);
			String xmlFile = databaseRreaderWritter.uploadXmlFile("test.xml",bytes);
			stream.close();
			// Check for file uploading.
			Assert.assertTrue(!xmlFile.isEmpty());
			Assert.assertTrue(Files.exists(Paths.get(xmlFile)));
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}
}
