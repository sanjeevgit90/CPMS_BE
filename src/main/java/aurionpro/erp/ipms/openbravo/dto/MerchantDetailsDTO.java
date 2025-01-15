package aurionpro.erp.ipms.openbravo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MerchantDetailsDTO {
	
	    private String city;
	    private String email;
	    private String state;
	    private String country;
	    
	    @JsonProperty("pin_code")
	    private String pinCode;
	    
	    private String website;
	    
	    @JsonProperty("party_type")
	    private String partyType;
	    
	    @JsonProperty("address_type")
	    private String addressType;
	    
	    @JsonProperty("country_code")
	    private String countryCode;
	    
	    @JsonProperty("phone_number")
	    private String phoneNumber;
	    
	    private String organization;
	    
	    @JsonProperty("reg_address_line_1")
	    private String regAddressLine1;
	    
	    @JsonProperty("reg_address_line_2")
	    private String regAddressLine2;
	    
	    @JsonProperty("contact_person_name")
	    private String contactPersonName;
	    
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
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public String getPartyType() {
			return partyType;
		}
		public void setPartyType(String partyType) {
			this.partyType = partyType;
		}
		public String getAddressType() {
			return addressType;
		}
		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getOrganization() {
			return organization;
		}
		public void setOrganization(String organization) {
			this.organization = organization;
		}
		public String getRegAddressLine1() {
			return regAddressLine1;
		}
		public void setRegAddressLine1(String regAddressLine1) {
			this.regAddressLine1 = regAddressLine1;
		}
		public String getRegAddressLine2() {
			return regAddressLine2;
		}
		public void setRegAddressLine2(String regAddressLine2) {
			this.regAddressLine2 = regAddressLine2;
		}
		public String getContactPersonName() {
			return contactPersonName;
		}
		public void setContactPersonName(String contactPersonName) {
			this.contactPersonName = contactPersonName;
		}
	    
	    
	    

}
