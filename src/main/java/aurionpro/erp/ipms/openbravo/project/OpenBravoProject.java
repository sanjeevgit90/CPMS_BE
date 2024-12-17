package aurionpro.erp.ipms.openbravo.project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.projectmgmt.projectmaster.Project;

@Entity
@Table(name = "ob_project_master", schema = "openbravo")
public class OpenBravoProject extends JKDEntityAudit {

	@Id
	@NotBlank(message = "Open bravo id is required")
	@Column(name = "open_bravo_id", length = 32)
	private String openBravoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
	private Project project;

	public String getOpenBravoId() {
		return openBravoId;
	}

	public void setOpenBravoId(String openBravoId) {
		this.openBravoId = openBravoId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
