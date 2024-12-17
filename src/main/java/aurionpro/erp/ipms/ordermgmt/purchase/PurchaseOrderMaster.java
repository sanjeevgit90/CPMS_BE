package aurionpro.erp.ipms.ordermgmt.purchase;

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
@Table(name = "purchase_order_master", schema = "ordermgmt")
public class PurchaseOrderMaster extends JKDEntityAuditWithId {

	/*@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36, unique = true)
	private String id;*/

	@Column(name = "purchase_order_no", unique = true)
	private String purchaseOrderNo;

	@Column(name = "order_date")
	private Long orderDate;

	@Column(name = "department")
	private Long department;

	@Column(name = "account_name")
	private Long accountName;

	@Column(name = "order_type")
	private String orderType;

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

	@Column(name = "additional_terms", columnDefinition = "text")
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

	@Column(name = "discount_amt", columnDefinition = "numeric(15,2)")
	private Double discountAmt;

	@Column(name = "is_historic_data")
	private String isHistoricData;

	@Column(name = "workflow_name")
	private String workflowName;

	@Column(name = "po_made_from")
	private String poMadeFrom;

	@Column(name = "rc_id")
	private Long rateContractId;

	@Column(name = "remark")
	private String remark;

	@Column(name = "old_po_id")
	private Long oldPoId;

	@Column(name = "old_po_no")
	private String oldPoNo;

	@Column(name = "old_po_date")
	private Long oldPoDate;

	@Column(name = "is_amended_flag")
	private String isAmendedFlag;

	@Column(name = "amended_po_id")
	private Long amendedPoId;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "organisation_id")
	private Long organisationId;
/*
	@Column(name = "po_pushed_status")
	private String poPushedStatus;

	@Column(name = "po_pushed_date")
	private Date poPushedDate;
*/

	@Column(name = "verify")
	private String verify;

	@Column(name = "verify_date")
	private Long verifyDate;

	@Column(name = "verfied_po_attachment")
	private String verfiedPOAttachment;
	
	@Column(name = "verified_by")
	private String verifiedBy;

	@Column(name = "mail_sent_date")
	private Long mailSentDate;

	@Column(name = "mail_sent_by")
	private String mailSentBy;

	@Column(name = "po_copy")
	private String poCopy;
	
	@Column(name = "amount_in_words")
	private String amountInWords;
	
	@Transient
	private List<PurchaseOrderAttachmentsDto> attachments;
	
	@ElementCollection
    @CollectionTable(
        name = "purchase_order_attachments", schema = "ordermgmt",
        joinColumns = { @JoinColumn(name = "purchaseOrderId") })
    private List<String> attachment;

	public List<String> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<String> attachment) {
		this.attachment = attachment;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo == null? null: purchaseOrderNo.trim();
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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public Double getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(Double discountAmt) {
		this.discountAmt = discountAmt;
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

	public String getPoMadeFrom() {
		return poMadeFrom;
	}

	public void setPoMadeFrom(String poMadeFrom) {
		this.poMadeFrom = poMadeFrom;
	}

	public Long getRateContractId() {
		return rateContractId;
	}

	public void setRateContractId(Long rateContractId) {
		this.rateContractId = rateContractId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getOldPoId() {
		return oldPoId;
	}

	public void setOldPoId(Long oldPoId) {
		this.oldPoId = oldPoId;
	}

	public String getOldPoNo() {
		return oldPoNo;
	}

	public void setOldPoNo(String oldPoNo) {
		this.oldPoNo = oldPoNo;
	}

	public Long getOldPoDate() {
		return oldPoDate;
	}

	public void setOldPoDate(Long oldPoDate) {
		this.oldPoDate = oldPoDate;
	}

	public String getIsAmendedFlag() {
		return isAmendedFlag;
	}

	public void setIsAmendedFlag(String isAmendedFlag) {
		this.isAmendedFlag = isAmendedFlag;
	}

	public Long getAmendedPoId() {
		return amendedPoId;
	}

	public void setAmendedPoId(Long amendedPoId) {
		this.amendedPoId = amendedPoId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public Long getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Long verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getVerfiedPOAttachment() {
		return verfiedPOAttachment;
	}

	public void setVerfiedPOAttachment(String verfiedPOAttachment) {
		this.verfiedPOAttachment = verfiedPOAttachment;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public Long getMailSentDate() {
		return mailSentDate;
	}

	public void setMailSentDate(Long mailSentDate) {
		this.mailSentDate = mailSentDate;
	}

	public String getMailSentBy() {
		return mailSentBy;
	}

	public void setMailSentBy(String mailSentBy) {
		this.mailSentBy = mailSentBy;
	}

	public String getPoCopy() {
		return poCopy;
	}

	public void setPoCopy(String poCopy) {
		this.poCopy = poCopy;
	}

	public List<PurchaseOrderAttachmentsDto> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<PurchaseOrderAttachmentsDto> attachments) {
		this.attachments = attachments;
	}

	public String getAmountInWords() {
		return amountInWords;
	}

	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
}
