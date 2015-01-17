package com.finvoices.dao;

import java.util.List;

import com.finvoices.model.BuyerPartyDetails;


public interface BuyerPartyDetailsDAO {
	public List<BuyerPartyDetails> list();
	
	public BuyerPartyDetails get(int id);
	
	public void saveOrUpdate(BuyerPartyDetails buyerPartyDetails);
	
	public void delete(int id);
}
