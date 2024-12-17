package aurionpro.erp.ipms.billingmgmt.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="invoice_master", schema = "billingmgmt")
public class InvoiceMaster extends JKDEntityAuditWithId{
	
	private Long projectid;
	
	@Column(length = 100)  
	private String pono;
	
	private Long customer;
	
	private Long customeraddress;
	
	@Column(length = 15)  
	private String gstno;
	
	@Column(length = 20)  
	private String panno;
	
	@Column(length = 10)  
	private String milestoneno;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double amountwithouttax;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double amountwithtax;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double totalamount;

	private Long invoicedate;
	
	@Column(length = 50)  
	private String invoiceno;
	
	@Column(length = 50)  
	private String invoicestatus;
	
	@Column(length = 200)  
	private String invoiceexcel;
	
	@Column(length = 200)  
	private String invoicesupportingdoc;
	
	@Column(length = 200)  
	private String accountexcel;
	
	@Column(length = 200)  
	private String invoicesignedexcel;
	    
    public InvoiceMaster() {
    }

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	
	public String getGstno() {
		return gstno;
	}

	public void setGstno(String gstno) {
		this.gstno = gstno;
	}

	public String getPanno() {
		return panno;
	}

	public void setPanno(String panno) {
		this.panno = panno;
	}

	public String getMilestoneno() {
		return milestoneno;
	}

	public void setMilestoneno(String milestoneno) {
		this.milestoneno = milestoneno;
	}

	public String getPono() {
		return pono;
	}

	public void setPono(String pono) {
		this.pono = pono;
	}

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	public Long getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(Long customeraddress) {
		this.customeraddress = customeraddress;
	}

	public Double getAmountwithouttax() {
		return amountwithouttax;
	}

	public void setAmountwithouttax(Double amountwithouttax) {
		this.amountwithouttax = amountwithouttax;
	}

	public Double getAmountwithtax() {
		return amountwithtax;
	}

	public void setAmountwithtax(Double amountwithtax) {
		this.amountwithtax = amountwithtax;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public Long getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(Long invoicedate) {
		this.invoicedate = invoicedate;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public String getInvoiceexcel() {
		return invoiceexcel;
	}

	public void setInvoiceexcel(String invoiceexcel) {
		this.invoiceexcel = invoiceexcel;
	}

	public String getInvoicesupportingdoc() {
		return invoicesupportingdoc;
	}

	public void setInvoicesupportingdoc(String invoicesupportingdoc) {
		this.invoicesupportingdoc = invoicesupportingdoc;
	}

	public String getAccountexcel() {
		return accountexcel;
	}

	public void setAccountexcel(String accountexcel) {
		this.accountexcel = accountexcel;
	}

	public String getInvoicesignedexcel() {
		return invoicesignedexcel;
	}

	public void setInvoicesignedexcel(String invoicesignedexcel) {
		this.invoicesignedexcel = invoicesignedexcel;
	}

	
}