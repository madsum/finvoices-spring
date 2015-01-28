package com.finvoices.service;

import com.finvoices.model.Finvoices;

public interface DatabaseRreaderWritter {
	public boolean writeInDatabase(Finvoices finVoices);
	public String uploadXmlFile(String fileName, byte[] bytes);

}
