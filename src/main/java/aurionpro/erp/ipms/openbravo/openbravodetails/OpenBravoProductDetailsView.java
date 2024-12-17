package aurionpro.erp.ipms.openbravo.openbravodetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "ob_product_details_view", schema = "openbravo")
public class OpenBravoProductDetailsView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
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
	
	@Column(name = "hsn_id")
	private String hsnId;
	
	@Column(name = "final_amount", columnDefinition = "numeric(15,2)")
	private Double finalAmount;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "base_uom_data")
	private String baseUomData;
	
	@Column(name = "ob_product_id")
	private String obProductId;
	
	@Column(name = "ob_uom_id")
	private String obUomId;
	
	@Column(name = "ob_hsn_id")
	private String obHsnId;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
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

	public String getHsnId() {
		return hsnId;
	}

	public void setHsnId(String hsnId) {
		this.hsnId = hsnId;
	}

	public Double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBaseUomData() {
		return baseUomData;
	}

	public void setBaseUomData(String baseUomData) {
		this.baseUomData = baseUomData;
	}

	public String getObProductId() {
		return obProductId;
	}

	public void setObProductId(String obProductId) {
		this.obProductId = obProductId;
	}

	public String getObUomId() {
		return obUomId;
	}

	public void setObUomId(String obUomId) {
		this.obUomId = obUomId;
	}

	public String getObHsnId() {
		return obHsnId;
	}

	public void setObHsnId(String obHsnId) {
		this.obHsnId = obHsnId;
	}
}
