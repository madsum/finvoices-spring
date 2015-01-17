package com.finvoices.service;

import java.io.File;

import com.finvoices.model.Finvoices;

public interface XmlPaserService {

	//public void inputXmlPath(File fileName);
	public Finvoices getFinvoicesoBbjectFromXml(File xmlFile);
	public String getJsonData(Finvoices finvoices);
}
