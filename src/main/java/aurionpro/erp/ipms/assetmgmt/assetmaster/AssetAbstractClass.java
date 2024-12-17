package aurionpro.erp.ipms.assetmgmt.assetmaster;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AssetAbstractClass extends JKDEntityAuditWithId{

	@Column(length = 100)
	//@Pattern(regexp="/^[a-z][a-z\\s]*$/",message="Geography name should have alphabets with space only")  
	private String assettag;
	
	@NotEmpty(message = "Asset Name is required")
	@Column(length = 100)
	private String assetname;
	
	@NotEmpty(message = "Serial Number is required")
	@Column(length = 100)
	private String serialno;
	
	@Column(length = 100)
	private String orderno;
	
	@Column(length = 100)
	private String deliverychallanno;
	
	@Column(length = 100)
	private String depreciation;
	
	@Column(length = 100)
	private String eol;
	
	@Column(length = 500)
	private String notes ;
	
	@NotEmpty(message = "Asset Status is required")
	@Column(length = 50)
	private String assetstatus;
	
	@NotNull(message = "Vendor Name is required")
	@Column(length = 50)
	private Long vendorname;
	
	@NotNull(message = "Project Name is required")
	private Long projectname;
	
	//@NotNull(message = "Purchase Date is required")
	private Long purchasedate;
	  
	//@NotNull(message = "Warranty Date is required")
	private Long warrantytilldate;
     
   private String location;
   
   private Boolean isprimary;
   
   @NotNull(message = "Product is required")
   private Long productid;
	
     
     public AssetAbstractClass() {
    }


	public String getAssettag() {
		return assettag;
	}


	public void setAssettag(String assettag) {
		this.assettag = assettag== null ? null : assettag.trim();
	}


	public String getAssetname() {
		return assetname;
	}


	public void setAssetname(String assetname) {
		this.assetname =  assetname== null ? null :assetname.trim();
	}


	public String getSerialno() {
		return serialno;
	}


	public void setSerialno(String serialno) {
		this.serialno = serialno== null ? null :serialno.trim();
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


	public Long getProjectname() {
		return projectname;
	}


	public void setProjectname(Long projectname) {
		this.projectname = projectname;
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


	public Boolean getIsprimary() {
		return isprimary;
	}


	public void setIsprimary(Boolean isprimary) {
		this.isprimary = isprimary;
	}

	
}