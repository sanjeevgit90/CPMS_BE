package aurionpro.erp.ipms.assetmgmt.locationwiseinstallation;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="location_asset_installation", schema = "assetmgmt")
public class AssetLocationWiseInstallationMaster extends JKDEntityAuditWithId{

	@NotEmpty(message = "Location is required")
	@Column(length = 100)
    private String location;
	
	@NotEmpty(message = "Location Address is required")
	@Column(length = 500)
	private String locationaddress;
	
	@Column(length = 100)
    private String policestation;
    
    @NotEmpty(message = "City is required")
    @Column(length = 100)
    private String city;
    
    @Column(length = 100)
    private String district;
    
    @NotEmpty(message = "state is required")
    @Column(length = 100)
    private String state;
    
    @NotNull(message = "Installation Date is required")
    private Long installationdate;
   
    @Column(nullable = false)
	private Long project;
	
	private Boolean printflag;
	
	private Boolean uploadflag;
	
	@Column(length = 500)
	private String remark;
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name="location")
    private List<AssetLocationWiseInstallationChild> installationMasterChild;
	
	@Column(length = 200)
	private String installationattachment;

    public AssetLocationWiseInstallationMaster() {
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationaddress() {
		return locationaddress;
	}

	public void setLocationaddress(String locationaddress) {
		this.locationaddress = locationaddress;
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

	public Long getInstallationdate() {
		return installationdate;
	}

	public void setInstallationdate(Long installationdate) {
		this.installationdate = installationdate;
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

	public List<AssetLocationWiseInstallationChild> getInstallationMasterChild() {
		return installationMasterChild;
	}

	public void setInstallationMasterChild(List<AssetLocationWiseInstallationChild> installationMasterChild) {
		this.installationMasterChild = installationMasterChild;
	}

	public String getInstallationattachment() {
		return installationattachment;
	}

	public void setInstallationattachment(String installationattachment) {
		this.installationattachment = installationattachment;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
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