package aurionpro.erp.ipms.ticketmgmt.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name = "currencymaster", schema = "ticketmgmt")
public class CurrencyMaster extends JKDEntityAudit {
    
	@Id
	@Column(name = "currency_name",unique = true,length=50)
	private String currencyName;
	
	@Column(name = "organisation_id")
	private String organisationId;
	
	@Column(name = "currency_symbol",length=10)
	private String currencySymbol;
	
	@Column(name = "inr_value")
	private Double inrValue;

	public Double getInrValue() {
		return inrValue;
	}

	public void setInrValue(Double inrValue) {
		this.inrValue = inrValue;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName == null? null : currencyName.trim();
	}

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
}
