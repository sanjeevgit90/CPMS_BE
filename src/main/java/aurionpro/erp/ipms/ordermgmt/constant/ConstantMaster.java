package aurionpro.erp.ipms.ordermgmt.constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "constant_master", schema = "ordermgmt", uniqueConstraints = { @UniqueConstraint(columnNames={"type", "value","organisation_id"}) })
public class ConstantMaster extends JKDEntityAuditWithId {

	/*@Id
	@Column(name = "id", unique = true)
	private String id;*/
	
	@NotNull(message = "Type cannot be null.")
	@Column(name = "type")
	private String type;
	
	@NotNull(message = "Value cannot be null.")
	@Column(name = "value")
	private String value;
	
	@NotNull(message = "Organisation cannot be null.")
	@Column(name = "organisation_id")
	private Long organisationId;
	
	@Transient
	private String obOrganisationId;

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null? null : value.trim();
	}

	public String getObOrganisationId() {
		return obOrganisationId;
	}

	public void setObOrganisationId(String obOrganisationId) {
		this.obOrganisationId = obOrganisationId;
	}
}