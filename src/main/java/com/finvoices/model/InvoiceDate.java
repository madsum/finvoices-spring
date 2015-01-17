package com.finvoices.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
                 

/**
 * This class represents InvoiceDate XML node. 
 * 
 * @author Masum Islam
 *
 */
public class InvoiceDate {
	
	public InvoiceDate(){
		
	}
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/
	
	@XmlValue
	private String date;
	private String Format;	


	public String getFormat() {
		return Format;
	}
	
	@XmlAttribute(name="Format") 
	public void setFormat(String format) {
		this.Format = format;
	}
	
	public String getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return "InvoiceDate [date=" + date + ", Format=" + Format + "]";
	}	
}