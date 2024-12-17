package aurionpro.erp.ipms.jkdframework.officelocation;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="officelocation",schema = "authentication",uniqueConstraints = @UniqueConstraint(columnNames ={"officename"}))
public class OfficeLocation extends JKDEntityAuditWithId {

	@NotEmpty(message = "office name is required")
    @Column(length = 100)
    private String officename;
	
	@NotEmpty(message = "Office Code cannot be Blank")
    @Column(length = 10)
    private String officecode;
	
	@NotEmpty(message = "State is required")
    @Column(length = 100)
    private String state;
	
	@NotEmpty(message = "District is required")
    @Column(length = 100)
    private String district;
	
	@NotEmpty(message = "Country required")
    @Column(length = 100)
    private String country;
	
	@Column(length = 500)
    private String address;
  

    public OfficeLocation() {
    }


	public String getOfficename() {
		return officename;
	}


	public void setOfficename(String officename) {
		this.officename = officename;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getOfficecode() {
		return officecode;
	}


	public void setOfficecode(String officecode) {
		this.officecode = officecode;
	}

	
  
}