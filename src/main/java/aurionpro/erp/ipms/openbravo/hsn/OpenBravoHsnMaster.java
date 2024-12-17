package aurionpro.erp.ipms.openbravo.hsn;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.assetmgmt.hsncodemaster.HsnCodeMaster;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name = "ob_hsn_master", schema = "openbravo")
public class OpenBravoHsnMaster extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hsn_id")
	private HsnCodeMaster hsnCode;

	public String getOpenBravoId() {
		return openBravoId;
	}

	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}

	public HsnCodeMaster getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(HsnCodeMaster hsnCode) {
		this.hsnCode = hsnCode;
	}
}
