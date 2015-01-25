package com.finvoices.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.dao.BuyerPostalAddressDetailsDAO;
import com.finvoices.dao.InvoiceDetailsDAO;
import com.finvoices.exception.BuyerNotFound;
import com.finvoices.model.Buyer;
import com.finvoices.model.BuyerInvoices;
import com.finvoices.model.BuyerPartyDetails;
import com.finvoices.model.BuyerPostalAddressDetails;
import com.finvoices.model.InvoiceDetails;



public class ViewControllerServiceImpl implements ViewControllerService {

	private static final Logger logger = LoggerFactory
			.getLogger(ViewControllerServiceImpl.class);

	@Autowired
	private InvoiceDetailsDAO invoiceDetailsDAO;

	@Autowired
	private BuyerPostalAddressDetailsDAO buyerPostalAddressDetailsDAO;

	@Autowired
	private BuyerPartyDetailsDAO buyerPartyDetailsDAO;

	@Autowired
	private XmlPaserService xmlPaserService;

	public void buyerInvoiceDetails(HashMap<Integer, List<BuyerInvoices>> buyerInvoiceMap, List<Buyer> buyerList ){

		List<BuyerPartyDetails> buyerListDetails = buyerPartyDetailsDAO.list();
		List<BuyerPostalAddressDetails> addressList = buyerPostalAddressDetailsDAO.list();
		List<InvoiceDetails> invoiceList = invoiceDetailsDAO.list();

		int numberOfInvoice = 0;
		int index = 0;
		for(BuyerPartyDetails bp : buyerListDetails){
			List<BuyerInvoices> buyerInvoiceList = new ArrayList<BuyerInvoices>();
			logger.info("buyer name: "+bp.getBuyerOrganisationName());
			logger.info("buyer id: "+bp.getBuyerPartyIdentifier());
			Buyer buyer = new Buyer();

			buyer.setId(bp.getBuyerPartyDetails_id());
			buyer.setName(bp.getBuyerOrganisationName());
			buyer.setIdentifier(bp.getBuyid());
			buyer.setStreet(addressList.get(index).getBuyerStreetName());
			buyer.setTown(addressList.get(index).getBuyerTownName());
			buyer.setPostCode(addressList.get(index).getBuyerPostCodeIdentifier());
			logger.info("total invoice: "+invoiceList.size());

			for(InvoiceDetails invoice : invoiceList){
				logger.info("buy id "+bp.getBuyerPartyDetails_id());
				logger.info("invoice buy id "+invoice.getBuyerPartyDetails().getBuyerPartyDetails_id());
				if(bp.getBuyerPartyDetails_id() == invoice.getBuyerPartyDetails().getBuyerPartyDetails_id()){
					// found one invoice 
					numberOfInvoice++;
					BuyerInvoices buyerInvoice = new BuyerInvoices();
					buyerInvoice.setId(invoice.getInvoiceDetails_id());
					buyerInvoice.setBuyerName(bp.getBuyerOrganisationName());
					buyerInvoice.setBuyerIdentifier(bp.getBuyid());
					buyerInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
					buyerInvoice.setInvoiceAmount(invoice.getStr_InvoiceTotalVatIncludedAmount());
					buyerInvoice.setInvoiceFreeText(invoice.getInvoiceFreeText());
					buyerInvoice.setInvoiceDate(invoice.getDateInvoice());
					buyerInvoice.setInvoiceDueDate(invoice.getInvoiceDueDate());
					buyerInvoiceList.add(buyerInvoice);
				}
			}
			buyerInvoiceMap.put(bp.getBuyerPartyDetails_id(), buyerInvoiceList);
			buyer.setNumberOfInvoice(numberOfInvoice);
			buyerList.add(buyer);
			numberOfInvoice=0;
			index++;
		}
	}
	
	public BuyerPartyDetails deleteBuyerAndInovice(int id) throws BuyerNotFound{

		List<InvoiceDetails> invoiceList = invoiceDetailsDAO.list();
		for(InvoiceDetails invoice : invoiceList){
			int buyerId = invoice.getBuyerPartyDetails().getBuyerPartyDetails_id();
			if(buyerId == id){
				invoiceDetailsDAO.delete(invoice.getInvoiceDetails_id());
			}
		}
		BuyerPartyDetails buyer = buyerPartyDetailsDAO.delete(id);
		return  buyer;
	}
}
