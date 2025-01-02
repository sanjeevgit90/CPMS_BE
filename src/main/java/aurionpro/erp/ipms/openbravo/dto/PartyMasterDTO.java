package aurionpro.erp.ipms.openbravo.dto;

public class PartyMasterDTO {
	    private String caseId;
	    private String timeZone;
	    private String countryName;
	    private MerchantDetailsDTO merchantDetails;    
	    private CompanyDetailsDTO companyDetails;      
	    private BusinessDetailsDTO businessDetails;
	    private GstDetailsListDTO gstDetails;
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
		public GstDetailsListDTO getGstDetails() {
			return gstDetails;
		}
		public void setGstDetails(GstDetailsListDTO gstDetails) {
			this.gstDetails = gstDetails;
		}
		public BankDetailsListDTO getBankDetails() {
			return bankDetails;
		}
		public void setBankDetails(BankDetailsListDTO bankDetails) {
			this.bankDetails = bankDetails;
		}
	    
	    

}
