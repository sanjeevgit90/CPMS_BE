package aurionpro.erp.ipms.vendor.gstmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@Entity
@Table(name = "gstmaster", schema = "ordermgmt")
public class GstMaster extends JKDEntityAuditWithId {

	@ManyToOne()
    @JoinColumn(name="party_id")
	private PartyMaster partyMasterParent;

	/*@Column(name = "party_id")
	private String partyId;*/	

	@Column(name = "state")
	private String state;

	@Column(name = "gst_no", length = 15)
	private String gstNo;

	@Column(name = "gst_no_attachment")
	private String gstNoAttachment;
	
	@Column(name = "attachment_file_name")
	private String attachmentFileName;
	
	@Column(name = "status", length = 10)
	private String status;
	
	@Transient
	private String stateName;
	
	@Transient
	private String partyId;
	
	

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public PartyMaster getPartyMasterParent() {
		return partyMasterParent;
	}

	public void setPartyMasterParent(PartyMaster partyMasterParent) {
		this.partyMasterParent = partyMasterParent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getGstNoAttachment() {
		return gstNoAttachment;
	}

	public void setGstNoAttachment(String gstNoAttachment) {
		this.gstNoAttachment = gstNoAttachment;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
