package aurionpro.erp.ipms.assetmgmt.citywiseinstallation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="city_asset_installation_child", schema = "assetmgmt")
public class AssetCityWiseInstallationChild extends JKDEntityAuditWithId{
	
	@Column(length = 100)
    private String city;
	
	@Column(length = 500)
	private String product;
	  
    private Integer count;
   	
    public AssetCityWiseInstallationChild() {
    }

   
	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}

	
}