package com.finvoices.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * 
 * This class represents DefinitionDetails XML node.
 * 
 * @author Masum Islam
 *
 */

@Entity
@Table(name = "definitionDetails")
public class DefinitionDetails implements java.io.Serializable{
	
	public DefinitionDetails(){
		
	}
	
	
	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/	
	
	int DefinitionDetails_id;
	
	String definitionHeaderText_attribute;
	
	String definitionHeaderText_value;
	
	@XmlElement
    private String DefinitionValue;
	
    // It represents DefinitionHeaderText with attribute by setter
    @Transient // escape this property for database
    private DefinitionHeaderText DefinitionHeaderText;
    
    @Transient // escape this property for database
	@XmlElements({ @XmlElement(name = "DefinitionHeaderText", type = DefinitionHeaderText.class),
	@XmlElement(name = "DefinitionDetails", type = DefinitionDetails.class) })
    public void setDefinitionHeaderText (DefinitionHeaderText DefinitionHeaderText)
    {
        this.DefinitionHeaderText = DefinitionHeaderText;
        definitionHeaderText_attribute = DefinitionHeaderText.getDefinitionCode();
        definitionHeaderText_value = DefinitionHeaderText.getContent();
        System.out.println("DefinitionHeaderText: "+DefinitionHeaderText.getContent());
   }
    
    
    
    private InvoiceDetails invoiceDetails;
    
	// must annotate in getter method for database
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InvoiceDetails_id", nullable = false)
	public InvoiceDetails getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(InvoiceDetails invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}    
    
	// must annotate in getter method for database
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DefinitionDetails_id", unique = true, nullable = false)	
    public int getDefinitionDetails_id() {
		return DefinitionDetails_id;
	}

	public void setDefinitionDetails_id(int definitionDetails_id) {
		DefinitionDetails_id = definitionDetails_id;
	}    
    
// must annotate in getter method for database
	@Column(name = "DefinitionValue")   
   public String getDefinitionValue() {
		return DefinitionValue;
	}

	
	public void setDefinitionValue(String definitionValue) {
		DefinitionValue = definitionValue;
	}
	
	// must annotate in getter method for database
	@Column(name = "DefinitionHeaderText_attribute")
	public String getDefinitionHeaderText_attribute() {
		return definitionHeaderText_attribute;
	}

	public void setDefinitionHeaderText_attribute(
			String definitionHeaderText_attribute) {
		this.definitionHeaderText_attribute = definitionHeaderText_attribute;
	}

	// must annotate in getter method for database
	@Column(name = "DefinitionHeaderText_value")
	public String getDefinitionHeaderText_value() {
		return definitionHeaderText_value;
	}

	public void setDefinitionHeaderText_value(String definitionHeaderText_value) {
		this.definitionHeaderText_value = definitionHeaderText_value;
	}	



@Override
   public String toString() {
	   return "DefinitionDetails [DefinitionHeaderText="
		+ DefinitionHeaderText + ", DefinitionValue=" + DefinitionValue
		+ "]";
	}
}