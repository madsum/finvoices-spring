package com.finvoices.model;


/**
 * Buyer class combines buyer details and buyer address into on class
 * @author masum
 *
 */
public class Buyer {
		
	public Buyer(){
		
	}
	
	private int id;
	private String name;
	private String identifier;
	private String street;
	private String town;
	private String postCode;
	private int numberOfInvoice;
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getNumberOfInvoice() {
		return numberOfInvoice;
	}
	public void setNumberOfInvoice(int numberOfInvoice) {
		this.numberOfInvoice = numberOfInvoice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


}
