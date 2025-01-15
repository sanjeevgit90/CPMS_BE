package aurionpro.erp.ipms.openbravo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankDetailDTO {
	
	private String key;
    private String value;
    
    @JsonProperty("css_class")
    private String cssClass;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
    
    

}
