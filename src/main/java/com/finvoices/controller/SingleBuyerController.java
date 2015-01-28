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

import com.finvoices.exception.BuyerNotFound;
import com.finvoices.model.Buyer;
import com.finvoices.model.BuyerInvoices;
import com.finvoices.model.BuyerPartyDetails;
import com.finvoices.service.ViewControllerService;

@Controller
@RequestMapping(value="/singleBuyer")
public class SingleBuyerController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserInputController.class);

	@Autowired
	private ViewControllerService viewControllerService;	
	
	private HashMap<Integer, List<BuyerInvoices>> buyerInvoiceMap; 
	
	public SingleBuyerController() {
		buyerInvoiceMap = new HashMap<Integer, List<BuyerInvoices>>();	
	}
	
	@RequestMapping(value="/{buyerName}", method=RequestMethod.GET)
	public ModelAndView processSubmit(@PathVariable("buyerName") String buyerName){

		ModelAndView mav = null;
		Buyer buyer = viewControllerService.findUserByName(buyerName, buyerInvoiceMap);
		if( buyer != null){
			logger.info("No null");
			mav = new ModelAndView("Singlerbuyer");
			mav.addObject("buyer", buyer);
		}
		else{
			mav = new ModelAndView("404");
			mav.addObject("message", "No buyer found named "+buyerName);
		}
		return mav;
	}

	@RequestMapping(value="/buyerInvoices/{id}", method = RequestMethod.GET)
	public ModelAndView getSingelBuyerInvoice(@PathVariable Integer id){
		ModelAndView mav = new ModelAndView("SinglebuyerInvoiceList");
		if (buyerInvoiceMap != null){
			List<BuyerInvoices> buyerInvoiceList = new ArrayList<BuyerInvoices>();
			buyerInvoiceList = buyerInvoiceMap.get(id);
			mav = new ModelAndView("SinglebuyerInvoiceList");
			mav.addObject("buyerInvoiceList", buyerInvoiceList);
		}else{
			mav = new ModelAndView("404");
			mav.addObject("message", "No invoice found");		
		}
		
		logger.info("Return buyerInvoiceList.jsp ");
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
}
