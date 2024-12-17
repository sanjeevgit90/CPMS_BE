package aurionpro.erp.ipms.ordermgmt.rcproductdetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractMaster;

@Entity
@Table(name = "rate_contract_product_details", schema = "ordermgmt")
public class RcProductDetails extends JKDEntityAuditWithId {

	/*@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36, unique = true)
	private String id;*/
	
	/*@Column(name = "purchase_order_no")
	private String purchaseOrderNo;*/
	
	@ManyToOne()
    @JoinColumn(name="rate_contract_no")
	private RateContractMaster rateContract;

	@Column(name = "product_name") //this should also be ManyToOne
	private Long productName;
	
	@Column(name = "rate", nullable = false, columnDefinition = "numeric(15,2)")
	private Double rate;
	
	@Column(name = "quantity", nullable = false, columnDefinition = "numeric(15,2)")
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
	
	@Column(name = "hsn_id", columnDefinition = "numeric(15,2)")
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
	
	@Column(name = "description" ,columnDefinition = "text")
	private String description;
	
	@Column(name = "product_name_with_desc",  columnDefinition = "text")
	private String productNameWithDesc;
	
	@Column(name = "base_uom_data")
	private String baseUomData;
	
	@Transient
	private String currency;
	
	@Transient
	private String isHistoricData;
	
	@Transient
	private String applyGstFlag;
	
	@Transient
	private Boolean stateFlag;

	public RateContractMaster getRateContract() {
		return rateContract;
	}

	public void setRateContract(RateContractMaster rateContract) {
		this.rateContract = rateContract;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getIsHistoricData() {
		return isHistoricData;
	}

	public void setIsHistoricData(String isHistoricData) {
		this.isHistoricData = isHistoricData;
	}

	public String getApplyGstFlag() {
		return applyGstFlag;
	}

	public void setApplyGstFlag(String applyGstFlag) {
		this.applyGstFlag = applyGstFlag;
	}

	public Boolean getStateFlag() {
		return stateFlag;
	}

	public void setStateFlag(Boolean stateFlag) {
		this.stateFlag = stateFlag;
	}
}
