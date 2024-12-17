package aurionpro.erp.ipms.projectmgmt.projectmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "project_approval_task", schema = "projectmgmt")
public class ProjectApprovalTask extends TaskMaster {

	@Column(length = 50 ,nullable = false)
    private String projectname;
	
	private Long projectid;
    
    public ProjectApprovalTask() {

    }

   	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}


	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}


	@Override
    protected Object clone() throws CloneNotSupportedException {
        ProjectApprovalTask tm=(ProjectApprovalTask)super.clone();
        tm.setRemark(null);
        //tm.setProbableUse(null);

        return tm;
    }

    
}