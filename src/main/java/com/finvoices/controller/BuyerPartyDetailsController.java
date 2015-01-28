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
@RequestMapping(value="/buyer")
public class BuyerPartyDetailsController {

	private static final Logger logger = LoggerFactory
			.getLogger(BuyerPartyDetailsController.class);

	@Autowired
	private ViewControllerService viewControllerService;

	private HashMap<Integer, List<BuyerInvoices>> buyerInvoiceMap; 

	private List<Buyer> buyerList;
	
	public BuyerPartyDetailsController(){
		buyerList = new ArrayList<Buyer>();
		buyerInvoiceMap = new HashMap<Integer, List<BuyerInvoices>>();	
	}
	
	@RequestMapping(value="/listBuyer", method=RequestMethod.GET)
	public ModelAndView listBuyerPartyDetails() {

		buyerInvoiceMap.clear();
		buyerList.clear();
		
		viewControllerService.buyerInvoiceDetails(buyerInvoiceMap, buyerList );

		ModelAndView mav = new ModelAndView("buyerList");
		if(buyerList.size() > 0){
			mav = new ModelAndView("buyerList");
			logger.info("Got buyerList now retunr buyerList.jsp ");
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
		logger.info("Return buyerInvoiceList.jsp ");
		return mav;
	}

	@RequestMapping(value="/listBuyer/{xmlFile}", method=RequestMethod.GET)
	public ModelAndView listBuyerByFile(@PathVariable("xmlFile") String xmlFile) throws BuyerNotFound {

		buyerInvoiceMap.clear();
		buyerList.clear();

		viewControllerService.buyerInvoiceDetailsByFile(buyerInvoiceMap, buyerList, xmlFile);

		ModelAndView mav = new ModelAndView("FilebuyerList");
		if(buyerList.size() > 0){
			mav = new ModelAndView("FilebuyerList");
			logger.info("Got buyerList now retunr FilebuyerList.jsp ");
			mav.addObject("buyerList", buyerList);
		}
		else{
			mav = new ModelAndView("404");
		}
		return mav;
	}
}
