package com.finvoices.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.finvoices.dao.BuyerPartyDetailsDAO;
import com.finvoices.dao.BuyerPostalAddressDetailsDAO;
import com.finvoices.dao.DefinitionDetailsDAO;
import com.finvoices.dao.InvoiceDetailsDAO;
import com.finvoices.exception.BuyerNotFound;
import com.finvoices.model.Buyer;
import com.finvoices.model.BuyerInvoices;
import com.finvoices.model.BuyerPartyDetails;
import com.finvoices.service.ViewControllerService;
import com.finvoices.service.XmlPaserService;

@Controller
@RequestMapping(value="/buyer")
public class BuyerPartyDetailsController {

	private static final Logger logger = LoggerFactory
			.getLogger(BuyerPartyDetailsController.class);

	@Autowired
	private BuyerPostalAddressDetailsDAO buyerPostalAddressDetailsDAO;


	@Autowired
	private InvoiceDetailsDAO invoiceDetailsDAO;

	@Autowired
	private DefinitionDetailsDAO definitionDetailsDAO;

	@Autowired
	private BuyerPartyDetailsDAO buyerPartyDetailsDAO;

	@Autowired
	private XmlPaserService xmlPaserService;
	
	@Autowired
	private ViewControllerService viewControllerService;
	
	//private List<BuyerInvoices> buyerInvoiceList;	
	private HashMap<Integer, List<BuyerInvoices>> buyerInvoiceMap; 

	@RequestMapping(value="/listBuyer", method=RequestMethod.GET)
	public ModelAndView listBuyerPartyDetails() {
		
		List<Buyer> buyerList = new ArrayList<Buyer>();
		buyerInvoiceMap = new HashMap<Integer, List<BuyerInvoices>>();		
		viewControllerService.buyerInvoiceDetails(buyerInvoiceMap, buyerList);
				
		ModelAndView mav = new ModelAndView("buyerList");
		if(buyerList.size() > 0){
			mav = new ModelAndView("buyerList");
			mav.addObject("buyerList", buyerList);
		}
		else{
			mav = new ModelAndView("404");
		}
		return mav;
	}	

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteBuyer(@PathVariable Integer id) throws BuyerNotFound {

		ModelAndView mav = new ModelAndView("index");	
		BuyerPartyDetails buyer = viewControllerService.deleteBuyerAndInovice((int)id);		
		String message = "The buyer "+buyer.getBuyerOrganisationName()+" was successfully deleted.";
		mav.addObject("message", message);
		return mav;
	}
	
	
	@RequestMapping(value="/buyerInvoices/{id}", method=RequestMethod.GET)
	public ModelAndView showBuyerInvoices(@PathVariable Integer id) throws BuyerNotFound {
		ModelAndView mav = new ModelAndView("buyerInvoiceList");
		if (buyerInvoiceMap != null)
		{
			List<BuyerInvoices> buyerInvoiceList = buyerInvoiceMap.get(id);
			mav.addObject("buyerInvoiceList", buyerInvoiceList);
		}
		return mav;
	}	


}
