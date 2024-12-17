package aurionpro.erp.ipms.ordermgmt.povendorcomparison;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "vendor_comparison", schema = "ordermgmt")
public class VendorComparison extends JKDEntityAuditWithId {

	@Column(name = "purchase_order_no")
	private Long purchaseOrderNo;
	
	@NotBlank(message = "Customer name is required")
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "amount")
	private Double amount;
	
	@Min(value = 1, message = "Delivery date is required")
	@Column(name = "delivery_time")
	private Long deliveryTime;
	
	@Column(name = "payment_terms")
	private String paymentTerms;
	
	@Column(name = "quality")
	private String quality;
	
	@Column(name = "remarks")
	private String remarks;
	
	@ElementCollection
    @CollectionTable(
        name = "vendor_comparison_attachments", schema = "ordermgmt",
        joinColumns = { @JoinColumn(name = "purchaseOrderId") })
    private List<String> attachment;

	public Long getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Long purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
