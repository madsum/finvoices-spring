package com.finvoices.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.finvoices.service.Utility;

/**
 * This class PaymentTermsDetails InvoiceDetails XML node.
 * 
 * @author Masum Islam
 *
 */
public class PaymentTermsDetails{
	
	public PaymentTermsDetails(){
	}
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/	
	
	private InvoiceDetails parent;
	private InvoiceDueDate invoiceDueDate;
	private String dueDate;

    /**
     * All properties setter and getter 
     */
	@XmlElements({ @XmlElement(name = "InvoiceDueDate", type = InvoiceDueDate.class),
	@XmlElement(name = "PaymentTermsDetails", type = PaymentTermsDetails.class) })
	public void setInvoiceDueDate(InvoiceDueDate duedata) {
		invoiceDueDate = duedata;
		// we have to construct it here because api calls it randomly. So we don't know how to initialize.  
		//parent = new InvoiceDetails();
		
		System.out.println("content: "+duedata.getDate());
		System.out.println("format: "+duedata.getFormat());
		// use service class for string formating
		String format = Utility.getDateFormatString(duedata.getFormat());
		String dateVal = Utility.getDateValue(duedata.getDate());
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		DateTime dt = formatter.parseDateTime(dateVal);
		dueDate = dt.toString("dd-MM-yyyy");	
		
		/*
		String format = getDateFormatString(invoiceDueDate.getFormat());
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			String dateVal = getDateValue(duedata.getDate());
			Date xmlDate = formatter.parse(dateVal);
			DateTime dt = new DateTime(xmlDate);
			dueDate = dt.toString("dd-MM-yyyy");
			System.out.println("ldate: "+dt.toString("dd-MM-yyyy"));
			parent.setInvoiceDueDate(dueDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		*/
	}
	
	public InvoiceDueDate getInvoiceDueDate() {
		return invoiceDueDate;
	}
	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}



	@Override
	public String toString() {
		return "PaymentTermsDetails [invoiceDueDate=" + invoiceDueDate + "]";
	}
	
	   /**
     * We get  Format="CCYYMMDD" from xml. We have to convert it as yyyy-MM-dd. So that we call Java api to convert actual date. 
     * @param formatString
     * @return
     */
    private String getDateFormatString(String formatString){
    		char[] strArr = formatString.toCharArray();
    		StringBuilder str = new StringBuilder();
    		
    		for(int i=0; i<strArr.length; i++)
    		{
    			if (strArr[i] == 'C' || strArr[i] == 'Y')
    			{
    				str.append('y');
    				continue;
    			}
    			
    			if (strArr[i] == 'M')
    			{
    				if(i%4 == 0){
    					str.append("-M");
    				}
    				else{
    					str.append('M');
    				}
    			}
    			else if(strArr[i] == 'D')
    			{
    				if(i%2 == 0 ){
    					str.append("-d");	
    				}
    				else{
    					str.append('d');
    				}
    			}
    			else
    			{
    				str.append(Character.toLowerCase(strArr[i]));
    			}
    		}
    		return str.toString();
    	}
    	
    /**
     * We get date as 20131223. We have to convert as 2013-12-23. So it can used as actual readable date.
     * 
     * @param dateStr
     * @return
     */
    	private String getDateValue(String dateStr){
    		StringBuilder str = new StringBuilder();
    		
    		str.append(dateStr.substring(0, 4));
    		str.append("-");
    		str.append(dateStr.substring(4, 6));
    		str.append("-");
    		str.append(dateStr.substring(6, 8));

    		return str.toString();
    	} 	
}