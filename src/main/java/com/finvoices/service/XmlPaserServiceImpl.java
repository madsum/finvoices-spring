package com.finvoices.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.dao.DefinitionDetailsDAO;
import com.finvoices.dao.InvoiceDetailsDAO;
import com.finvoices.model.BuyerPartyDetails;
import com.finvoices.model.BuyerPostalAddressDetails;
import com.finvoices.model.DefinitionDetails;
import com.finvoices.model.Finvoice;
import com.finvoices.model.Finvoices;
import com.finvoices.model.InvoiceDetails;



public class XmlPaserServiceImpl implements XmlPaserService {

	@Autowired
	private InvoiceDetailsDAO invoiceDetailsDAO;

	@Autowired
	private DefinitionDetailsDAO definitionDetailsDAO;

	@Autowired
	private BuyerPartyDetailsDAO buyerPartyDetailsDAO;

	private static final Logger logger = LoggerFactory.getLogger(XmlPaserServiceImpl.class);

	public XmlPaserServiceImpl(){

	}

	private boolean isBuyerExistInDatabase(BuyerPartyDetails byerDetails){
		// check does this buyer already in database
		List<BuyerPartyDetails> buyerList = buyerPartyDetailsDAO.list();
		logger.info("DB buyer list size = "+buyerList.size());
		
		for(BuyerPartyDetails buyer : buyerList){
			if(buyer == null){
				return false;
			}		
			logger.info("BuyerPartyIdentifier from DB ="+buyer.getBuyid());
			if(buyer.getBuyid().compareToIgnoreCase(byerDetails.getBuyerPartyIdentifier()) == 0){
				// buyer found in DB 
				return true;
			}
		}
		return false;
	}

	private boolean isInvoiceExistInDatabase(InvoiceDetails invoice){
		// check does this buyer invoice in database
		List<InvoiceDetails> invoiceList = invoiceDetailsDAO.list();
		logger.info("DB buyer list size = "+invoiceList.size());
		for(InvoiceDetails invo : invoiceList){
			if(invo == null){
				return false;
			}
			logger.info("BuyerPartyIdentifier from DB ="+invo.getInvoiceNumber());
			if(invo.getInvoiceNumber().compareToIgnoreCase(invoice.getInvoiceNumber()) == 0){
				// invoice found in DB 
				return true;
			}
		}
		return false;
	}	

	public boolean writeInDatabase(Finvoices finVoices){


		if( finVoices == null){
			return false;
		}


		for(Finvoice finvoice : finVoices.getFinvoices())
		{
			if( finvoice == null){
				return false;
			}

			BuyerPartyDetails byerDetails = finvoice.getBuyerPartyDetails();
			BuyerPostalAddressDetails postalAddrDetails = null;
			if(byerDetails != null)
			{
				postalAddrDetails = byerDetails.getBuyerPostalAddressDetails();
				if(postalAddrDetails == null)
				{
					return false;
				}
			}
			// setting each other table member to make one 2 one relationship
			byerDetails.setBuyerPostalAddressDetails(postalAddrDetails);
			postalAddrDetails.setBuyerPartyDetails(byerDetails);
			// write only new buyer
			if(!isBuyerExistInDatabase(byerDetails))
			{
				// this buyer is not in DB, so write it.
				buyerPartyDetailsDAO.saveOrUpdate(byerDetails);
			}
			InvoiceDetails invoiceDetails = finvoice.getInvoiceDetails();

			// write new invoice only
			if(!isInvoiceExistInDatabase(invoiceDetails)){
				// this 
				if(invoiceDetails != null){
					invoiceDetails.setBuyerPartyDetails(buyerPartyDetailsDAO.
							getByBuyerPartyIdentifier(byerDetails.getBuyerPartyIdentifier()));
				}

				invoiceDetailsDAO.saveOrUpdate(invoiceDetails);
				for(DefinitionDetails dd : invoiceDetails.getDefinitionDetails())
				{
					if(dd != null){
						dd.setInvoiceDetails(invoiceDetails);
						definitionDetailsDAO.saveOrUpdate(dd);
					}
					else{
						return false;
					}
				}
			}
		}
		return true;
	}

	public Finvoices parseXmlFile(File xmlFile){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Finvoices.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Finvoices fininvoices = (Finvoices) jaxbUnmarshaller.unmarshal(xmlFile);
			logger.info("Print: "+fininvoices);
			//getJsonData(fininvoices);
			return fininvoices;
		} catch (Exception e) {
			System.out.println("Unmarshaller STOP excption: " + e.getMessage());
			return null;
		}	
	}

	public Finvoices getFinvoicesoBbjectFromXml(File xmlFile){

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Finvoices.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Finvoices fininvoices = (Finvoices) jaxbUnmarshaller.unmarshal(xmlFile);
			//fillData(fininvoices);	
			String str = getJsonData(fininvoices);
			System.out.println(str);
			return fininvoices;
		} catch (Exception e) {
			System.out.println("Unmarshaller STOP excption: " + e.getMessage());
			return null;
		}
	}


	public String getJsonData(Finvoices finvoices){
		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string,
			// mapper.writeValue(new File("test.json"), finvoices);
			System.out.println(mapper.writeValueAsString(finvoices));
			return mapper.writeValueAsString(finvoices);

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		catch(Exception ex){

		}

		return "";
	}	
}
