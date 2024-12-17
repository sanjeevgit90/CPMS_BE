package aurionpro.erp.ipms.ordermgmt.pofulfilmentdelay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "po_fulfilment_delay", schema = "ordermgmt")
public class FulfilmentDelay extends JKDEntityAuditWithId {

	@Column(name = "purchase_order_no")
	private Long purchaseOrderNo;
	
	@Min(value = 1, message = "Fulfilment date is required")
	@Column(name = "fulfilment_date")
	private Long fulfilmentDate;
	
	@NotBlank(message = "Delay reason is required")
	@Column(name = "delay_reason")
	private String delayReason;
	
	@Min(value = 1, message = "Next delivery date is required")
	@Column(name = "nextdelivery_date")
	private Long nextdeliveryDate;
	
	@Column(name = "delay_category")
	private String delayCategory;
	
	/*@Column(name = "po_number") //not needed
	private String poNo;*/

	public Long getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Long purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Long getFulfilmentDate() {
		return fulfilmentDate;
	}

	public void setFulfilmentDate(Long fulfilmentDate) {
		this.fulfilmentDate = fulfilmentDate;
	}

	public String getDelayReason() {
		return delayReason;
	}

	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}

	public Long getNextdeliveryDate() {
		return nextdeliveryDate;
	}

	public void setNextdeliveryDate(Long nextdeliveryDate) {
		this.nextdeliveryDate = nextdeliveryDate;
	}

	public String getDelayCategory() {
		return delayCategory;
	}

	public void setDelayCategory(String delayCategory) {
		this.delayCategory = delayCategory;
	}
}