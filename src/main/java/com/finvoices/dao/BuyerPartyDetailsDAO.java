package com.finvoices.dao;

import java.util.List;

import com.finvoices.exception.BuyerNotFound;
import com.finvoices.model.BuyerPartyDetails;


public interface BuyerPartyDetailsDAO {
	public List<BuyerPartyDetails> list();
	
	public BuyerPartyDetails get(int id);
	
	public BuyerPartyDetails getByBuyerPartyIdentifier(String buyer_id);
	
	public void saveOrUpdate(BuyerPartyDetails buyerPartyDetails);
	
	public BuyerPartyDetails delete(int id) throws BuyerNotFound;
}
