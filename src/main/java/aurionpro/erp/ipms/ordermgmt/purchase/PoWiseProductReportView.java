package aurionpro.erp.ipms.ordermgmt.purchase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "products_by_po_report_view", schema = "ordermgmt")
public class PoWiseProductReportView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
	@Column(name = "purchase_order_no")
	private Long purchaseOrderNo;
	
	@Column(name = "po_number")
	private String poNumber;
	
	@Column(name = "party_id")
	private Long partyId;
	
	@Column(name = "party_name")
	private String partyName;
	
	@Column(name = "project_id")
	private Long projectId;
	
	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "projectpin")
	private String projectPin;
	
	@Column(name = "order_date")
	private Long orderDate;
	
	@Column(name = "product_name")
	private Long productId;
	
	@Column(name = "product")
	private String productName;
	
	@Column(name = "hsn_code")
	private String hsnCode;
	
	@Column(name = "rate", columnDefinition = "numeric(15,2)")
	private Double rate;
	
	@Column(name = "quantity", columnDefinition = "numeric(15,2)")
	private Double quantity;
	
	@Column(name = "amount", columnDefinition = "numeric(15,2)")
	private Double amount;
	
	@Column(name = "cgst_amount", columnDefinition = "numeric(15,2)")
	private Double cgstAmount;
	
	@Column(name = "sgst_amount", columnDefinition = "numeric(15,2)")
	private Double sgstAmount;
	
	@Column(name = "igst_amount", columnDefinition = "numeric(15,2)")
	private Double igstAmount;
	
	@Column(name = "total_discount", columnDefinition = "numeric(15,2)")
	private Double totalDiscount;
	
	@Column(name = "final_amount", columnDefinition = "numeric(15,2)")
	private Double finalAmount;
	
	@Column(name = "po_grand_total", columnDefinition = "numeric(15,2)")
	private Double poGrandTotal;
	
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

	public Long getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Long purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
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

	public String getProjectPin() {
		return projectPin;
	}

	public void setProjectPin(String projectPin) {
		this.projectPin = projectPin;
	}

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(Double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public Double getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(Double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public Double getIgstAmount() {
		return igstAmount;
	}

	public void setIgstAmount(Double igstAmount) {
		this.igstAmount = igstAmount;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Double getPoGrandTotal() {
		return poGrandTotal;
	}

	public void setPoGrandTotal(Double poGrandTotal) {
		this.poGrandTotal = poGrandTotal;
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
}
