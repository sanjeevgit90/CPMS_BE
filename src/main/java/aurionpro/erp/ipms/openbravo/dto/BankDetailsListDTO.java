package aurionpro.erp.ipms.openbravo.dto;

import java.util.List;

public class BankDetailsListDTO {
	
	 private List<BankDetailDTO> bankDetails;
	    private String bankIfscCode;
	    private String accountType;
	    private String beneficiaryName;
	    private String bankAccountNumber;
	    private String reenterBankAccountNumber;
	    private String currency;
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
		
		public String getAccountType() {
			return accountType;
		}
		public void setAccountType(String accountType) {
			this.accountType = accountType;
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
