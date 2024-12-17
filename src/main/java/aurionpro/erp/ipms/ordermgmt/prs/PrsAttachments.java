package aurionpro.erp.ipms.ordermgmt.prs;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PrsAttachments {

	@NotNull(message = "Prs Id is required")
	private String prsid;
	
	@NotEmpty(message = "Attachment is required")
	@Column(length = 250)
	 private String attachments;


	public String getPrsid() {
		return prsid;
	}


	public void setPrsid(String prsid) {
		this.prsid = prsid;
	}


	public String getAttachments() {
		return attachments;
	}


	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	
}
