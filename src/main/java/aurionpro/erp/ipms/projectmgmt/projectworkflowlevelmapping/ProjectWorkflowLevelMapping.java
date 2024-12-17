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
@Table(name="project_workflowlevel_mapping", schema = "projectmgmt")
public class ProjectWorkflowLevelMapping {

	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ipms_seq")
	    @SequenceGenerator(name = "ipms_seq", sequenceName ="entity_sequence", allocationSize = 1, initialValue = 1)
	    private Long entityId;
	 
	@NotNull(message = "Project is required")
	private Long projectid;
	
	@NotEmpty(message = "Level is required")
	@Column(length = 50)
	private String workflowlevel;
		
	@NotNull(message = "Organization is required")
	private Long orgid;
	
	@NotNull(message = "User is required")
	private Long userid;
	
    public ProjectWorkflowLevelMapping() {
    }
    
    public ProjectWorkflowLevelMapping(String level, Long user) {
    	this.workflowlevel = level;
    	this.userid = user;
    }

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public String getWorkflowlevel() {
		return workflowlevel;
	}

	public void setWorkflowlevel(String workflowlevel) {
		this.workflowlevel = workflowlevel;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
    

}