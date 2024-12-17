package aurionpro.erp.ipms.openbravo.currency;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.ticketmgmt.currency.CurrencyMaster;

@Entity
@Table(name = "ob_currency_master", schema = "openbravo")
public class OpenBravoCurrencyMaster extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_name")
	private CurrencyMaster currency;

	public String getOpenBravoId() {
		return openBravoId;
	}

	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}

	public CurrencyMaster getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyMaster currency) {
		this.currency = currency;
	}
}
