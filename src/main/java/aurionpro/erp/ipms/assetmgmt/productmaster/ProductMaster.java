package aurionpro.erp.ipms.assetmgmt.productmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="productmaster", schema = "assetmgmt",uniqueConstraints = @UniqueConstraint(columnNames ={"productname"}))
public class ProductMaster extends JKDEntityAuditWithId{

	@NotEmpty(message = "Product name is required")
	@Column(length = 100)
	private String productname;
    
	@NotEmpty(message = "Serial Number is required")
	@Column(length = 100)
    private String serialno;
	
	@Column(length = 50)
    private String barcode;
	
	@NotEmpty(message = "Product Type is required")
	@Column(length = 50)
    private String producttype;
	
	@Column(length = 2000)
    private String description;
	
	@Column(length = 520)
    private String hsndescription;
	
	@Column(length = 50)
    private String baseuom;
	
    private Boolean ishazardous;
    
    @Column(length = 100)
    private String hazardousgoodtype;
   
    @Column(length = 100)
    private String category;
    
    @Column(length = 100)
    private String manufacturer;
    
    @Column(length = 100)
    private String model;
    
    @Column(length = 100)
    private String hsncode;
    
    @Column(length = 100)
    private String subcategory;
    
    private Integer orgflag;

     public ProductMaster() {
    }

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname == null ? null: productname.trim();
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno == null ? null: serialno.trim();
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHsndescription() {
		return hsndescription;
	}

	public void setHsndescription(String hsndescription) {
		this.hsndescription = hsndescription;
	}

	public String getBaseuom() {
		return baseuom;
	}

	public void setBaseuom(String baseuom) {
		this.baseuom = baseuom;
	}

	public Boolean getIshazardous() {
		return ishazardous;
	}

	public void setIshazardous(Boolean ishazardous) {
		this.ishazardous = ishazardous;
	}

	public String getHazardousgoodtype() {
		return hazardousgoodtype;
	}

	public void setHazardousgoodtype(String hazardousgoodtype) {
		this.hazardousgoodtype = hazardousgoodtype;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Integer getOrgflag() {
		return orgflag;
	}

	public void setOrgflag(Integer orgflag) {
		this.orgflag = orgflag;
	}
	
	
    
}