package aurionpro.erp.ipms.ordermgmt.popayment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "payment_schedule_view", schema = "ordermgmt")
public class PoPaymentView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
	@Column(name = "purchase_order_no")
	private Long purchaseOrderNo;
	
	@Column(name = "amount", columnDefinition = "numeric(15,2)")
	private Double amount;
	
	@Column(name = "schedule_date")
	private Long scheduleDate;
	
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

	@Column(name = "po_number")
	private String poNumber;
	
	@Column(name = "party_id")
	private Long partyId;
	
	@Column(name = "party_name")
	private String partyName;
	
	@Column(name = "project_id")
	private Long projectId;
	
	@Column(name = "projectname")
	private String projectName;
	
	@Column
	private Long createdDate;
	
    @Column
    private String createdBy;
    
    @Column
    private Long updatedDate;
    
    @Column
    private String updatedBy;
    
    @Column
    private Boolean isDeleted;
    
    @Column
    private Long organizationId;
	
	@Transient
	private Long toDate;
	
	@Transient
	private Integer month;
	
	@Transient
	private Integer year;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Long purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Long scheduleDate) {
		this.scheduleDate = scheduleDate;
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

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
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

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getToDate() {
		return toDate;
	}

	public void setToDate(Long toDate) {
		this.toDate = toDate;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
