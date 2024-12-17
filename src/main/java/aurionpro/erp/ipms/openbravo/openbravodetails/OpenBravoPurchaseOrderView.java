package aurionpro.erp.ipms.openbravo.openbravodetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "ob_purchase_order_view", schema = "openbravo")
public class OpenBravoPurchaseOrderView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
	@Column(name = "purchase_order_no")
	private String purchaseOrderNo;

	@Column(name = "order_date")
	private Long orderDate;

	@Column(name = "mode_of_payment")
	private String modeOfPayment;

	@Column(name = "suppliers_reference")
	private String suppliersReference;

	@Column(name = "currency")
	private String currency;

	@Column(name = "delivery_term")
	private String deliveryTerm;

	@Column(name = "invoice_to_address")
	private Long invoiceToAddress;

	@Column(name = "supplier_details")
	private Long supplierDetails;

	@Column(name = "account_name")
	private Long accountName;

	@Column(name = "terms_conditions")
	private String termsConditions;

	@Column(name = "supplier_name")
	private Long supplierName;

	@Column(name = "buyer_name")
	private Long buyerName;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "organisation_id")
	private Long organisationId;

	//open bravo
	@Column(name = "po_pushed_status")
	private String poPushedStatus;

	@Column(name = "po_pushed_date")
	private Long poPushedDate;
	//open bravo

	@Column(name = "supplier_ob")
	private String supplierOb;

	@Column(name = "price_list")
	private String priceList;

	@Column(name = "contact_id")
	private String contactId;

	@Column(name = "suppaddress_ob")
	private String suppaddressOb;

	@Column(name = "buyer_name_ob")
	private String buyerNameOb;

	@Column(name = "buyinvoaddress_ob")
	private String buyinvoaddressOb;

	@Column(name = "project_ob")
	private String projectOb;

	@Column(name = "currency_ob")
	private String currencyOb;

	@Column(name = "ob_payment_term_id")
	private String obPaymentTermId;

	@Column(name = "ob_payment_method_id")
	private String obPaymentMethodId;

	@Column(name = "organisation_ob")
	private String organisationOb;

	@Column(name = "push_po_to_ob")
	private String pushPoToOb;
	
	@Column(name = "document_type")
	private String documentType;
	
	@Column(name = "transaction_document_type")
	private String transactionDocumentType;
	
	@Column(name = "warehouse")
	private String warehouse;

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

	public Long getAccountName() {
		return accountName;
	}

	public void setAccountName(Long accountName) {
		this.accountName = accountName;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
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

	public String getSupplierOb() {
		return supplierOb;
	}

	public void setSupplierOb(String supplierOb) {
		this.supplierOb = supplierOb;
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

	public String getSuppaddressOb() {
		return suppaddressOb;
	}

	public void setSuppaddressOb(String suppaddressOb) {
		this.suppaddressOb = suppaddressOb;
	}

	public String getBuyerNameOb() {
		return buyerNameOb;
	}

	public void setBuyerNameOb(String buyerNameOb) {
		this.buyerNameOb = buyerNameOb;
	}

	public String getBuyinvoaddressOb() {
		return buyinvoaddressOb;
	}

	public void setBuyinvoaddressOb(String buyinvoaddressOb) {
		this.buyinvoaddressOb = buyinvoaddressOb;
	}

	public String getProjectOb() {
		return projectOb;
	}

	public void setProjectOb(String projectOb) {
		this.projectOb = projectOb;
	}

	public String getCurrencyOb() {
		return currencyOb;
	}

	public void setCurrencyOb(String currencyOb) {
		this.currencyOb = currencyOb;
	}

	public String getObPaymentTermId() {
		return obPaymentTermId;
	}

	public void setObPaymentTermId(String obPaymentTermId) {
		this.obPaymentTermId = obPaymentTermId;
	}

	public String getObPaymentMethodId() {
		return obPaymentMethodId;
	}

	public void setObPaymentMethodId(String obPaymentMethodId) {
		this.obPaymentMethodId = obPaymentMethodId;
	}

	public String getOrganisationOb() {
		return organisationOb;
	}

	public void setOrganisationOb(String organisationOb) {
		this.organisationOb = organisationOb;
	}

	public String getPushPoToOb() {
		return pushPoToOb;
	}

	public void setPushPoToOb(String pushPoToOb) {
		this.pushPoToOb = pushPoToOb;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getTransactionDocumentType() {
		return transactionDocumentType;
	}

	public void setTransactionDocumentType(String transactionDocumentType) {
		this.transactionDocumentType = transactionDocumentType;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

}