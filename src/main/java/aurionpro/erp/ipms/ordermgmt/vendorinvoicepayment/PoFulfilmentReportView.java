package aurionpro.erp.ipms.ordermgmt.vendorinvoicepayment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "po_fulfilment_view", schema = "ordermgmt")
public class PoFulfilmentReportView {

	@Id
	@Column(name = "entityid")
	private Long entityId;
	
    @Column(name = "purchase_order_no")
	private String purchaseOrderNo;
	
    @Column(name = "grand_total", columnDefinition = "numeric(15,2)")
	private Double grandTotal;
    
    @Column(name = "balance_invoice", columnDefinition = "numeric(15,2)")
   	private Double balanceInvoice;
    
    @Column(name = "order_date")
   	private Long orderDate;
    
	@Column(name = "TotalInvoice", columnDefinition = "numeric(15,2)")
	private Double invoiceAmount;
	
    @Column(name = "TotalPayments", columnDefinition = "numeric(15,2)")
	private Double amount;
	
    @Column(name = "balance_payment", columnDefinition = "numeric(15,2)")
	private Double balancePayment;
    
    @Column(name = "project_id")
   	private Long projectId;
    
    @Column(name = "projectname")
   	private String projectName;
      
	private String projectPin;
	
	 @Column(name = "approval_status")
	private String approvalStatus;
    
    public String getProjectPin() {
		return projectPin;
	}

	public void setProjectPin(String projectPin) {
		this.projectPin = projectPin;
	}

	@Column(name = "party_id")
   	private Long partyId;
    
    @Column(name = "party_name")
   	private String partyName;
    
    @Column(name = "payment_due_date")
   	private Long paymentDuedate;
    
    @Transient
	private Long fromDate;
	
	@Transient
	private Long toDate;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Double getBalanceInvoice() {
		return balanceInvoice;
	}

	public void setBalanceInvoice(Double balanceInvoice) {
		this.balanceInvoice = balanceInvoice;
	}

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}

	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalancePayment() {
		return balancePayment;
	}

	public void setBalancePayment(Double balancePayment) {
		this.balancePayment = balancePayment;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Long getPaymentDuedate() {
		return paymentDuedate;
	}

	public void setPaymentDuedate(Long paymentDuedate) {
		this.paymentDuedate = paymentDuedate;
	}

	public Long getFromDate() {
		return fromDate;
	}

	public void setFromDate(Long fromDate) {
		this.fromDate = fromDate;
	}

	public Long getToDate() {
		return toDate;
	}

	public void setToDate(Long toDate) {
		this.toDate = toDate;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	 
}
