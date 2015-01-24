package com.finvoices.controller;

import java.io.File;
import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.dao.BuyerPostalAddressDetailsDAO;
import com.finvoices.dao.DefinitionDetailsDAO;
import com.finvoices.dao.InvoiceDetailsDAO;
import com.finvoices.model.BuyerPartyDetails;
import com.finvoices.model.BuyerPostalAddressDetails;
import com.finvoices.model.DefinitionDetails;
import com.finvoices.model.Finvoice;
import com.finvoices.model.Finvoices;
import com.finvoices.model.InvoiceDetails;
import com.finvoices.service.ResourceReaderService;
import com.finvoices.service.XmlPaserService;

@Controller
public class BuyerPostalAddressDetailsController implements ServletContextAware {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BuyerPostalAddressDetailsController.class);
	
	@Autowired
	private BuyerPostalAddressDetailsDAO buyerPostalAddressDetailsDAO;
	
	//@Autowired
	//private BuyerPartyDetailsDAO buyerPartyDetailsDAO;
	
	@Autowired
	private InvoiceDetailsDAO invoiceDetailsDAO;
	
	@Autowired
	private DefinitionDetailsDAO definitionDetailsDAO;
	
	@Autowired
	private BuyerPartyDetailsDAO buyerPartyDetailsDAO;
	
	@Autowired
	private XmlPaserService xmlPaserService;
	
	@Autowired
	private ResourceReaderService resourceReaderService;	
	
	private ServletContext servletContext;
	
	public void setServletContext(ServletContext pServletContext) {
		servletContext = pServletContext;
	}

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ModelAndView createNewShop() {
		
		//resourceReaderService.ReadResources();
		File xmlFile = new File(servletContext.getRealPath("/WEB-INF/resources/test.xml"));		
		Finvoices fininvoices = xmlPaserService.parseXmlFile(xmlFile);
		/*
		if(fininvoices != null){
			logger.info("parsing succesful! Check frist SellerOrganisationUnitNumbere:  "+fininvoices.getFinvoices().get(0).getSellerOrganisationUnitNumber());
			//boolean success = xmlPaserService.writeInDatabase(fininvoices);
			logger.info("return afer write success = "+success);
		}
		*/
		//Finvoices finVoices = xmlPaserService.getFinvoicesoBbjectFromXml(file);
		 
		//System.out.println(finVoices);
		for(Finvoice finvoice : fininvoices.getFinvoices())
		{
			BuyerPartyDetails byerDetails = finvoice.getBuyerPartyDetails();
			BuyerPostalAddressDetails postalAddrDetails = byerDetails.getBuyerPostalAddressDetails();
			
			byerDetails.setBuyerPostalAddressDetails(postalAddrDetails);
			
			postalAddrDetails.setBuyerPartyDetails(byerDetails);
			buyerPartyDetailsDAO.saveOrUpdate(byerDetails);
			
		}
		
		
		ModelAndView mav = new ModelAndView("test");
		
		
		
		//File file = new File(servletContext.getRealPath("/WEB-INF/resources/20140319_ESPOO228.xml"));		
		//xmlPaserService.WriteInDatabase(file);
/*		
		Set<BuyerPartyDetails> buyerPartyDetailsSet = xmlPaserService.getBuyerPartyDetails();
		Set<InvoiceDetails> invoiceDetailsSet = xmlPaserService.getInvoiceDetailsSet();
		Set<DefinitionDetails> definitionDetailsSet = xmlPaserService.getDefinitionDetailsSet();
		

		for(BuyerPartyDetails buyer : buyerPartyDetailsSet){
			buyerPartyDetailsDAO.saveOrUpdate(buyer);
		}
		
		for(InvoiceDetails invoice : invoiceDetailsSet){
			invoiceDetailsDAO.saveOrUpdate(invoice);
		}
		
		for(DefinitionDetails definitionDetails : definitionDetailsSet){
			definitionDetailsDAO.saveOrUpdate(definitionDetails);
		}
		Finvoices finVoices = xmlPaserService.getFinvoicesoBbjectFromXml(file);
				 
		//System.out.println(finVoices);
		for(Finvoice finvoice : finVoices.getFinvoices())
		{
			BuyerPartyDetails byerDetails = finvoice.getBuyerPartyDetails();
			BuyerPostalAddressDetails postalAddrDetails = byerDetails.getBuyerPostalAddressDetails();
			
			byerDetails.setBuyerPostalAddressDetails(postalAddrDetails);
			
			InvoiceDetails invoiceDetails = finvoice.getInvoiceDetails();
			invoiceDetails.setBuyerPartyDetails(byerDetails);
			
			postalAddrDetails.setBuyerPartyDetails(byerDetails);
			buyerPartyDetailsDAO.saveOrUpdate(byerDetails);
			invoiceDetailsDAO.saveOrUpdate(invoiceDetails);
			for(DefinitionDetails dd : invoiceDetails.getDefinitionDetails())
			{
				dd.setInvoiceDetails(invoiceDetails);
				definitionDetailsDAO.saveOrUpdate(dd);
			}
		}
		*/
		return mav;		
	}	

}
