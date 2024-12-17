package aurionpro.erp.ipms.openbravo.constants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.ordermgmt.constant.ConstantMaster;

@Entity
@Table(name = "ob_constant_master", schema = "openbravo")
public class OpenBravoConstantMaster extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "constant_id")
	private ConstantMaster constant;

	public String getOpenBravoId() {
		return openBravoId;
	}

	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}

	public ConstantMaster getConstant() {
		return constant;
	}

	public void setConstant(ConstantMaster constant) {
		this.constant = constant;
	}
}
