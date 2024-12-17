package aurionpro.erp.ipms.assetmgmt.locationwiseinstallation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="location_asset_installation_child", schema = "assetmgmt")
public class AssetLocationWiseInstallationChild extends JKDEntityAuditWithId{
	
	@Column(length = 100)
    private String location;
	
	@Column(length = 500)
	private String product;
	
	@Column(length = 100)
    private String manufacturer;
    
   @Column(length = 100)
    private String model;
    
    private Integer count;
   
    @Column(length = 100)
	private String serialno;
    
    @Column(length = 100)
  	private String assetstatus;
	
	
    public AssetLocationWiseInstallationChild() {
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getAssetstatus() {
		return assetstatus;
	}

	public void setAssetstatus(String assetstatus) {
		this.assetstatus = assetstatus;
	}
    
}