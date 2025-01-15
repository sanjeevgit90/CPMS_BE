package aurionpro.erp.ipms.openbravo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PartyMasterDTO {
	
	    @JsonProperty("case_id")
	    private String caseId;
	    
	    @JsonProperty("time_zone")
	    private String timeZone;
	    
	    @JsonProperty("country_name")
	    private String countryName;
	    
	    @JsonProperty("merchant_details")
	    private MerchantDetailsDTO merchantDetails; 
	    
	    @JsonProperty("company_details")
	    private CompanyDetailsDTO companyDetails;   
	    
	    @JsonProperty("business_details")
	    private BusinessDetailsDTO businessDetails;
	    
	    @JsonProperty("gst_details")
	    private List<GstDetailDTO> gstDetails;
	    
	    @JsonProperty("bank_details")
	    private BankDetailsListDTO bankDetails;
	    
	    
		public String getCaseId() {
			return caseId;
		}
		public void setCaseId(String caseId) {
			this.caseId = caseId;
		}
		public String getTimeZone() {
			return timeZone;
		}
		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public MerchantDetailsDTO getMerchantDetails() {
			return merchantDetails;
		}
		public void setMerchantDetails(MerchantDetailsDTO merchantDetails) {
			this.merchantDetails = merchantDetails;
		}
		public CompanyDetailsDTO getCompanyDetails() {
			return companyDetails;
		}
		public void setCompanyDetails(CompanyDetailsDTO companyDetails) {
			this.companyDetails = companyDetails;
		}
		public BusinessDetailsDTO getBusinessDetails() {
			return businessDetails;
		}
		public void setBusinessDetails(BusinessDetailsDTO businessDetails) {
			this.businessDetails = businessDetails;
		}

		public List<GstDetailDTO> getGstDetails() {
			return gstDetails;
		}
		public void setGstDetails(List<GstDetailDTO> gstDetails) {
			this.gstDetails = gstDetails;
		}
		public BankDetailsListDTO getBankDetails() {
			return bankDetails;
		}
		public void setBankDetails(BankDetailsListDTO bankDetails) {
			this.bankDetails = bankDetails;
		}
	    
	    

}
