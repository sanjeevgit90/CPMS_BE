package aurionpro.erp.ipms.ordermgmt.purchaseordertask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "all_task_view", schema = "ordermgmt")
public class AllTaskView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
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
    
    @Column(name = "po_id")
	private Long poId;
	
	@Column(name = "upload_file")
	private String uploadFile;

	@Column(name = "po_rc_flag")
	private String poRcFlag;
	
	@Column
    private String stageName;

    @Column
    private String workflowName;

    @Column
    private String assignToRole;

    @Column
    private String assignToUser;

    @Column
    private String approvalStatus;
    
    @Column
    private String remark;
    
    @Column(name = "purchase_order_no")
	private String purchaseOrderNo;
    
    @Column(name = "order_date")
	private Long orderDate;
    
    @Column(name = "po_approval_status")
	private String poApprovalStatus;
    
    @Column(name = "supplier_name")
	private Long supplierName;
    
    @Column(name = "account_name")
	private Long accountName;

    @Column(name = "organisation_id")
	private Long organisationId;
    
    @Column(name = "grand_total", columnDefinition = "numeric(15,2)")
	private Double grandTotal;
    
    @Column(name = "projectname")
	private String projectName;
    
    @Column(name = "orgname")
	private String orgName;
    
    @Column(name = "party_name")
	private String partyName;
    
    @Column(name = "currency")
	private String currency;
    
    @Column(name = "currency_symbol")
	private String currencySymbol;
    
    @Column(name = "actioned_by")
	private String actionedBy;
    
    @Column(name = "task_count")
	private Long taskCount;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
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

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
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

	public Long getPoId() {
		return poId;
	}

	public void setPoId(Long poId) {
		this.poId = poId;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getPoRcFlag() {
		return poRcFlag;
	}

	public void setPoRcFlag(String poRcFlag) {
		this.poRcFlag = poRcFlag;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getAssignToRole() {
		return assignToRole;
	}

	public void setAssignToRole(String assignToRole) {
		this.assignToRole = assignToRole;
	}

	public String getAssignToUser() {
		return assignToUser;
	}

	public void setAssignToUser(String assignToUser) {
		this.assignToUser = assignToUser;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public String getPoApprovalStatus() {
		return poApprovalStatus;
	}

	public void setPoApprovalStatus(String poApprovalStatus) {
		this.poApprovalStatus = poApprovalStatus;
	}

	public Long getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(Long supplierName) {
		this.supplierName = supplierName;
	}

	public Long getAccountName() {
		return accountName;
	}

	public void setAccountName(Long accountName) {
		this.accountName = accountName;
	}

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Long taskCount) {
		this.taskCount = taskCount;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getActionedBy() {
		return actionedBy;
	}

	public void setActionedBy(String actionedBy) {
		this.actionedBy = actionedBy;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
}
