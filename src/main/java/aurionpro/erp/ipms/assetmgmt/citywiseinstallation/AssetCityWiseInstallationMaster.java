package aurionpro.erp.ipms.assetmgmt.citywiseinstallation;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="city_asset_installation", schema = "assetmgmt")
public class AssetCityWiseInstallationMaster extends JKDEntityAuditWithId{

	@NotEmpty(message = "City is required")
	@Column(length = 100)
    private String city;
	
	@Column(length = 100)
    private String district;
	
	@Column(length = 100)
	private String poreferenceno;
	
	@Column(length = 100)
    private String pono;
    
    private Long podate;
    
    private String customerdepartment;
   
    private Long project;
	
	private Boolean printflag;
	
	@Column(length = 500)
	private String remark;
	
	private Long installationdate;
	
	@Column(length = 100)
	private String contactperson;
	
	@Column(length = 100)
	private String designation;
	
	@Column(length = 500)
	private String address;
	
	@Column(length = 15)
	private String telno;
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name="city")
    private List<AssetCityWiseInstallationChild> installationMasterChild;
	
	@Column(length = 200)
	private String installationattachment;
	
	private Boolean uploadflag;
	
	@Column(length = 200)
	private String state;

    public AssetCityWiseInstallationMaster() {
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

	public List<AssetCityWiseInstallationChild> getInstallationMasterChild() {
		return installationMasterChild;
	}

	public void setInstallationMasterChild(List<AssetCityWiseInstallationChild> installationMasterChild) {
		this.installationMasterChild = installationMasterChild;
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

}