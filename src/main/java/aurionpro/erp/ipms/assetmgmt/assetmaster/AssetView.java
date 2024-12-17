package aurionpro.erp.ipms.assetmgmt.assetmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "assetview", schema = "assetmgmt")
@Immutable

public class AssetView{

		@Id
		@Column(columnDefinition = "bigint")
	    private Long entityId;

		@Column(columnDefinition = "bigint")
	    private Long organizationId;
	    
	    @Column(name ="isdeleted")
	    private Boolean isdeleted;
	    
	    @Column(name = "assettag")
	    private String assettag;
	
	    @Column(name = "assetname")
	    private String assetname;
	    
	    @Column(name = "serialno")
	    private String serialno;
	
	    @Column(name = "orderno")
	    private String orderno;
	
	    @Column(name = "deliverychallanno")
	    private String deliverychallanno;
	
	    @Column(name = "depreciation")
		private String depreciation;
	
	    @Column(name = "eol")
	    private String eol;
	
	    @Column(name = "notes")
	    private String notes ;
	
	    @Column(name= "assetstatus")
	    private String assetstatus;
	
	    @Column(columnDefinition = "bigint")
	    private Long vendorname;
	
	    @Column(columnDefinition = "bigint")
	    private Long projectid;
	    
	    @Column(name = "projectname")
	    private String projectname;
	
	    @Column(columnDefinition = "bigint")
	    private Long purchasedate;
	  
	    @Column(columnDefinition = "bigint")
	    private Long warrantytilldate;
     
	    @Column(name = "location")
	    private String location;
   
	    @Column(columnDefinition = "bigint")
	    private Long productid;
   
	    @Column(name = "category")
	    private String category;
   
	    @Column(name = "subcategory")
	    private String subcategory;
   
	    @Column(name = "productname")
	    private String productname;
   
	    @Column(name = "locationid")
	    private String locationid;
	    
	    @Column(name = "policestation")
	    private String policestation;
   
	    @Column(name = "city")
	    private String city;
   
	    @Column(name = "district")
	    private String district;
   
	    @Column(name = "state")
	    private String state;
	
	    @Column(length = 100)
	    private String manufacturer;
	    
	    @Column(length = 100)
	    private String model;
	    
	    @Column(length = 100)
	    private String vendor;
	    
	    private Boolean isprimary;
     
     public AssetView() {
    }


	public String getAssettag() {
		return assettag;
	}


	public void setAssettag(String assettag) {
		this.assettag = assettag;
	}


	public String getAssetname() {
		return assetname;
	}


	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}


	public String getSerialno() {
		return serialno;
	}


	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}


	public String getOrderno() {
		return orderno;
	}


	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}


	public String getDeliverychallanno() {
		return deliverychallanno;
	}


	public void setDeliverychallanno(String deliverychallanno) {
		this.deliverychallanno = deliverychallanno;
	}


	public String getDepreciation() {
		return depreciation;
	}


	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}


	public String getEol() {
		return eol;
	}


	public void setEol(String eol) {
		this.eol = eol;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getAssetstatus() {
		return assetstatus;
	}


	public void setAssetstatus(String assetstatus) {
		this.assetstatus = assetstatus;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Long getVendorname() {
		return vendorname;
	}


	public void setVendorname(Long vendorname) {
		this.vendorname = vendorname;
	}

	public Long getPurchasedate() {
		return purchasedate;
	}


	public void setPurchasedate(Long purchasedate) {
		this.purchasedate = purchasedate;
	}


	public Long getWarrantytilldate() {
		return warrantytilldate;
	}


	public void setWarrantytilldate(Long warrantytilldate) {
		this.warrantytilldate = warrantytilldate;
	}



	public Long getProductid() {
		return productid;
	}


	public void setProductid(Long productid) {
		this.productid = productid;
	}


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


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getLocationid() {
		return locationid;
	}


	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}


	public String getPolicestation() {
		return policestation;
	}


	public void setPolicestation(String policestation) {
		this.policestation = policestation;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
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


	public Long getEntityId() {
		return entityId;
	}


	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}


	public Long getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}


	public Boolean getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}


	public Long getProjectid() {
		return projectid;
	}


	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}


	public String getProjectname() {
		return projectname;
	}


	public void setProjectname(String projectname) {
		this.projectname = projectname;
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


	public String getVendor() {
		return vendor;
	}


	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public Boolean getIsprimary() {
		return isprimary;
	}


	public void setIsprimary(Boolean isprimary) {
		this.isprimary = isprimary;
	}
	
	
}