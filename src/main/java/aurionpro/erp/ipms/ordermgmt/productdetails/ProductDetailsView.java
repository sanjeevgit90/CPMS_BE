package aurionpro.erp.ipms.ordermgmt.productdetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "product_details_view", schema = "ordermgmt")
public class ProductDetailsView {

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
	
	@Column(name="purchase_order_no")
	private Long purchaseOrder;

	@Column(name = "product_name")
	private Long productName;
	
	@Column(name = "rate", columnDefinition = "numeric(15,2)")
	private Double rate;
	
	@Column(name = "quantity", columnDefinition = "numeric(15,2)")
	private Float quantity;
	
	@Column(name = "amount", columnDefinition = "numeric(15,2)")
	private Double amount;
	
	@Column(name = "discount", columnDefinition = "numeric(15,2)")
	private Float discount;
	
	@Column(name = "discount_amount", columnDefinition = "numeric(15,2)")
	private Double discountAmount;
	
	@Column(name = "total_discount", columnDefinition = "numeric(15,2)")
	private Double totalDiscount;
	
	@Column(name = "total_amount", columnDefinition = "numeric(15,2)")
	private Double totalAmount;
	
	@Column(name = "cgst", columnDefinition = "numeric(15,2)")
	private Float cgst;
	
	@Column(name = "sgst", columnDefinition = "numeric(15,2)")
	private Float sgst;
	
	@Column(name = "igst", columnDefinition = "numeric(15,2)")
	private Float igst;
	
	@Column(name = "hsn_id")
	private String hsnId;
	
	@Column(name = "cgst_amount", columnDefinition = "numeric(15,2)")
	private Double cgstAmount;
	
	@Column(name = "sgst_amount", columnDefinition = "numeric(15,2)")
	private Double sgstAmount;
	
	@Column(name = "igst_amount", columnDefinition = "numeric(15,2)")
	private Double igstAmount;
	
	@Column(name = "final_amount", columnDefinition = "numeric(15,2)")
	private Double finalAmount;
	
	@Column(name = "service_tax", columnDefinition = "numeric(15,2)")
	private Double serviceTax;
	
	@Column(name = "vat", columnDefinition = "numeric(15,2)")
	private Double vat;
	
	@Column(name = "description", columnDefinition = "text")
	private String description;
	
	@Column(name = "product_name_with_desc", columnDefinition = "text")
	private String productNameWithDesc;
	
	@Column(name = "base_uom_data")
	private String baseUomData;
	
	@Column(name = "product")
	private String product;
	
	@Column(name = "product_desc")
	private String productDesc;
	
	@Column(name = "baseuom")
	private String baseuom;

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

	public Long getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(Long purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Long getProductName() {
		return productName;
	}

	public void setProductName(Long productName) {
		this.productName = productName;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getCgst() {
		return cgst;
	}

	public void setCgst(Float cgst) {
		this.cgst = cgst;
	}

	public Float getSgst() {
		return sgst;
	}

	public void setSgst(Float sgst) {
		this.sgst = sgst;
	}

	public Float getIgst() {
		return igst;
	}

	public void setIgst(Float igst) {
		this.igst = igst;
	}

	public String getHsnId() {
		return hsnId;
	}

	public void setHsnId(String hsnId) {
		this.hsnId = hsnId;
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

	public Double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Double getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductNameWithDesc() {
		return productNameWithDesc;
	}

	public void setProductNameWithDesc(String productNameWithDesc) {
		this.productNameWithDesc = productNameWithDesc;
	}

	public String getBaseUomData() {
		return baseUomData;
	}

	public void setBaseUomData(String baseUomData) {
		this.baseUomData = baseUomData;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getBaseuom() {
		return baseuom;
	}

	public void setBaseuom(String baseuom) {
		this.baseuom = baseuom;
	}

}
