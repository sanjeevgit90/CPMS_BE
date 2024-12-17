package aurionpro.erp.ipms.ordermgmt.purchase;

import java.io.Serializable;

public class PurchaseOrderAttachmentsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String base64data;
	private String src;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBase64data() {
		return base64data;
	}
	public void setBase64data(String base64data) {
		this.base64data = base64data;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
	
}
