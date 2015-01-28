package com.finvoices.service;

import com.finvoices.model.Finvoices;

public interface XmlPaserService {
	public Finvoices parseXmlFile(String xmlFile);
	public String getJsonData(Finvoices finvoices);
}