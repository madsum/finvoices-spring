package com.finvoices.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.exolab.castor.types.DateTime;

import com.finvoices.service.XmlPaserServiceImpl;



/**
 * 
 * This class represents InvoiceDetails XML node. Also map to mysql invoiceDetails table
 * 
 * @author Masum Islam
 *
 */

@Entity
@Table(name = "invoiceDetails")
public class InvoiceDetails implements java.io.Serializable{
	
	public InvoiceDetails(){
		
	}

	/**
	* For annotation all subsequent properties name must be presented as same as in XML file. 
	*/

	// for invoiceDetails.InvoiceDetails_id table primary key column
	int InvoiceDetails_id;
	
	// Xml node and invoiceDetails.AgreementIdentifier column.
	@XmlElement
    private String AgreementIdentifier;
    
	// Xml node and invoiceDetails.InvoiceTypeCode column.
	@XmlElement
    private String InvoiceTypeCode;
    
	// Xml node and invoiceDetails.SellersBuyerIdentifier column.
    @XmlElement
    private String SellersBuyerIdentifier;
	    
	// Xml node and invoiceDetails.OriginCode column.
    @XmlElement
    private String OriginCode;
    
	// Xml node and invoiceDetails.InvoiceNumber column.
    @XmlElement
    private String InvoiceNumber;
    
	// Xml node and invoiceDetails.InvoiceFreeText column.
    @XmlElement(name="InvoiceFreeText")
    private String InvoiceFreeText;
    
	// Xml node and invoiceDetails.InvoiceTypeText column.
    @XmlElement
    private String InvoiceTypeText;	
    
    // escape this property for database column. We map InvoiceDueDate. It has child node InvoiceDueDate.
    @Transient 
    // Xml node.
	@XmlElements({ @XmlElement(name = "PaymentTermsDetails", type = PaymentTermsDetails.class),
	@XmlElement(name = "InvoiceDetails", type = InvoiceDetails.class) })
    private PaymentTermsDetails paymentTermsDetails;
	
    // Escape this node from database because it has attribute and value format. We map value in setter. 
    @Transient  
    // Xml node.
	private InvoiceTotalVatIncludedAmount invoiceTotalVatIncludedAmount;
    
    // 	represent AmountCurrencyIdentifier attribute by it's setter method
	@XmlElements({ @XmlElement(name = "InvoiceTotalVatIncludedAmount", type = InvoiceTotalVatIncludedAmount.class),
	@XmlElement(name = "InvoiceDetails", type = InvoiceDetails.class) })
    void setInvoiceTotalVatIncludedAmount(InvoiceTotalVatIncludedAmount invoiceTotal){
		this.invoiceTotalVatIncludedAmount  = invoiceTotal;
		System.out.print("hit total: "+invoiceTotalVatIncludedAmount.getContent());
		str_InvoiceTotalVatIncludedAmount = invoiceTotalVatIncludedAmount.getContent();

	}
	
	// this property hold actual InvoiceTotalVatIncludedAmount node value. We map in database in getter method.
	private String str_InvoiceTotalVatIncludedAmount; 
	
	// We map in database in getter method. We save date in unix long format to avoid any platform or api limitation. 
	private long long_invoiceDate;
	
	// must annotate in getter method for database
	@Column(name = "InvoiceDate")
	public long getLong_invoiceDate() {
		return long_invoiceDate;
	}

	public void setLong_invoiceDate(long long_invoiceDate) {
		this.long_invoiceDate = long_invoiceDate;
	}
	
	// Escape this property from database column. It has value and attribute. We map value later. 
	@Transient
	private DateTime invoiceDate;	
	
	// represent InvoiceDate node with it's attribute Format by setter.
	@XmlElements({ @XmlElement(name = "InvoiceDate", type = InvoiceDate.class),
	@XmlElement(name = "InvoiceDetails", type = InvoiceDetails.class) })
	public void setInvoiceDate(InvoiceDate billdate) {
		System.out.println("content: "+billdate.getDate());
		System.out.println("format: "+billdate.getFormat());
		// use service class for string formating
		String format = XmlPaserServiceImpl.getDateFormatString(billdate.getFormat());
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			// use service class date
			String dateVal = XmlPaserServiceImpl.getDateValue(billdate.getDate());
			Date xmlDate = formatter.parse(dateVal);
			invoiceDate = new DateTime(xmlDate);
			long_invoiceDate = invoiceDate.toLong();
			System.out.println("long date: "+long_invoiceDate);
			DateTime dt = new DateTime(long_invoiceDate);
			System.out.println("long date: "+dt.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
	}
	
	
	// We map in database in getter method. We save date in unix long format to avoid any platform or api limitation.
	private long invoiceDueDate;
	// invoiceDetails.InvoiceDueDate column. 
	@Column(name = "InvoiceDueDate")
	public long getInvoiceDueDate() {
		return invoiceDueDate;
	}
	
	public void setInvoiceDueDate(long dueDate){
		invoiceDueDate = dueDate;	
	}
	
		
	// This property is for mapping relation with buyerPartyDetails table.
	// invoiceDetails table has many to one relation with buyerPartyDetails table.
	private BuyerPartyDetails buyerPartyDetails;
	
	// invoiceDetails table has many to one relation with buyerPartyDetails table.
	// must annotate in getter method for database
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BuyerPartyDetails_id", nullable = false)
	public BuyerPartyDetails getBuyerPartyDetails() {
		return this.buyerPartyDetails;
	}

	@XmlElements({ @XmlElement(name = "BuyerPartyDetails", type = BuyerPartyDetails.class),
	@XmlElement(name = "Finvoice", type = Finvoice.class) })
	public void setBuyerPartyDetails(BuyerPartyDetails buyerPartyDetails) {
		this.buyerPartyDetails = buyerPartyDetails;
	}
	
	
    //@Transient // escape this 
    // Xml node
    //@XmlElements({ @XmlElement(name = "DefinitionDetails", type = DefinitionDetails.class),
	//@XmlElement(name = "InvoiceDetails", type = InvoiceDetails.class) })
    private Set<DefinitionDetails> definitionDetails = new HashSet<DefinitionDetails>(0);
	
	// invoiceDetails has one to many relation with definitionDetails table. 
	//private Set<DefinitionDetails> definitionDetails = new HashSet<DefinitionDetails>(0);
	// must annotate in getter method for database
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoiceDetails")
	public Set<DefinitionDetails> getDefinitionDetails() {
		return definitionDetails;
	}

    @XmlElements({ @XmlElement(name = "DefinitionDetails", type = DefinitionDetails.class),
	@XmlElement(name = "InvoiceDetails", type = InvoiceDetails.class) })
	public void setDefinitionDetails(Set<DefinitionDetails> definitionDetails) {
		this.definitionDetails = definitionDetails;
	}
	

	/**
     * All properties setter and getter 
     */
	

	// must annotate in getter method for database
	// invoiceDetails.InvoiceDetails_id table primary key.
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "InvoiceDetails_id", unique = true, nullable = false)	
	public int getInvoiceDetails_id() {
		return InvoiceDetails_id;
	}

	public void setInvoiceDetails_id(int invoiceDetails_id) {
		InvoiceDetails_id = invoiceDetails_id;
	}

	// must annotate in getter method for database
	// invoiceDetails.InvoiceTotalVatIncludedAmount column.
	@Column(name = "InvoiceTotalVatIncludedAmount")
	public String getStr_InvoiceTotalVatIncludedAmount() {
		return str_InvoiceTotalVatIncludedAmount;
	}

	public void setStr_InvoiceTotalVatIncludedAmount(
			String str_InvoiceTotalVatIncludedAmount) {
		this.str_InvoiceTotalVatIncludedAmount = str_InvoiceTotalVatIncludedAmount;
	}


	@Transient // ignore this property for database column
	public DateTime getInvoiceDate() {
		return invoiceDate;
	}


	// must annotate in getter method for database
	// invoiceDetails.AgreementIdentifier column.
	@Column(name = "AgreementIdentifier")
    public String getAgreementIdentifier ()
    {
        return AgreementIdentifier;
    }

    public void setAgreementIdentifier (String AgreementIdentifier)
    {
        this.AgreementIdentifier = AgreementIdentifier;
    }
    
	// must annotate in getter method for database
    @Column(name = "InvoiceTypeCode")
    public String getInvoiceTypeCode ()
    {
        return InvoiceTypeCode;
    }

    public void setInvoiceTypeCode (String InvoiceTypeCode)
    {
        this.InvoiceTypeCode = InvoiceTypeCode;
    }

	// must annotate in getter method for database
    @Column(name = "SellersBuyerIdentifier")
    public String getSellersBuyerIdentifier ()
    {
        return SellersBuyerIdentifier;
    }

    public void setSellersBuyerIdentifier (String SellersBuyerIdentifier)
    {
        this.SellersBuyerIdentifier = SellersBuyerIdentifier;
    }
    
	// must annotate in getter method for database
	// invoiceDetails.OriginCode column.
    @Column(name = "OriginCode")
    public String getOriginCode ()
    {
        return OriginCode;
    }

    public void setOriginCode (String OriginCode)
    {
        this.OriginCode = OriginCode;
    }

	// must annotate in getter method for database
	// invoiceDetails.InvoiceNumber column.
    @Column(name = "InvoiceNumber")
    public String getInvoiceNumber ()
    {
        return InvoiceNumber;
    }

    public void setInvoiceNumber (String InvoiceNumber)
    {
        this.InvoiceNumber = InvoiceNumber;
    }

	// must annotate in getter method for database
	// invoiceDetails.InvoiceFreeText column.
    @Column(name = "InvoiceFreeText")
    public String getInvoiceFreeText ()
    {
        return InvoiceFreeText;
    }

    public void setInvoiceFreeText (String InvoiceFreeText)
    {
        this.InvoiceFreeText = InvoiceFreeText;
    }

	// must annotate in getter method for database
	// invoiceDetails.InvoiceTypeText column.
    @Column(name = "InvoiceTypeText")
    public String getInvoiceTypeText ()
    {
        return InvoiceTypeText;
    }

    public void setInvoiceTypeText (String InvoiceTypeText)
    {
        this.InvoiceTypeText = InvoiceTypeText;
    }
    
	@Override
	public String toString() {
		return "InvoiceDetails [AgreementIdentifier=" + AgreementIdentifier
				+ ", invoiceDate=" + invoiceDate.toString() + ", InvoiceTypeCode="
				+ InvoiceTypeCode + ", SellersBuyerIdentifier="
				+ SellersBuyerIdentifier + ", invoiceTotalVatIncludedAmount="
				+ invoiceTotalVatIncludedAmount + ", DefinitionDetails="
				+ definitionDetails + ", OriginCode=" + OriginCode
				+ ", InvoiceNumber=" + InvoiceNumber + ", InvoiceFreeText="
				+ InvoiceFreeText + ", InvoiceTypeText=" + InvoiceTypeText
				+ ", paymentTermsDetails=" + paymentTermsDetails + "]";
	}
}