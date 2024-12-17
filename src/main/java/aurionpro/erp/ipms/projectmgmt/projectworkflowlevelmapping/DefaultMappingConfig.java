package aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="default_mapping_config", schema = "projectmgmt")
public class DefaultMappingConfig {
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ipms_seq")
	    @SequenceGenerator(name = "ipms_seq", sequenceName ="entity_sequence", allocationSize = 1, initialValue = 1)
	    private Long entityId;
	 
	@NotEmpty(message = "Level is required")
	@Column(length = 50)
	private String workflowlevel;
		
	@NotNull(message = "Organization is required")
	private Long organizationid;
	
	@NotNull(message = "User is required")
	private Long userid;
	
	@Column(length = 50)
	private String projecttype;
	
    public DefaultMappingConfig() {
    }

	public String getWorkflowlevel() {
		return workflowlevel;
	}

	public void setWorkflowlevel(String workflowlevel) {
		this.workflowlevel = workflowlevel;
	}

	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Long organizationid) {
		this.organizationid = organizationid;
	}
	
	
    
}