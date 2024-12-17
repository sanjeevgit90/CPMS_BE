package aurionpro.erp.ipms.jkdframework.workflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="workflowconfig")
public class WorkflowConfig extends JKDEntityAudit {
	
	@Id
	@Column(name = "workflow_name")
    private String workflowName;
	
	@NotEmpty(message = "Workflow type is required")
	@Column(name = "workflow_type")
	private String workflowType;
	
	@Column(name = "project_type")
    private String projectType;
	
	@Column(name = "organisation")
    private String organisation;

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
}
