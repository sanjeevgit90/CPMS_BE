package aurionpro.erp.ipms.ordermgmt.ratecontract;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "rate_contract_master", schema = "ordermgmt")
public class RateContractMaster extends JKDEntityAuditWithId {

	/*@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36, unique = true)
	private String id;*/

	@Column(name = "rate_contract_no", unique = true)
	private String rateContractNo;

	@Column(name = "contract_date")
	private Long contractDate;

	@Column(name = "department")
	private Long department;

	@Column(name = "account_name")
	private Long accountName;

	@Column(name = "contract_type")
	private String contractType;

	@Column(name = "mode_of_payment")
	private String modeOfPayment;

	@Column(name = "suppliers_reference")
	private String suppliersReference;

	@Column(name = "other_reference")
	private String otherReference;

	@Column(name = "dispatch_through")
	private String dispatchThrough;

	@Column(name = "currency")
	private String currency;

	@Column(name = "delivery_term")
	private String deliveryTerm;

	@Column(name = "invoice_to_address")
	private Long invoiceToAddress;

	@Column(name = "supplier_details")
	private Long supplierDetails;

	@Column(name = "bill_from_state")
	private String billFromState;

	@Column(name = "bill_to_address")
	private Long billToAddress;

	@Column(name = "ship_to_address")
	private Long shipToAddress;

	@Column(name = "terms_conditions", columnDefinition = "text")
	private String termsConditions;

	@Column(name = "uploaded_terms_annexure")
	private String uploadedTermsAnnexure;

	@Column(name = "additional_terms")
	private String additionalTerms;

	@Column(name = "approval_status")
	private String approvalStatus;

	@Column(name = "signed_copy")
	private String signedCopyPath;

	@Column(name = "supplier_name")
	private Long supplierName;

	@Column(name = "buyer_name")
	private Long buyerName;

	@Column(name = "bill_from_gst_no")
	private String billFromGstNo;

	@Column(name = "bill_to_gst_no")
	private String billToGstNo;

	@Column(name = "include_terms")
	private String includeTerms;

	@Column(name = "grand_total", columnDefinition = "numeric(15,2)")
	private Double grandTotal;

	@Column(name = "total_taxes", columnDefinition = "numeric(15,2)")
	private Double totalTaxes;

	@Column(name = "total_without_taxes", columnDefinition = "numeric(15,2)")
	private Double totalWithoutTaxes;

	@Column(name = "is_historic_data")
	private String isHistoricData;

	@Column(name = "workflow_name")
	private String workflowName;

	@Column(name = "remark")
	private String remark;
	
	@Column(name = "valid_till")
	private Long validTill;
	
	@Column(name = "max_limit", columnDefinition = "numeric(15,2)")
	private Double maxLimit;
	
	@Column(name = "organisation_id")
	private Long organisationId;
	
	@Column(name = "amount_in_words")
	private String amountInWords;
	
	/*@Column(name = "poheadname")
	private String poheadname;
	
	@Column(name = "poheaddesignation")
	private String poheaddesignation;*/
	
	@Transient
	private List<RateContractAttachmentsDto> attachments;
	
	@ElementCollection
    @CollectionTable(
        name = "rate_contract_attachments", schema = "ordermgmt",
        joinColumns = { @JoinColumn(name = "rateContractId") })
    private List<String> attachment;

	public List<String> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<String> attachment) {
		this.attachment = attachment;
	}

	public String getRateContractNo() {
		return rateContractNo;
	}

	public void setRateContractNo(String rateContractNo) {
		this.rateContractNo = rateContractNo == null ? null: rateContractNo.trim();
	}

	public Long getContractDate() {
		return contractDate;
	}

	public void setContractDate(Long contractDate) {
		this.contractDate = contractDate;
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

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getSuppliersReference() {
		return suppliersReference;
	}

	public void setSuppliersReference(String suppliersReference) {
		this.suppliersReference = suppliersReference;
	}

	public String getOtherReference() {
		return otherReference;
	}

	public void setOtherReference(String otherReference) {
		this.otherReference = otherReference;
	}

	public String getDispatchThrough() {
		return dispatchThrough;
	}

	public void setDispatchThrough(String dispatchThrough) {
		this.dispatchThrough = dispatchThrough;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDeliveryTerm() {
		return deliveryTerm;
	}

	public void setDeliveryTerm(String deliveryTerm) {
		this.deliveryTerm = deliveryTerm;
	}

	public Long getInvoiceToAddress() {
		return invoiceToAddress;
	}

	public void setInvoiceToAddress(Long invoiceToAddress) {
		this.invoiceToAddress = invoiceToAddress;
	}

	public Long getSupplierDetails() {
		return supplierDetails;
	}

	public void setSupplierDetails(Long supplierDetails) {
		this.supplierDetails = supplierDetails;
	}

	public String getBillFromState() {
		return billFromState;
	}

	public void setBillFromState(String billFromState) {
		this.billFromState = billFromState;
	}

	public Long getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(Long billToAddress) {
		this.billToAddress = billToAddress;
	}

	public Long getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(Long shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	public String getUploadedTermsAnnexure() {
		return uploadedTermsAnnexure;
	}

	public void setUploadedTermsAnnexure(String uploadedTermsAnnexure) {
		this.uploadedTermsAnnexure = uploadedTermsAnnexure;
	}

	public String getAdditionalTerms() {
		return additionalTerms;
	}

	public void setAdditionalTerms(String additionalTerms) {
		this.additionalTerms = additionalTerms;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getSignedCopyPath() {
		return signedCopyPath;
	}

	public void setSignedCopyPath(String signedCopyPath) {
		this.signedCopyPath = signedCopyPath;
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

	public String getBillFromGstNo() {
		return billFromGstNo;
	}

	public void setBillFromGstNo(String billFromGstNo) {
		this.billFromGstNo = billFromGstNo;
	}

	public String getBillToGstNo() {
		return billToGstNo;
	}

	public void setBillToGstNo(String billToGstNo) {
		this.billToGstNo = billToGstNo;
	}

	public String getIncludeTerms() {
		return includeTerms;
	}

	public void setIncludeTerms(String includeTerms) {
		this.includeTerms = includeTerms;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Double getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(Double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	public Double getTotalWithoutTaxes() {
		return totalWithoutTaxes;
	}

	public void setTotalWithoutTaxes(Double totalWithoutTaxes) {
		this.totalWithoutTaxes = totalWithoutTaxes;
	}

	public String getIsHistoricData() {
		return isHistoricData;
	}

	public void setIsHistoricData(String isHistoricData) {
		this.isHistoricData = isHistoricData;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getValidTill() {
		return validTill;
	}

	public void setValidTill(Long validTill) {
		this.validTill = validTill;
	}

	public Double getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(Double maxLimit) {
		this.maxLimit = maxLimit;
	}

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	public List<RateContractAttachmentsDto> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<RateContractAttachmentsDto> attachments) {
		this.attachments = attachments;
	}

	public String getAmountInWords() {
		return amountInWords;
	}

	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
}
