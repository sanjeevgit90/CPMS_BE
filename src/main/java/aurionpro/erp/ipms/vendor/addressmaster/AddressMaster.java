package aurionpro.erp.ipms.vendor.addressmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@Entity
@Table(name = "addressmaster", schema = "ordermgmt")
public class AddressMaster extends JKDEntityAuditWithId {
	
	@ManyToOne()
    @JoinColumn(name="party_id")
	private PartyMaster partyMasterParent;
	
	/*@Column(name = "party_id")
	private String partyId;*/
	
	@Column(name = "address_type")
	private String addressType;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;
	
	@Column(name = "landmark")
	private String landmark;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "pin_code", length = 6)
	private String pinCode;
	
	@Column(name = "status", length = 10)
	private String status;
	
	//@Transient
	//private String cityName;
	
	//@Transient
	//private String districtName;
	
	//@Transient
	//private String stateName;
	
	@Column(name = "fulladdress", length = 500)
	private String fullAddress;
	
	@Column(name = "contactperson")
	private String contactPerson;
	
	@Column(name = "contactno", length = 10)
	private String contactNo;
	
	@Transient
	private String partyId;

	public PartyMaster getPartyMasterParent() {
		return partyMasterParent;
	}

	public void setPartyMasterParent(PartyMaster partyMasterParent) {
		this.partyMasterParent = partyMasterParent;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}*/

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
}
