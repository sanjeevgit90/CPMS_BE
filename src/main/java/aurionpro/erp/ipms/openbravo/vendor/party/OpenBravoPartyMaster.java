package aurionpro.erp.ipms.openbravo.vendor.party;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@Entity
@Table(name = "ob_party_master", schema = "openbravo")
public class OpenBravoPartyMaster extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "party_id")
	private PartyMaster party;
	
	@Column(name = "client_id")
	private Long clientId;
	
	/* already added in party master
	@Column(name = "contact_id", length = 32)
	private String contactId;
	
	@Column(name = "price_list", length = 32)
	private String priceList;*/
	
	

	public String getOpenBravoId() {
		return openBravoId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}

	public PartyMaster getParty() {
		return party;
	}

	public void setParty(PartyMaster party) {
		this.party = party;
	}
}
