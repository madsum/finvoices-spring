package com.finvoices.service;

import java.io.File;
import java.util.Set;

import com.finvoices.model.BuyerPartyDetails;
import com.finvoices.model.DefinitionDetails;
import com.finvoices.model.Finvoices;
import com.finvoices.model.InvoiceDetails;

public interface XmlPaserService {

	public Finvoices parseXmlFile(File xmlFile);
	//public Finvoices getFinvoicesoBbjectFromXml(File xmlFile);
	public String getJsonData(Finvoices finvoices);
	//public Finvoices parseXml(File xmlFile);
	//public Set<DefinitionDetails> getDefinitionDetailsSet();
	//public Set<InvoiceDetails> getInvoiceDetailsSet();
	//public Set<BuyerPartyDetails> getBuyerPartyDetails();
	public boolean writeInDatabase(Finvoices finVoices);
}
