package aurionpro.erp.ipms.openbravo.organisation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.jkdframework.organization.Organization;

@Entity
@Table(name="ob_organisation", schema = "openbravo")
public class OpenBravoOrganisation extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	@Column(name = "document_type")
	private String documentType;
	
	@Column(name = "transaction_document_type")
	private String transactionDocumentType;
	
	@Column(name = "warehouse")
	private String warehouse;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
	private Organization org;

	public String getOpenBravoId() {
		return openBravoId;
	}

	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getTransactionDocumentType() {
		return transactionDocumentType;
	}

	public void setTransactionDocumentType(String transactionDocumentType) {
		this.transactionDocumentType = transactionDocumentType;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
}