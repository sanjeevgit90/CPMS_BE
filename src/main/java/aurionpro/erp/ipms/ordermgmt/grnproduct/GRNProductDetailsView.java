package aurionpro.erp.ipms.ordermgmt.grnproduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "grn_product_details_view", schema = "ordermgmt")
public class GRNProductDetailsView {

	@Id
	@Column(name = "entityid")
	private Long entityId;
	
	@Column(name = "grn_id")
	private Long grnId;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "accepted_quantity")
	private Integer acceptedQuantity;
	
	@Column(name = "rejected_quantity")
	private Integer rejectedQuantity;
	
	@Column(name = "received_quantity")
	private Integer receivedQuantity;
	
	@Column(name = "productname")
	private String productName;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getAcceptedQuantity() {
		return acceptedQuantity;
	}

	public void setAcceptedQuantity(Integer acceptedQuantity) {
		this.acceptedQuantity = acceptedQuantity;
	}

	public Integer getRejectedQuantity() {
		return rejectedQuantity;
	}

	public void setRejectedQuantity(Integer rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}

	public Integer getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(Integer receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}