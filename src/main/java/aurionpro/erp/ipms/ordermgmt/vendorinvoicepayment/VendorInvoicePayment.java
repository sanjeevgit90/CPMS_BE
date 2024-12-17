package aurionpro.erp.ipms.ordermgmt.vendorinvoicepayment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "vendor_invoice_payment", schema = "ordermgmt")
public class VendorInvoicePayment extends JKDEntityAuditWithId {

	@Min(value = 1, message = "Payment date is required")
	@Column(name = "payment_date")
	private Long paymentDate;
	
	@Min(value = 1, message = "Amount is required")
	@Column(name = "amount", columnDefinition = "numeric(15,2)")
	private Double amount;
	
	@NotBlank(message = "Payment reference is required")
	@Column(name = "payment_reference")
	private String paymentReference;
	
	@NotBlank(message = "Remark is required")
	@Column(name = "remark")
	private String remark;
	
	@Min(value = 1, message = "Prs id is required")
	@Column(name = "prs_id")
	private Long prsId;
	
	@Column(name = "approved_by")
	private String approvedBy;
	
	@Column(name = "approval_status")
	private String approvalStatus;
	
	@Column(name = "status")
	private String status;

	public Long getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Long paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getPrsId() {
		return prsId;
	}

	public void setPrsId(Long prsId) {
		this.prsId = prsId;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
