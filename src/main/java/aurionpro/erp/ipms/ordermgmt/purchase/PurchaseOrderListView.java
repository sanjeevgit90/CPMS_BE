package aurionpro.erp.ipms.ordermgmt.purchase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "purchase_order_list_view", schema = "ordermgmt")
public class PurchaseOrderListView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
	
    @Column(name = "purchase_order_no")
	private String purchaseOrderNo;

	@Column(name = "order_date")
	private Long orderDate;

	@Column(name = "department")
	private Long department;

	@Column(name = "account_name")
	private Long accountName;

	@Column(name = "approval_status")
	private String approvalStatus;

	@Column(name = "supplier_name")
	private Long supplierName;

	@Column(name = "buyer_name")
	private Long buyerName;

	@Column(name = "workflow_name")
	private String workflowName;
	@Column(name = "amended_po_id")
	private Long amendedPoId;

	@Column(name = "organisation_id")
	private Long organisationId;
	
	//open bravo
	@Column(name = "po_pushed_status")
	private String poPushedStatus;

	@Column(name = "po_pushed_date")
	private Long poPushedDate;
	
	@Column(name = "suppliername")
	private String suppliernameforhistory;
	
	
	//data from joins
	@Column(name = "supp_name")
	private String suppName;

	@Column(name = "buy_name")
	private String buyName;

	@Column(name = "departmentname")
	private String departmentName;
	
	@Column(name = "acc_name")
	private String accName;

	@Column(name = "orgname")
	private String organisationName;
		
	@Transient
	private Long fromDate;
	
	@Transient
	private Long toDate;

	@Column(name = "grand_total", columnDefinition = "numeric(15,2)")
	private Double grandTotal;
		
	private String projectPin;
	

	public String getProjectPin() {
		return projectPin;
	}

	public void setProjectPin(String projectPin) {
		this.projectPin = projectPin;
	}

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

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}

	public Long getDepartment() {
		return department;
	}

	public void setDepartment(Long department) {
		this.department = department;
	}

	public Long getAccountName() {
		return accountName;
	}

	public void setAccountName(Long accountName) {
		this.accountName = accountName;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Long getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(Long supplierName) {
		this.supplierName = supplierName;
	}

	public Long getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(Long buyerName) {
		this.buyerName = buyerName;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public Long getAmendedPoId() {
		return amendedPoId;
	}

	public void setAmendedPoId(Long amendedPoId) {
		this.amendedPoId = amendedPoId;
	}

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	public String getPoPushedStatus() {
		return poPushedStatus;
	}

	public void setPoPushedStatus(String poPushedStatus) {
		this.poPushedStatus = poPushedStatus;
	}

	public Long getPoPushedDate() {
		return poPushedDate;
	}

	public void setPoPushedDate(Long poPushedDate) {
		this.poPushedDate = poPushedDate;
	}

	public String getSuppliernameforhistory() {
		return suppliernameforhistory;
	}

	public void setSuppliernameforhistory(String suppliernameforhistory) {
		this.suppliernameforhistory = suppliernameforhistory;
	}


	public String getSuppName() {
		return suppName;
	}

	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
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

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	
}
