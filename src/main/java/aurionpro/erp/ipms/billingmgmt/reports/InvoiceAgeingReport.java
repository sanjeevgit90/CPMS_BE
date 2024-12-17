package aurionpro.erp.ipms.billingmgmt.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="invoice_ageing_report", schema = "billingmgmt")
@Immutable
public class InvoiceAgeingReport{

	@Id
    private Long entityid;

	 private Long customer;
	 
	 private Long projectid;
	 
	 private String customername; 
	
	 private String milestoneno;
	 
	 private String projectname;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double totalamount;
	
	 private Long invoicedate;

	  private String projectpin;
     
	  private Boolean isdeleted;
	  
	  @Column(columnDefinition = "interval" )
		private String ageingsince;

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

	public String getProjectpin() {
		return projectpin;
	}

	public void setProjectpin(String projectpin) {
		this.projectpin = projectpin;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getAgeingsince() {
		return ageingsince;
	}

	public void setAgeingsince(String ageingsince) {
		this.ageingsince = ageingsince;
	}

	
	
	
}