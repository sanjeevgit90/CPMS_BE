package aurionpro.erp.ipms.ticketmgmt.supportteam;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "supportteam", schema = "ticketmgmt")
public class SupportTeam extends JKDEntityAudit { 
        
	@Id
	@NotEmpty
	@Column(name = "employee_id",nullable = false)
	private String employeeId;

	@NotEmpty
	@Column(name = "first_name",nullable = false)
	private String firstName;
	
	@NotEmpty
	@Column(name = "last_name",nullable = false)
	private String lastName;
	
	@NotEmpty
	@Column(name = "vehicle_support_type",nullable = false)
	private String vehicleSupportType;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private Integer pincode;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "unique_site_id")
	private String usid;

    @Column(name = "service_start_date",nullable = false)
	private Long serviceStartDate;

    @Column(name = "id_proof_attachment",nullable = false)
	private String idProof;
	
	@Column(name = "address_proof_attachment")
	private String addressProof;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVehicleSupportType() {
		return vehicleSupportType;
	}

	public void setVehicleSupportType(String vehicleSupportType) {
		this.vehicleSupportType = vehicleSupportType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
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

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	

	public Long getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Long serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public String getAddressProof() {
		return addressProof;
	}

	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}
	
	

		
}

