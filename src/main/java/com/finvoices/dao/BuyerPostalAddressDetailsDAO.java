package com.finvoices.dao;

import java.util.List;

import com.finvoices.model.BuyerPostalAddressDetails;

public interface BuyerPostalAddressDetailsDAO {
	public List<BuyerPostalAddressDetails> list();
	
	public BuyerPostalAddressDetails get(int id);
	
	public void saveOrUpdate(BuyerPostalAddressDetails buyerPostalAddressDetails);
	
	public void delete(int id);
}