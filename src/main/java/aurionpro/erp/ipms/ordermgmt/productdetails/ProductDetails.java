package aurionpro.erp.ipms.ordermgmt.productdetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderMaster;

@Entity
@Table(name = "product_details", schema = "ordermgmt")
public class ProductDetails extends JKDEntityAuditWithId {

	/*@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36, unique = true)
	private String id;*/
	
	/*@Column(name = "purchase_order_no")
	private String purchaseOrderNo;*/
	
	@ManyToOne()
    @JoinColumn(name="purchase_order_no")
	private PurchaseOrderMaster purchaseOrder;

	@Column(name = "product_name") //this should also be ManyToOne
	private long productName;
	
	@Column(name = "rate", nullable = false, columnDefinition = "numeric(15,2)")
	private double rate;
	
	@Column(name = "quantity", nullable = false, columnDefinition = "numeric(15,2)")
	private float quantity;
	
	@Column(name = "amount", columnDefinition = "numeric(15,2)")
	private double amount;
	
	@Column(name = "discount", columnDefinition = "numeric(15,2)")
	private float discount;
	
	@Column(name = "discount_amount", columnDefinition = "numeric(15,2)")
	private double discountAmount;
	
	@Column(name = "total_discount", columnDefinition = "numeric(15,2)")
	private double totalDiscount;
	
	@Column(name = "total_amount", columnDefinition = "numeric(15,2)")
	private double totalAmount;
	
	@Column(name = "cgst", columnDefinition = "numeric(15,2)")
	private float cgst;
	
	@Column(name = "sgst", columnDefinition = "numeric(15,2)")
	private float sgst;
	
	@Column(name = "igst", columnDefinition = "numeric(15,2)")
	private float igst;
	
	@Column(name = "hsn_id")
	private String hsnId;
	
	@Column(name = "cgst_amount", columnDefinition = "numeric(15,2)")
	private double cgstAmount;
	
	@Column(name = "sgst_amount", columnDefinition = "numeric(15,2)")
	private double sgstAmount;
	
	@Column(name = "igst_amount", columnDefinition = "numeric(15,2)")
	private double igstAmount;
	
	@Column(name = "final_amount", columnDefinition = "numeric(15,2)")
	private double finalAmount;
	
	@Column(name = "service_tax", columnDefinition = "numeric(15,2)")
	private double serviceTax;
	
	@Column(name = "vat", columnDefinition = "numeric(15,2)")
	private double vat;
	
	@Column(name = "description", columnDefinition = "text")
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
	private boolean stateFlag;
	
	@Transient
	private boolean poHeadEditFlag;

	public PurchaseOrderMaster getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrderMaster purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public long getProductName() {
		return productName;
	}

	public void setProductName(long productName) {
		this.productName = productName;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getCgst() {
		return cgst;
	}

	public void setCgst(float cgst) {
		this.cgst = cgst;
	}

	public float getSgst() {
		return sgst;
	}

	public void setSgst(float sgst) {
		this.sgst = sgst;
	}

	public float getIgst() {
		return igst;
	}

	public void setIgst(float igst) {
		this.igst = igst;
	}

	public String getHsnId() {
		return hsnId;
	}

	public void setHsnId(String hsnId) {
		this.hsnId = hsnId;
	}

	public double getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public double getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public double getIgstAmount() {
		return igstAmount;
	}

	public void setIgstAmount(double igstAmount) {
		this.igstAmount = igstAmount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public double getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(double serviceTax) {
		this.serviceTax = serviceTax;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
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

	public boolean isStateFlag() {
		return stateFlag;
	}

	public void setStateFlag(boolean stateFlag) {
		this.stateFlag = stateFlag;
	}

	public boolean isPoHeadEditFlag() {
		return poHeadEditFlag;
	}

	public void setPoHeadEditFlag(boolean poHeadEditFlag) {
		this.poHeadEditFlag = poHeadEditFlag;
	}
}
