package aurionpro.erp.ipms.ordermgmt.grnproduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "grn_product_details", schema = "ordermgmt")
public class GRNProductDetails extends JKDEntityAuditWithId {

	@NotNull
	@Column(name = "grn_id", nullable = false)
	private Long grnId;
	
	@NotNull
	@Column(name = "product_id", nullable = false)
	private Long productId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "accepted_quantity")
	private int acceptedQuantity;
	
	@Column(name = "rejected_quantity")
	private int rejectedQuantity;
	
	@Column(name = "received_quantity")
	private int receivedQuantity;
	
	@Transient
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getGrnId() {
		return grnId;
	}

	public void setGrnId(Long grnId) {
		this.grnId = grnId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAcceptedQuantity() {
		return acceptedQuantity;
	}

	public void setAcceptedQuantity(int acceptedQuantity) {
		this.acceptedQuantity = acceptedQuantity;
	}

	public int getRejectedQuantity() {
		return rejectedQuantity;
	}

	public void setRejectedQuantity(int rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}

	public int getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}
}
