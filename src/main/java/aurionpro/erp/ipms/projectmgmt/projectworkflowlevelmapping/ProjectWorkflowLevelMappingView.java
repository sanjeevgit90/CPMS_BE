package aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_workflowlevel_mapping_view", schema = "projectmgmt")
public class ProjectWorkflowLevelMappingView {

	 @Id
	 private Long entityId;
	 
	 private Long projectid;
	
	 private String workflowlevel;
		
	 private Long orgid;
	
	 private Long userid;
	 
	 private String orgname;
		
	 private String projectname;
		
	 private String username;
	
	 private String emailid;
	 
	 private String mobileappkey;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
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

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getMobileappkey() {
		return mobileappkey;
	}

	public void setMobileappkey(String mobileappkey) {
		this.mobileappkey = mobileappkey;
	}

}