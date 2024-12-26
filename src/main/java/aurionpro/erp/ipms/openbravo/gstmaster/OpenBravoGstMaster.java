package aurionpro.erp.ipms.openbravo.gstmaster;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.vendor.gstmaster.GstMaster;

@Entity
@Table(name = "ob_gst_master", schema = "openbravo")
public class OpenBravoGstMaster extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gst_id")
	private GstMaster gst;


	public String getOpenBravoId() {
		return openBravoId;
	}


	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}


	public GstMaster getGst() {
		return gst;
	}


	public void setGst(GstMaster gst) {
		this.gst = gst;
	}
	
	
	
}
