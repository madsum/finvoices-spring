package com.finvoices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * This class represents BuyerPostalAddressDetails XML node. Also map to mysql buyerPostalAddressDetails table
 * 
 * @author Masum Islam
 *
 */

@Entity
@Table(name = "buyerPostalAddressDetails")
public class BuyerPostalAddressDetails{
	
	public BuyerPostalAddressDetails(){
		
	}
		
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/

	int BuyerPartyDetails_id;
	
	@XmlElement
	private String BuyerTownName;
	
	@XmlElement
	private String BuyerPostCodeIdentifier;

	@XmlElement
    private String BuyerStreetName;
	
	private BuyerPartyDetails buyerPartyDetails;


	/**
     * All properties setter and getter 
     */
	
	// must annotate in getter method for database
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
    public BuyerPartyDetails getBuyerPartyDetails() {
		return buyerPartyDetails;
	}

	public void setBuyerPartyDetails(BuyerPartyDetails buyerPartyDetails) {
		this.buyerPartyDetails = buyerPartyDetails;
	}	
	
	// must annotate in getter method for database
	// buyerPostalAddressDetails table has one to one relation with buyerPartyDetails table.
	// buyerPostalAddressDetails.BuyerPartyDetails_id table column.	
	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "buyerPartyDetails"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "BuyerPartyDetails_id", unique = true, nullable = false)
	public int getBuyerPartyDetails_id() {
		return BuyerPartyDetails_id;
	}

	public void setBuyerPartyDetails_id(int buyerPartyDetails_id) {
		BuyerPartyDetails_id = buyerPartyDetails_id;
	}
	
	// must annotate in getter method for database
	// buyerPostalAddressDetails.BuyerTownName table column.
	@Column(name = "BuyerTownName")
	public String getBuyerTownName() {
		return BuyerTownName;
	}

	public void setBuyerTownName(String buyerTownName) {
		BuyerTownName = buyerTownName;
	}
	
	// must annotate in getter method for database
	// buyerPostalAddressDetails.BuyerPostCodeIdentifier table column.	
	@Column(name = "BuyerPostCodeIdentifier")
	public String getBuyerPostCodeIdentifier() {
		return BuyerPostCodeIdentifier;
	}

	public void setBuyerPostCodeIdentifier(String buyerPostCodeIdentifier) {
		BuyerPostCodeIdentifier = buyerPostCodeIdentifier;
	}

	public String getBuyerStreetName() {
		return BuyerStreetName;
	}

	// must annotate in getter method for database
	// buyerPostalAddressDetails.BuyerStreetName table column.	
	@Column(name = "BuyerStreetName")
	public void setBuyerStreetName(String buyerStreetName) {
		BuyerStreetName = buyerStreetName;
	}

	@Override
	public String toString() {
		return "BuyerPostalAddressDetails [BuyerPartyDetails_id="
				+ BuyerPartyDetails_id + ", BuyerTownName=" + BuyerTownName
				+ ", BuyerPostCodeIdentifier=" + BuyerPostCodeIdentifier
				+ ", BuyerStreetName=" + BuyerStreetName
				+ ", buyerPartyDetails=" + buyerPartyDetails + "]";
	}
	
}
