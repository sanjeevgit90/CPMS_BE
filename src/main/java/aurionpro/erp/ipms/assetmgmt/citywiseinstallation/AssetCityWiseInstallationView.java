package aurionpro.erp.ipms.assetmgmt.citywiseinstallation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="city_installation_view", schema = "assetmgmt")
@Immutable
public class AssetCityWiseInstallationView {

	@Id
	private Long entityId;
	
	private Boolean isDeleted;
	 
    private String city;
	
	private String district;
	
	private String poreferenceno;
	
	  private String pono;
    
    private Long podate;
    
    private String customerdepartment;
   
    private Long project;
	
	private Boolean printflag;
	
	private String remark;
	
	private Long installationdate;
	
	private String contactperson;
	
	private String designation;
	
	private String address;
	
	private String telno;
	
	private String installationattachment;
	
	private String projectname;
	
	private Boolean uploadflag;
	
	@Column(length = 200)
	private String state;

    public AssetCityWiseInstallationView() {
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPoreferenceno() {
		return poreferenceno;
	}

	public void setPoreferenceno(String poreferenceno) {
		this.poreferenceno = poreferenceno;
	}

	public String getPono() {
		return pono;
	}

	public void setPono(String pono) {
		this.pono = pono;
	}

	public Long getPodate() {
		return podate;
	}

	public void setPodate(Long podate) {
		this.podate = podate;
	}

	public String getCustomerdepartment() {
		return customerdepartment;
	}

	public void setCustomerdepartment(String customerdepartment) {
		this.customerdepartment = customerdepartment;
	}

	public void setProject(Long project) {
		this.project = project;
	}

	public Long getProject() {
		return project;
	}

	public Boolean getPrintflag() {
		return printflag;
	}

	public void setPrintflag(Boolean printflag) {
		this.printflag = printflag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getInstallationdate() {
		return installationdate;
	}

	public void setInstallationdate(Long installationdate) {
		this.installationdate = installationdate;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}


	public String getInstallationattachment() {
		return installationattachment;
	}

	public void setInstallationattachment(String installationattachment) {
		this.installationattachment = installationattachment;
	}

	public Boolean getUploadflag() {
		return uploadflag;
	}

	public void setUploadflag(Boolean uploadflag) {
		this.uploadflag = uploadflag;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}    

}