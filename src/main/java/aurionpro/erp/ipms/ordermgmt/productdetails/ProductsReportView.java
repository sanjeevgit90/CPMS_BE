package aurionpro.erp.ipms.ordermgmt.productdetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "products_view", schema = "ordermgmt")
public class ProductsReportView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
	@Column(name="purchase_order_no")
	private Long purchaseOrderNo;

	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "productname")
	private String productName;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "subcategory")
	private String subCategory;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
}