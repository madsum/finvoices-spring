package com.finvoices.model;

/**
 * BuyerInvoices combines basic number information and invoices information together
 * @author masum
 */
public class BuyerInvoices {
	
	public BuyerInvoices(){
		
	}
	
	private int id;
	private String buyerName;
	private String buyerIdentifier;
	private String invoiceNumber;
	private String invoiceDate;
	private String invoiceAmount;
	private String invoiceDueDate;
	private String invoiceFreeText;
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getInvoiceDueDate() {
		return invoiceDueDate;
	}
	public void setInvoiceDueDate(String invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}
	public String getInvoiceFreeText() {
		return invoiceFreeText;
	}
	public void setInvoiceFreeText(String invoiceFreeText) {
		this.invoiceFreeText = invoiceFreeText;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerIdentifier() {
		return buyerIdentifier;
	}
	public void setBuyerIdentifier(String buyerIdentifier) {
		this.buyerIdentifier = buyerIdentifier;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
}
