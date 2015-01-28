package com.finvoices.dao;

import java.util.List;

import com.finvoices.model.InvoiceDetails;

public interface InvoiceDetailsDAO {
	public List<InvoiceDetails> list();	
	public InvoiceDetails get(int id);	
	public void saveOrUpdate(InvoiceDetails user);
	public void delete(int id);
	// gets an InvoiceDetails by given buyer databse id
	public List<InvoiceDetails> getByBuyerId(int id);
}