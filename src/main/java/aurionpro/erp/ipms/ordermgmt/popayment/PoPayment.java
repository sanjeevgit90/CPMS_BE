package aurionpro.erp.ipms.ordermgmt.popayment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "po_payment", schema = "ordermgmt")
public class PoPayment extends JKDEntityAuditWithId {

	@Column(name = "purchase_order_no")
	private Long purchaseOrderNo;
	
	@Min(value = 1, message = "Schedule date is required")
	@Column(name = "schedule_date")
	private Long scheduleDate;
	
	@Column(name = "amount", columnDefinition = "numeric(15,2)")
	private Double amount;
	
	@Column(name = "payment_date")
	private Long paymentDate;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "dd_number")
	private String ddNumber;
	
	@Column(name = "dd_bank")
	private String ddBank;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "invoice_date")
	private Long invoiceDate;
	
	@Column(name = "cashback_received", columnDefinition = "numeric(15,2)")
	private Double cashBackReceived;
	
	@Column(name = "interest_paid", columnDefinition = "numeric(15,2)")
	private Double interestPaid;

	public Long getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Long purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Long getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Long scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Long paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDdNumber() {
		return ddNumber;
	}

	public void setDdNumber(String ddNumber) {
		this.ddNumber = ddNumber;
	}

	public String getDdBank() {
		return ddBank;
	}

	public void setDdBank(String ddBank) {
		this.ddBank = ddBank;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Long getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Long invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Double getCashBackReceived() {
		return cashBackReceived;
	}

	public void setCashBackReceived(Double cashBackReceived) {
		this.cashBackReceived = cashBackReceived;
	}

	public Double getInterestPaid() {
		return interestPaid;
	}

	public void setInterestPaid(Double interestPaid) {
		this.interestPaid = interestPaid;
	}
}