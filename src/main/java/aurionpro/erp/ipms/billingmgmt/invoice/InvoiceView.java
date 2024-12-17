package aurionpro.erp.ipms.billingmgmt.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="invoiceview", schema = "billingmgmt")
@Immutable
public class InvoiceView{

	@Id
    private Long entityid;

	 private Long customer;
	 
	 private Long projectid;
	 
	 private String customername; 
	
	 private String milestoneno;
	 
	 private String projectname;
		 
	 private String gstno;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	 private Double totalamount;
	 
	 private String invoicestatus;

	 private String pono;
	 
	 private Long customeraddress;

	 private String panno;

	 @Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	 private Double amountwithouttax;

		@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	 private Double amountwithtax;
		
	 private Long invoicedate;

	 private String invoiceno;
	 
	 private String invoiceexcel;

	 private String invoicesupportingdoc;
	 
	 private String accountexcel;
	 
	 private String projectpin;

	 private String address;
	 
	 private Boolean isdeleted;

	public Long getEntityid() {
		return entityid;
	}

	public void setEntityid(Long entityid) {
		this.entityid = entityid;
	}

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getMilestoneno() {
		return milestoneno;
	}

	public void setMilestoneno(String milestoneno) {
		this.milestoneno = milestoneno;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getGstno() {
		return gstno;
	}

	public void setGstno(String gstno) {
		this.gstno = gstno;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
	}

	public String getPono() {
		return pono;
	}

	public void setPono(String pono) {
		this.pono = pono;
	}

	public Long getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(Long customeraddress) {
		this.customeraddress = customeraddress;
	}

	public String getPanno() {
		return panno;
	}

	public void setPanno(String panno) {
		this.panno = panno;
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

	public String getProjectpin() {
		return projectpin;
	}

	public void setProjectpin(String projectpin) {
		this.projectpin = projectpin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	
}