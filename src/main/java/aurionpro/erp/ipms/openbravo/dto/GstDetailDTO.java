package aurionpro.erp.ipms.openbravo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GstDetailDTO {
	private String city;
    private String email;
    private String state;
    private String country;
    
    @JsonProperty("pin_code")
    private String pinCode;
    
    @JsonProperty("gst_state")
    private String gstState;
    
    @JsonProperty("gst_number")
    private String gstNumber;
    
    @JsonProperty("address_type")
    private String addressType;
    
    @JsonProperty("phone_number")
    private PhoneNumberDTO phoneNumber; 
    
    @JsonProperty("address_line_1")
    private String addressLine1;
    
    @JsonProperty("address_line_2")
    private String addressLine2;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getGstState() {
		return gstState;
	}
	public void setGstState(String gstState) {
		this.gstState = gstState;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public PhoneNumberDTO getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumberDTO phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
    
    

}
