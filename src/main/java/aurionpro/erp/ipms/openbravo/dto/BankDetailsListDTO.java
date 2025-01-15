package aurionpro.erp.ipms.openbravo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankDetailsListDTO {
	
	    @JsonProperty("bank_details")
	    private List<BankDetailDTO> bankDetails;
	    
	    @JsonProperty("bank_ifsc_code")
	    private String bankIfscCode;

	    private Integer accounttype;
	    
	    @JsonProperty("beneficiary_name")
	    private String beneficiaryName;
	    
	    @JsonProperty("bank_account_number")
	    private String bankAccountNumber;
	    
	    @JsonProperty("reenterbank_account_number")
	    private String reenterBankAccountNumber;
	    
	    private String currency;
	    
	    @JsonProperty("bank_name")
	    private String bankName;
		public List<BankDetailDTO> getBankDetails() {
			return bankDetails;
		}
		public void setBankDetails(List<BankDetailDTO> bankDetails) {
			this.bankDetails = bankDetails;
		}
		public String getBankIfscCode() {
			return bankIfscCode;
		}
		public void setBankIfscCode(String bankIfscCode) {
			this.bankIfscCode = bankIfscCode;
		}
		public String getBeneficiaryName() {
			return beneficiaryName;
		}
		
	
		public Integer getAccounttype() {
			return accounttype;
		}
		public void setAccounttype(Integer accounttype) {
			this.accounttype = accounttype;
		}
		public void setBeneficiaryName(String beneficiaryName) {
			this.beneficiaryName = beneficiaryName;
		}
		public String getBankAccountNumber() {
			return bankAccountNumber;
		}
		public void setBankAccountNumber(String bankAccountNumber) {
			this.bankAccountNumber = bankAccountNumber;
		}
		public String getReenterBankAccountNumber() {
			return reenterBankAccountNumber;
		}
		public void setReenterBankAccountNumber(String reenterBankAccountNumber) {
			this.reenterBankAccountNumber = reenterBankAccountNumber;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
	    
	    

}
