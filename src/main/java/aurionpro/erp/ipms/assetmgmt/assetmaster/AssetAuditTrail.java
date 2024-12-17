package aurionpro.erp.ipms.assetmgmt.assetmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMaster;
import aurionpro.erp.ipms.common.locationmaster.LocationMaster;

@Entity
@Table(name="assetaudittrail", schema = "assetmgmt")
public class AssetAuditTrail extends AssetAbstractClass{
	
	@Column(length = 100)
	  private String category;
	 
	 public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPolicestation() {
		return policestation;
	}

	public void setPolicestation(String policestation) {
		this.policestation = policestation;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(length = 100)
	  private String subcategory;
	 
	@Column(length = 100)
	  private String city;
	 
	@Column(length = 100)
	  private String policestation;
	 
	@Column(length = 100)
	  private String district;
	 
	@Column(length = 100)
	  private String state;
	 

	public AssetAuditTrail() {
		super();
	}
	
	public AssetAuditTrail(	Long entityId,String assetname, String assetstatus, String assettag, String deliverychallanno, String depreciation, String eol, 
			String location, String orderno, Long purchasedate, String serialno, Long warrantytilldate,  String product,
			String category,String subcategory,String project, 
			String operation ,Long assetid,String vendor, String city,String policestation,String district,String state) {
		this.setAssetid(entityId);
		this.setAssettag(assettag);
		 this.setAssetname(assetname);
		 this.setSerialno(serialno);
		 this.setAssetstatus(assetstatus);
		 this.setDeliverychallanno(deliverychallanno);
		 this.setDepreciation(depreciation);
		 this.setEol(eol);
		 this.setLocation(location);
		 this.setOrderno(orderno);
		 this.setProduct(product);
		 this.setProject(project);
		 this.setPurchasedate(purchasedate);
		 this.setVendor(vendor);
		 this.setWarrantytilldate(warrantytilldate);
		 this.setOperation(operation);
		 this.setAssetid(assetid);
		 this.setCity(city);
		 this.setState(state);
		 this.setPolicestation(policestation);
		 this.setDistrict(district);
		 this.setCategory(subcategory);
		 this.setSubcategory(subcategory);
	}
	
	public AssetAuditTrail(AssetMaster asset, ProductMaster product, LocationMaster location) {
		this.setAssetid(asset.getEntityId());
		this.setAssettag(asset.getAssettag());
		 this.setAssetname(asset.getAssetname());
		 this.setSerialno(asset.getSerialno());
		 this.setAssetstatus(asset.getAssetstatus());
		 this.setDeliverychallanno(asset.getDeliverychallanno());
		 this.setDepreciation(asset.getDepreciation());
		 this.setEol(asset.getEol());
		 this.setLocation(asset.getLocation());
		 this.setNotes(asset.getNotes());
		 this.setOrderno(asset.getOrderno());
		 this.setProductid(asset.getProductid());
		 this.setProjectname(asset.getProjectname());
		 this.setPurchasedate(asset.getPurchasedate());
		 this.setVendorname(asset.getVendorname());
		 this.setWarrantytilldate(asset.getWarrantytilldate());
		 this.setNotes(asset.getNotes());
		 this.setCategory(product.getCategory());
		 this.setSubcategory(product.getSubcategory());
		 this.setCity(location.getCity());
		 this.setPolicestation(location.getPolicestation());
		 this.setDistrict(location.getDistrict());
		 this.setState(location.getState());
	}

	@Column(length = 15)
    private String operation;
	
	
    private Long assetid;
	

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getAssetid() {
		return assetid;
	}

	public void setAssetid(Long assetid) {
		this.assetid = assetid;
	}
	
	@Transient
	 private String project;
	
	@Transient
	 private String vendor;
	
	@Transient
	 private String product;


	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	

	
}