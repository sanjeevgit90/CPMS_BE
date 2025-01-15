package aurionpro.erp.ipms.openbravo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneNumberDTO {
	
	    private String number;
	    
	    @JsonProperty("isd_code")
	    private String isdCode;
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getIsdCode() {
			return isdCode;
		}
		public void setIsdCode(String isdCode) {
			this.isdCode = isdCode;
		}

	    
}
