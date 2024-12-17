package aurionpro.erp.ipms.billingmgmt.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="billingcomparsion", schema = "billingmgmt")
@Immutable
public class BillingComparsion{

	@Id
	private String invoiceno;
	
	private Long projectid;
	
	private String milestoneno;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double amountofbilling;
	
	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double totalamount;
	
	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double difference;

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public String getMilestoneno() {
		return milestoneno;
	}

	public void setMilestoneno(String milestoneno) {
		this.milestoneno = milestoneno;
	}

	public Double getAmountofbilling() {
		return amountofbilling;
	}

	public void setAmountofbilling(Double amountofbilling) {
		this.amountofbilling = amountofbilling;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public Double getDifference() {
		return difference;
	}

	public void setDifference(Double difference) {
		this.difference = difference;
	}

	
	
}