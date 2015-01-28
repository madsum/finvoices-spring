package com.finvoices.dao;

import java.util.List;

import com.finvoices.exception.BuyerNotFound;
import com.finvoices.model.BuyerPartyDetails;


public interface BuyerPartyDetailsDAO {
	// list all buyer
	public List<BuyerPartyDetails> list();
	// list buyer by file name
	public List<BuyerPartyDetails> listByfileName(String fileName);
	// find only one buyer by database id
	public BuyerPartyDetails get(int id);
	// find buyer by buyerPartyIdentifier string from xml
	public BuyerPartyDetails getByBuyerPartyIdentifier(String buyer_id);
	// save or update a buyer
	public void saveOrUpdate(BuyerPartyDetails buyerPartyDetails);
	// delete a the buyer with given database id.
	public BuyerPartyDetails delete(int id) throws BuyerNotFound;
	// find a buyer by name
	public BuyerPartyDetails getByBuyerPartyByName(String name);
}
