package aurionpro.erp.ipms.common.locationmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="locationmaster")
public class LocationMaster extends JKDEntityAudit{

	@Id
	@NotEmpty(message = "Location Id is required")
	@Column(length = 100)
	private String locationid;
	
	@NotEmpty(message = "Location Address is required")
	@Column(length = 500)
	private String locationaddress;
	
	@Column(length = 6, nullable = false)
	@NotNull(message= "Zip may not be empty")
    private String zip;
	
	@Column(length = 20, nullable = false)
	//@NotNull(message= "Latitude may not be empty")
	private String latitude;
	
	@Column(length = 20, nullable = false)
	//@NotNull(message= "longitude may not be empty")
	private String longitude;
	
	@Column(length = 100)
	private String contactperson;
	
	@Column(length = 15)
	private String phoneno;
	
	@Column(length = 100)
	@Email(message = "Email should be valid")
	private String emailid ;
	
	@Column(nullable = true)
	private Boolean isprioritysite;
	
	@Column(nullable = true)
    private Long surveydate;
	
	@Column(nullable = true)
    private Long approvaldate;
	
	@Column(length = 100)
	private String country ;
    
	@Column(length = 100)
    private String policestation;
    
    @NotEmpty(message = "City is required")
    @Column(length = 100)
    private String city;
    
    @NotEmpty(message = "District is required")
    @Column(length = 100)
    private String district;
    
    @NotEmpty(message = "State is required")
    @Column(length = 100)
    private String state;
    
    private Boolean warehouse;
    
      
     public LocationMaster() {
    }


	public String getLocationid() {
		return locationid;
	}


	public void setLocationid(String locationid) {
		this.locationid = locationid == null ? null: locationid.trim();
	}


	public String getLocationaddress() {
		return locationaddress;
	}


	public void setLocationaddress(String locationaddress) {
		this.locationaddress = locationaddress;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getContactperson() {
		return contactperson;
	}


	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}


	public String getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public Boolean getIsprioritysite() {
		return isprioritysite;
	}


	public void setIsprioritysite(Boolean isprioritysite) {
		this.isprioritysite = isprioritysite;
	}


	public Long getSurveydate() {
		return surveydate;
	}


	public void setSurveydate(Long surveydate) {
		this.surveydate = surveydate;
	}


	public Long getApprovaldate() {
		return approvaldate;
	}


	public void setApprovaldate(Long approvaldate) {
		this.approvaldate = approvaldate;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
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


	public Boolean getWarehouse() {
		return warehouse;
	}


	public void setWarehouse(Boolean warehouse) {
		this.warehouse = warehouse;
	}

	
}