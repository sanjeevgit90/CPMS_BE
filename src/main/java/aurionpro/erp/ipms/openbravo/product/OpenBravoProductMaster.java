package aurionpro.erp.ipms.openbravo.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMaster;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name = "ob_product_master", schema = "openbravo")
public class OpenBravoProductMaster extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
	private ProductMaster product;

	public String getOpenBravoId() {
		return openBravoId;
	}

	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}

	public ProductMaster getProduct() {
		return product;
	}

	public void setProduct(ProductMaster product) {
		this.product = product;
	}
}
