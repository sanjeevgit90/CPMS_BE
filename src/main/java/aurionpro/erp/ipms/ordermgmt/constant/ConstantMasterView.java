package aurionpro.erp.ipms.ordermgmt.constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "constant_master_view", schema = "ordermgmt")
public class ConstantMasterView {

	@Id
	@Column(name = "entityid")
	private Long entityId;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "organisation_id")
	private Long organisationId;
	
	@Column(name = "organisation_name")
	private String organisationName;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
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
		this.value = value;
	}

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
}
