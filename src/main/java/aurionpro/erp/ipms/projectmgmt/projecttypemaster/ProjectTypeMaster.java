package aurionpro.erp.ipms.projectmgmt.projecttypemaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="project_type_master", schema = "projectmgmt")
public class ProjectTypeMaster extends JKDEntityAudit {

	@Id
	@Column(length = 50)
	private String projecttype;
	
	/*
	@Column(length = 50)
	private String groupHead;
	
	@Column(length = 50)
	private String buHead;
	*/
	
    public ProjectTypeMaster() {
    }

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype == null ? null: projecttype;
	}
   

}