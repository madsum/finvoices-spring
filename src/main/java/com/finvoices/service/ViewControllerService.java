package com.finvoices.service;

import java.util.HashMap;
import java.util.List;

import com.finvoices.exception.BuyerNotFound;
import com.finvoices.model.Buyer;
import com.finvoices.model.BuyerInvoices;
import com.finvoices.model.BuyerPartyDetails;

public interface ViewControllerService {
	public void buyerInvoiceDetails(HashMap<Integer, List<BuyerInvoices>> buyerInvoiceMap, List<Buyer> buyerList );
	public BuyerPartyDetails deleteBuyerAndInovice(int id) throws BuyerNotFound;
}
