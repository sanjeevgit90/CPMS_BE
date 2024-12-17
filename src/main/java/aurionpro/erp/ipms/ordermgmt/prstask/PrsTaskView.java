package aurionpro.erp.ipms.ordermgmt.prstask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "prs_task_view", schema = "ordermgmt")
public class PrsTaskView {
	
	@Id
	@Column(name="taskid")
	private Long taskid;
	
	@Column(name="prs_id")
	private Long prsId;
	 
	private String workflowname;
	
	private String assigntorole;
	 
	private String assigntouser;
	
	@Column
    private String stageName;
	
	@Column
    private String remark;

	@Column(name = "prs_no")
	private String prsNo;
	
	@Column(name = "prs_date")
	private Long prsDate;
	
	@Column(name = "purchase_order_no")
	private Long purchaseOrderNo;
	
	@Column(name = "issue_cheque_to")
	private Long issueChequeTo;
	
	@Column(name = "invoice_no")
	private String invoiceNo;
	
	@Column(name = "invoice_date")
	private Long invoiceDate;
	
	@Column(name = "invoice_amount", columnDefinition = "numeric(15,2)")
	private Double invoiceAmount;
	
	@Column(name = "payment_due_date")
	private Long paymentDueDate;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "project_name")
	private Long projectName;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "request_by")
	private String requestedBy;
	
	@Column(name = "approved_by")
	private String approvedBy;
	
	@Column(name = "signature")
	private String signature;
	
	@Column(name = "checklist")
	private String checklist;
	
	@Column(name = "quotation")
	private Boolean quotation;
	
	@Column(name = "po_copy")
	private Boolean poCopy;
	
	@Column(name = "checked_invoice_copy")
	private Boolean checkedInvoiceCopy;
	
	@Column(name = "approval")
	private Boolean approval;
	
	@Column(name = "supporting_documents")
	private Boolean supportingDocuments;

	@Column(name = "invoice_file_upload")
	private String invoiceFileUpload;
	
	@Column(name = "purchase_order_number")
	private String purchaseOrderNumber;
	
	@Column(name = "project")
	private String project;
	
	@Column(name = "party_name")
	private String partyName;
	
	private String approvalStatus;

	/*@Column(name = "status")
	private String status;*/
	
	@Column(name = "total_without_taxes", columnDefinition = "numeric(15,2)")
	private Double totalWithoutTaxes;
	
	@Column(name = "total_taxes", columnDefinition = "numeric(15,2)")
	private Double totalTaxes;
	
	@Column(name = "grand_total", columnDefinition = "numeric(15,2)")
	private Double grandTotal;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "inr_value", columnDefinition = "numeric(15,2)")
	private Double inrValue;
	
	@Column(name = "po_grand_total_inr", columnDefinition = "numeric(15,2)")
	private Double poGrandTotalInr;
	
	@Column(name = "projectpin")
	private String projectPin;
	
	@Column(name = "invoice_amt_inr", columnDefinition = "numeric(15,2)")
	private Double invoiceAmtInr;
	
	@Column(name = "grn_id")
	private Long grnId;
	
	//@Column(name = "grn_no")
	//private String grnNo;
	
	@Column(name = "is_utility_payment")
	private Boolean isUtilityPayment;
	
	@Column(name = "office")
	private String office;
	
	@Column(name = "bill_type")
	private String billType;
	
	@Column(name = "bill_no")
	private String billNo;
	
	@Column(name = "attached_bill")
	private String attachedBill;
	
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
    
	//@Transient
	//private List<AttachmentsDto> attachments;

    @Column
	private Long taskCreatedDate;
	
    @Column
    private String taskCreatedBy;
    
    @Column
    private Long taskUpdatedDate;
    
    @Column
    private String taskUpdatedBy;
    
	public String getPrsNo() {
		return prsNo;
	}

	public void setPrsNo(String prsNo) {
		this.prsNo = prsNo;
	}

	public Long getPrsDate() {
		return prsDate;
	}

	public void setPrsDate(Long prsDate) {
		this.prsDate = prsDate;
	}

	public Long getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Long purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Long getIssueChequeTo() {
		return issueChequeTo;
	}

	public void setIssueChequeTo(Long issueChequeTo) {
		this.issueChequeTo = issueChequeTo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Long getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Long invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Long getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Long paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getProjectName() {
		return projectName;
	}

	public void setProjectName(Long projectName) {
		this.projectName = projectName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getChecklist() {
		return checklist;
	}

	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}

	
	public String getInvoiceFileUpload() {
		return invoiceFileUpload;
	}

	public void setInvoiceFileUpload(String invoiceFileUpload) {
		this.invoiceFileUpload = invoiceFileUpload;
	}

	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Double getTotalWithoutTaxes() {
		return totalWithoutTaxes;
	}

	public void setTotalWithoutTaxes(Double totalWithoutTaxes) {
		this.totalWithoutTaxes = totalWithoutTaxes;
	}

	public Double getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(Double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getInrValue() {
		return inrValue;
	}

	public void setInrValue(Double inrValue) {
		this.inrValue = inrValue;
	}

	public Double getPoGrandTotalInr() {
		return poGrandTotalInr;
	}

	public void setPoGrandTotalInr(Double poGrandTotalInr) {
		this.poGrandTotalInr = poGrandTotalInr;
	}

	public String getProjectPin() {
		return projectPin;
	}

	public void setProjectPin(String projectPin) {
		this.projectPin = projectPin;
	}

	public Double getInvoiceAmtInr() {
		return invoiceAmtInr;
	}

	public void setInvoiceAmtInr(Double invoiceAmtInr) {
		this.invoiceAmtInr = invoiceAmtInr;
	}

	public Long getGrnId() {
		return grnId;
	}

	public void setGrnId(Long grnId) {
		this.grnId = grnId;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getAttachedBill() {
		return attachedBill;
	}

	public void setAttachedBill(String attachedBill) {
		this.attachedBill = attachedBill;
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

	public Boolean getQuotation() {
		return quotation;
	}

	public void setQuotation(Boolean quotation) {
		this.quotation = quotation;
	}

	public Boolean getPoCopy() {
		return poCopy;
	}

	public void setPoCopy(Boolean poCopy) {
		this.poCopy = poCopy;
	}

	public Boolean getCheckedInvoiceCopy() {
		return checkedInvoiceCopy;
	}

	public void setCheckedInvoiceCopy(Boolean checkedInvoiceCopy) {
		this.checkedInvoiceCopy = checkedInvoiceCopy;
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

	public Boolean getSupportingDocuments() {
		return supportingDocuments;
	}

	public void setSupportingDocuments(Boolean supportingDocuments) {
		this.supportingDocuments = supportingDocuments;
	}

	public Boolean getIsUtilityPayment() {
		return isUtilityPayment;
	}

	public void setIsUtilityPayment(Boolean isUtilityPayment) {
		this.isUtilityPayment = isUtilityPayment;
	}

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public Long getPrsId() {
		return prsId;
	}

	public void setPrsId(Long prsId) {
		this.prsId = prsId;
	}

	public String getWorkflowname() {
		return workflowname;
	}

	public void setWorkflowname(String workflowname) {
		this.workflowname = workflowname;
	}

	public String getAssigntorole() {
		return assigntorole;
	}

	public void setAssigntorole(String assigntorole) {
		this.assigntorole = assigntorole;
	}

	public String getAssigntouser() {
		return assigntouser;
	}

	public void setAssigntouser(String assigntouser) {
		this.assigntouser = assigntouser;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getTaskCreatedDate() {
		return taskCreatedDate;
	}

	public void setTaskCreatedDate(Long taskCreatedDate) {
		this.taskCreatedDate = taskCreatedDate;
	}

	public String getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(String taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	public Long getTaskUpdatedDate() {
		return taskUpdatedDate;
	}

	public void setTaskUpdatedDate(Long taskUpdatedDate) {
		this.taskUpdatedDate = taskUpdatedDate;
	}

	public String getTaskUpdatedBy() {
		return taskUpdatedBy;
	}

	public void setTaskUpdatedBy(String taskUpdatedBy) {
		this.taskUpdatedBy = taskUpdatedBy;
	}
	
	
}