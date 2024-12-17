package aurionpro.erp.ipms.ordermgmt.purchase;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import aurionpro.erp.ipms.ordermgmt.productdetails.ProductDetailsView;

@Immutable
@Entity
@Table(name = "purchase_order_view", schema = "ordermgmt")
public class PurchaseOrderView {

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
    
    @Column(name = "purchase_order_no")
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

	@Column(name = "terms_conditions")
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
	
	//open bravo
	@Column(name = "po_pushed_status")
	private String poPushedStatus;

	@Column(name = "po_pushed_date")
	private Long poPushedDate;
	
	//po history data
	@Column(name = "invoicetoname")
	private String invoicetoname;

	@Column(name = "invoicetoaddressforhistory")
	private String invoicetoaddressforhistory;

	@Column(name = "invoicetocontact")
	private String invoicetocontact;

	@Column(name = "invoicetophone")
	private String invoicetophone;

	@Column(name = "invoicetoemail")
	private String invoicetoemail;

	@Column(name = "suppliername")
	private String suppliernameforhistory;

	@Column(name = "supplieraddress")
	private String supplieraddress;

	@Column(name = "suppliercontact")
	private String suppliercontact;

	@Column(name = "supplierphone")
	private String supplierphone;

	@Column(name = "supplieremail")
	private String supplieremail;

	@Column(name = "billtoname")
	private String billtoname;

	@Column(name = "billtoaddressforhistory")
	private String billtoaddressforhistory;

	@Column(name = "billtocontact")
	private String billtocontact;

	@Column(name = "billtophone")
	private String billtophone;

	@Column(name = "billtoemail")
	private String billtoemail;

	@Column(name = "billtogstin")
	private String billtogstin;

	@Column(name = "shiptoname")
	private String shiptoname;

	@Column(name = "shiptoaddressforhistory")
	private String shiptoaddressforhistory;

	@Column(name = "shiptocontact")
	private String shiptocontact;

	@Column(name = "shiptophone")
	private String shiptophone;

	@Column(name = "shiptoemail")
	private String shiptoemail;
	
	
	//data from joins
	@Column(name = "supp_name")
	private String suppName;

	@Column(name = "supp_contact_person")
	private String suppContactPerson;

	@Column(name = "supp_mob")
	private String suppMobNo;

	@Column(name = "supp_email")
	private String suppEmail;

	@Column(name = "buy_name")
	private String buyName;

	@Column(name = "buy_contact_person")
	private String buyContactPerson;

	@Column(name = "buy_mob")
	private String buyMobNo;

	@Column(name = "buy_email")
	private String buyEmail;

	@Column(name = "suppaddress")
	private String suppAddress;

	@Column(name = "suppcity")
	private String suppCity;

	@Column(name = "suppdist")
	private String suppDist;

	@Column(name = "supstate")
	private String suppState;

	@Column(name = "buyinvoaddress")
	private String buyInvoAddress;

	@Column(name = "buyinvocity")
	private String buyInvoCity;

	@Column(name = "buyinvodist")
	private String buyInvoDist;

	@Column(name = "buyinvostate")
	private String buyInvoState;

	@Column(name = "buybilltogstno")
	private String buyBillToGstNo;

	@Column(name = "buybilltoaddress")
	private String buyBillToAddress;

	@Column(name = "buybilltocity")
	private String buyBillToCity;

	@Column(name = "buybilltodist")
	private String buyBillToDist;

	@Column(name = "buybilltostate")
	private String buyBillToState;

	@Column(name = "buyshiptoaddress")
	private String buyShipToAddress;

	@Column(name = "buyshiptocity")
	private String buyShipToCity;

	@Column(name = "buyshiptodist")
	private String buyShipToDist;

	@Column(name = "buyshiptostate")
	private String buyShipToState;
	
	
	
	@Column(name = "price_list")
	private String priceList;
	
	@Column(name = "contact_id")
	private String contactId;
	
	@Column(name = "departmentname")
	private String departmentName;
	
	@Column(name = "acc_name")
	private String accName;
	
	@Column(name = "currency_symbol")
	private String currencySymbol;
	
	@Column(name = "payment_term_id")
	private Long paymentTermId;
	
	@Column(name = "payment_method_id")
	private Long paymentMethodId;
	
	@Column(name = "orgname")
	private String organisationName;
	
	@Column(name = "poheadname")
	private String poheadname;

	@Column(name = "poheaddesignation")
	private String poheaddesignation;
	
	//for project vendor wise report
	@Transient
	private String wiseReport;
	
	@Transient
	private Long fromDate;
	
	@Transient
	private Long toDate;
	//
	
	//for po criteria report
	@Transient
	private String category;

	@Transient
	private String subCategory;
	
	@Transient
	private Long productId;
	//
	
	@Transient
	private List<String> attachments;
	
	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public String getWiseReport() {
		return wiseReport;
	}

	public void setWiseReport(String wiseReport) {
		this.wiseReport = wiseReport;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getOrganisationName() {
		return organisationName;
	}


	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}


	@Transient
	private List<ProductDetailsView> productDetailsList;
	
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


	public String getInvoicetoname() {
		return invoicetoname;
	}


	public void setInvoicetoname(String invoicetoname) {
		this.invoicetoname = invoicetoname;
	}


	public String getInvoicetoaddressforhistory() {
		return invoicetoaddressforhistory;
	}


	public void setInvoicetoaddressforhistory(String invoicetoaddressforhistory) {
		this.invoicetoaddressforhistory = invoicetoaddressforhistory;
	}


	public String getInvoicetocontact() {
		return invoicetocontact;
	}


	public void setInvoicetocontact(String invoicetocontact) {
		this.invoicetocontact = invoicetocontact;
	}


	public String getInvoicetophone() {
		return invoicetophone;
	}


	public void setInvoicetophone(String invoicetophone) {
		this.invoicetophone = invoicetophone;
	}


	public String getInvoicetoemail() {
		return invoicetoemail;
	}


	public void setInvoicetoemail(String invoicetoemail) {
		this.invoicetoemail = invoicetoemail;
	}


	public String getSuppliernameforhistory() {
		return suppliernameforhistory;
	}


	public void setSuppliernameforhistory(String suppliernameforhistory) {
		this.suppliernameforhistory = suppliernameforhistory;
	}


	public String getSupplieraddress() {
		return supplieraddress;
	}


	public void setSupplieraddress(String supplieraddress) {
		this.supplieraddress = supplieraddress;
	}


	public String getSuppliercontact() {
		return suppliercontact;
	}


	public void setSuppliercontact(String suppliercontact) {
		this.suppliercontact = suppliercontact;
	}


	public String getSupplierphone() {
		return supplierphone;
	}


	public void setSupplierphone(String supplierphone) {
		this.supplierphone = supplierphone;
	}


	public String getSupplieremail() {
		return supplieremail;
	}


	public void setSupplieremail(String supplieremail) {
		this.supplieremail = supplieremail;
	}


	public String getBilltoname() {
		return billtoname;
	}


	public void setBilltoname(String billtoname) {
		this.billtoname = billtoname;
	}


	public String getBilltoaddressforhistory() {
		return billtoaddressforhistory;
	}


	public void setBilltoaddressforhistory(String billtoaddressforhistory) {
		this.billtoaddressforhistory = billtoaddressforhistory;
	}


	public String getBilltocontact() {
		return billtocontact;
	}


	public void setBilltocontact(String billtocontact) {
		this.billtocontact = billtocontact;
	}


	public String getBilltophone() {
		return billtophone;
	}


	public void setBilltophone(String billtophone) {
		this.billtophone = billtophone;
	}


	public String getBilltoemail() {
		return billtoemail;
	}


	public void setBilltoemail(String billtoemail) {
		this.billtoemail = billtoemail;
	}


	public String getBilltogstin() {
		return billtogstin;
	}


	public void setBilltogstin(String billtogstin) {
		this.billtogstin = billtogstin;
	}


	public String getShiptoname() {
		return shiptoname;
	}


	public void setShiptoname(String shiptoname) {
		this.shiptoname = shiptoname;
	}


	public String getShiptoaddressforhistory() {
		return shiptoaddressforhistory;
	}


	public void setShiptoaddressforhistory(String shiptoaddressforhistory) {
		this.shiptoaddressforhistory = shiptoaddressforhistory;
	}


	public String getShiptocontact() {
		return shiptocontact;
	}


	public void setShiptocontact(String shiptocontact) {
		this.shiptocontact = shiptocontact;
	}


	public String getShiptophone() {
		return shiptophone;
	}


	public void setShiptophone(String shiptophone) {
		this.shiptophone = shiptophone;
	}


	public String getShiptoemail() {
		return shiptoemail;
	}


	public void setShiptoemail(String shiptoemail) {
		this.shiptoemail = shiptoemail;
	}


	public String getSuppName() {
		return suppName;
	}


	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}


	public String getSuppContactPerson() {
		return suppContactPerson;
	}


	public void setSuppContactPerson(String suppContactPerson) {
		this.suppContactPerson = suppContactPerson;
	}


	public String getSuppMobNo() {
		return suppMobNo;
	}


	public void setSuppMobNo(String suppMobNo) {
		this.suppMobNo = suppMobNo;
	}


	public String getSuppEmail() {
		return suppEmail;
	}


	public void setSuppEmail(String suppEmail) {
		this.suppEmail = suppEmail;
	}


	public String getBuyName() {
		return buyName;
	}


	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}


	public String getBuyContactPerson() {
		return buyContactPerson;
	}


	public void setBuyContactPerson(String buyContactPerson) {
		this.buyContactPerson = buyContactPerson;
	}


	public String getBuyMobNo() {
		return buyMobNo;
	}


	public void setBuyMobNo(String buyMobNo) {
		this.buyMobNo = buyMobNo;
	}


	public String getBuyEmail() {
		return buyEmail;
	}


	public void setBuyEmail(String buyEmail) {
		this.buyEmail = buyEmail;
	}


	public String getSuppAddress() {
		return suppAddress;
	}


	public void setSuppAddress(String suppAddress) {
		this.suppAddress = suppAddress;
	}


	public String getSuppCity() {
		return suppCity;
	}


	public void setSuppCity(String suppCity) {
		this.suppCity = suppCity;
	}


	public String getSuppDist() {
		return suppDist;
	}


	public void setSuppDist(String suppDist) {
		this.suppDist = suppDist;
	}


	public String getSuppState() {
		return suppState;
	}


	public void setSuppState(String suppState) {
		this.suppState = suppState;
	}


	public String getBuyInvoAddress() {
		return buyInvoAddress;
	}


	public void setBuyInvoAddress(String buyInvoAddress) {
		this.buyInvoAddress = buyInvoAddress;
	}


	public String getBuyInvoCity() {
		return buyInvoCity;
	}


	public void setBuyInvoCity(String buyInvoCity) {
		this.buyInvoCity = buyInvoCity;
	}


	public String getBuyInvoDist() {
		return buyInvoDist;
	}


	public void setBuyInvoDist(String buyInvoDist) {
		this.buyInvoDist = buyInvoDist;
	}


	public String getBuyInvoState() {
		return buyInvoState;
	}


	public void setBuyInvoState(String buyInvoState) {
		this.buyInvoState = buyInvoState;
	}


	public String getBuyBillToGstNo() {
		return buyBillToGstNo;
	}


	public void setBuyBillToGstNo(String buyBillToGstNo) {
		this.buyBillToGstNo = buyBillToGstNo;
	}


	public String getBuyBillToAddress() {
		return buyBillToAddress;
	}


	public void setBuyBillToAddress(String buyBillToAddress) {
		this.buyBillToAddress = buyBillToAddress;
	}


	public String getBuyBillToCity() {
		return buyBillToCity;
	}


	public void setBuyBillToCity(String buyBillToCity) {
		this.buyBillToCity = buyBillToCity;
	}


	public String getBuyBillToDist() {
		return buyBillToDist;
	}


	public void setBuyBillToDist(String buyBillToDist) {
		this.buyBillToDist = buyBillToDist;
	}


	public String getBuyBillToState() {
		return buyBillToState;
	}


	public void setBuyBillToState(String buyBillToState) {
		this.buyBillToState = buyBillToState;
	}


	public String getBuyShipToAddress() {
		return buyShipToAddress;
	}


	public void setBuyShipToAddress(String buyShipToAddress) {
		this.buyShipToAddress = buyShipToAddress;
	}


	public String getBuyShipToCity() {
		return buyShipToCity;
	}


	public void setBuyShipToCity(String buyShipToCity) {
		this.buyShipToCity = buyShipToCity;
	}


	public String getBuyShipToDist() {
		return buyShipToDist;
	}


	public void setBuyShipToDist(String buyShipToDist) {
		this.buyShipToDist = buyShipToDist;
	}


	public String getBuyShipToState() {
		return buyShipToState;
	}


	public void setBuyShipToState(String buyShipToState) {
		this.buyShipToState = buyShipToState;
	}


	public String getPriceList() {
		return priceList;
	}


	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}


	public String getContactId() {
		return contactId;
	}


	public void setContactId(String contactId) {
		this.contactId = contactId;
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


	public String getCurrencySymbol() {
		return currencySymbol;
	}


	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}


	public Long getPaymentTermId() {
		return paymentTermId;
	}


	public void setPaymentTermId(Long paymentTermId) {
		this.paymentTermId = paymentTermId;
	}


	public Long getPaymentMethodId() {
		return paymentMethodId;
	}


	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}


	public List<ProductDetailsView> getProductDetailsList() {
		return productDetailsList;
	}


	public void setProductDetailsList(List<ProductDetailsView> productDetailsList) {
		this.productDetailsList = productDetailsList;
	}

	public String getPoheadname() {
		return poheadname;
	}

	public void setPoheadname(String poheadname) {
		this.poheadname = poheadname;
	}

	public String getPoheaddesignation() {
		return poheaddesignation;
	}

	public void setPoheaddesignation(String poheaddesignation) {
		this.poheaddesignation = poheaddesignation;
	}

	public String getAmountInWords() {
		return amountInWords;
	}

	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
}
