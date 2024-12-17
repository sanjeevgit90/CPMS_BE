package aurionpro.erp.ipms.ordermgmt.boqproduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
import aurionpro.erp.ipms.ordermgmt.boq.BOQ;

@Entity
@Table(name = "boq_product_child", schema = "ordermgmt", uniqueConstraints = { @UniqueConstraint(columnNames={"boq_id", "product_id"}) })
public class BOQProductChild extends JKDEntityAuditWithId {

	@ManyToOne()
    @JoinColumn(name="boq_id", nullable = false)
	private BOQ boq;
	
	@Column(name = "product_id", nullable = false)
	private Long productId;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "rate", nullable = false)
	private Double rate;

	public BOQ getBoq() {
		return boq;
	}

	public void setBoq(BOQ boq) {
		this.boq = boq;
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
