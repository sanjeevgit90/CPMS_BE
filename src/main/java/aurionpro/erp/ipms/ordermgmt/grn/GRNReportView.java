package aurionpro.erp.ipms.ordermgmt.grn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "grn_report_view", schema = "ordermgmt")
public class GRNReportView {

	@Id
	@Column(name = "entityid", unique = true)
	private Long entityId;
	
	@Column(name = "purchase_order_no")
	private Long purchaseOrderNo;
	
	@Column(name = "product_name")
	private Long productName;
	
	@Column(name = "product")
	private String product;

	@Column(name = "quantity", columnDefinition = "numeric(15,2)")
	private Double quantity;
	
	@Column(name = "po_no")
	private Long poNo;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "grnquantity", columnDefinition = "numeric")
	private Long grnQuantity;
	
	@Column(name = "po_id")
	private Long poId;
	
	@Column(name = "po_name")
	private String poName;
	
	@Column(name = "supp_id")
	private Long suppId;
	
	@Column(name = "supp_name")
	private String suppName;
	
	@Column(name = "order_date")
	private Long orderDate;
	
	@Column(name = "pending_quantity", columnDefinition = "numeric")
	private Double pendingQuantity;
	
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

	public Long getProductName() {
		return productName;
	}

	public void setProductName(Long productName) {
		this.productName = productName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Long getPoNo() {
		return poNo;
	}

	public void setPoNo(Long poNo) {
		this.poNo = poNo;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getGrnQuantity() {
		return grnQuantity;
	}

	public void setGrnQuantity(Long grnQuantity) {
		this.grnQuantity = grnQuantity;
	}

	public Long getPoId() {
		return poId;
	}

	public void setPoId(Long poId) {
		this.poId = poId;
	}

	public String getPoName() {
		return poName;
	}

	public void setPoName(String poName) {
		this.poName = poName;
	}

	public Long getSuppId() {
		return suppId;
	}

	public void setSuppId(Long suppId) {
		this.suppId = suppId;
	}

	public String getSuppName() {
		return suppName;
	}

	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}

	public Double getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(Double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
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
