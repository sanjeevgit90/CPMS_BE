package aurionpro.erp.ipms.billingmgmt.billingschedule;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="billing_schedule", schema = "billingmgmt")
public class BillingSchedule extends JKDEntityAudit{

	@EmbeddedId
    private ScheduleIdentity id;
	
	private Long dateofbilling;
	
	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double amountofbilling;
	
	@Column(length = 500)  
	private String remark;
     
    public BillingSchedule() {
    }
    
    public BillingSchedule(ScheduleIdentity schId,Long dateofbilling, Double amountofbilling, String remark) {
    	this.id = schId;
    	this.dateofbilling = dateofbilling;
    	this.amountofbilling = amountofbilling;
    	this.remark = remark;
    }

	public ScheduleIdentity getId() {
		return id;
	}

	public void setId(ScheduleIdentity id) {
		this.id = id;
	}

	public Long getDateofbilling() {
		return dateofbilling;
	}

	public void setDateofbilling(Long dateofbilling) {
		this.dateofbilling = dateofbilling;
	}

	public Double getAmountofbilling() {
		return amountofbilling;
	}

	public void setAmountofbilling(Double amountofbilling) {
		this.amountofbilling = amountofbilling;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}