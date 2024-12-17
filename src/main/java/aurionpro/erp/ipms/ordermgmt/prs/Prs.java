package aurionpro.erp.ipms.ordermgmt.prs;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "prs_master", schema = "ordermgmt")
public class Prs extends JKDEntityAuditWithId {

	@NotEmpty(message = "Prs no. is required")
	@Column(name = "prs_no", unique = true)
	private String prsNo;

	@Min(value = 1, message = "Prs date is required")
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

	@Column(name = "department", length = 50)
	private String department;

	@Column(name = "project_name")
	private Long projectName;
	
	@Column(name = "approval_status", length = 100)
	private String approvalStatus;
	
/*	@Column(name = "approval_level")
	private String PrsApprovalStatus;*/
		
	@Column(name = "location", length = 50)
	private String location;

	@Column(name = "request_by", length = 50)
	private String requestedBy;

	@Column(name = "approved_by", length = 100)
	private String approvedBy;

	@Column(name = "signature")
	private String signature;

	@Column(name = "checklist")
	private String checklist;
	
	@Column(name = "quotation", length = 5)
	private Boolean quotation;
	
	@Column(name = "po_copy", length = 5)
	private Boolean poCopy;
	
	@Column(name = "checked_invoice_copy", length = 5)
	private Boolean checkedInvoiceCopy;
	
	@Column(name = "approval", length = 5)
	private Boolean approval;
	
	@Column(name = "supporting_documents", length = 5)
	private Boolean supportingDocuments;
	
/*	@Column(name = "remark")
	private String remark;*/

	@Column(name = "invoice_file_upload", length = 500)
	private String invoiceFileUpload;

	@Column(name = "status", length = 20)
	private String status;
	
	@Column(name = "grn_id")
	private Long grnId;
	
	@Column(name = "is_utility_payment", length = 5)
	private Boolean isUtilityPayment;
	
	@Column(name = "office", length = 50)
	private String office;
	
	@Column(name = "bill_type")
	private String billType;
	
	@Column(name = "bill_no")
	private String billNo;
	
	@Column(name = "attached_bill")
	private String attachedBill;
	
	@ElementCollection
    @CollectionTable(
        name = "prs_attachment", schema = "ordermgmt",
        joinColumns = { @JoinColumn(name = "prsid") })
    private List<String> attachments;

	public String getPrsNo() {
		return prsNo;
	}

	public void setPrsNo(String prsNo) {
		this.prsNo = prsNo == null? null: prsNo.trim();
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

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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

	public String getInvoiceFileUpload() {
		return invoiceFileUpload;
	}

	public void setInvoiceFileUpload(String invoiceFileUpload) {
		this.invoiceFileUpload = invoiceFileUpload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Boolean getIsUtilityPayment() {
		return isUtilityPayment;
	}

	public void setIsUtilityPayment(Boolean isUtilityPayment) {
		this.isUtilityPayment = isUtilityPayment;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
	
}
