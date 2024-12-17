package aurionpro.erp.ipms.assetmgmt.locationwiseinstallation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="location_installation_view", schema = "assetmgmt")
@Immutable
public class AssetLocationWiseInstallationView{

	@Id
	private Long entityId;
	
	private Boolean isDeleted;
	
	private String location;
	
	private String locationaddress;
	
	private String policestation;
    
    private String city;
    
    private String district;
    
     private String state;
    
    private Long installationdate;
   
   private Long project;
	
	private Boolean printflag;
	
	private Boolean uploadflag;
	
	private String remark;
	
	private String projectname;
	
	private String installationattachment;

    public AssetLocationWiseInstallationView() {
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

	public Long getInstallationdate() {
		return installationdate;
	}

	public void setInstallationdate(Long installationdate) {
		this.installationdate = installationdate;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
	}

	public Boolean getPrintflag() {
		return printflag;
	}

	public void setPrintflag(Boolean printflag) {
		this.printflag = printflag;
	}

	public Boolean getUploadflag() {
		return uploadflag;
	}

	public void setUploadflag(Boolean uploadflag) {
		this.uploadflag = uploadflag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getInstallationattachment() {
		return installationattachment;
	}

	public void setInstallationattachment(String installationattachment) {
		this.installationattachment = installationattachment;
	}


}