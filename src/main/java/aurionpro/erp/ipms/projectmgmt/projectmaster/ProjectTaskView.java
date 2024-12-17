package aurionpro.erp.ipms.projectmgmt.projectmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping.ProjectWorkflowLevelMapping;

@Entity
@Table(name="project_task_view", schema = "projectmgmt")
@Immutable
public class ProjectTaskView {

	@Id
    private Long taskid;
	
	 private Long projectid;
	
	private String updatedby;
		
    private String workflowname;
	
	private String projectType;
	
	private String projectName;
	
	private String clientName;

	private String departmentName;
	
	private String projectLocation;
	
	private String remark;
	
	private Long projectStartDate;
	
	private Long projectStopDate;

	private String projectStatus;

	private Boolean poAttached;
	
	private Boolean projectPlan;

	private String poAttachment;
	
	private String planAttachment;
    
	private Double percentagePaymentReceived;
	 
	private Double percentageCompleted;
    
    private String approvalStatus;
	
	private String projectPin;
	
    private String completionStatus;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double budget;
	
	private Long organization;
	
	private String practiceName;
	
	private Long projectManager;
	
	private Long accountManager;
	
	private String projectManagerName;
	
	private String accountManagerName;
	
	private String assigntorole;
	
	private String assigntouser;
	
	@Transient
	private ProjectWorkflowLevelMapping levelList;

    public ProjectTaskView() {
    }

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Long projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Long getProjectStopDate() {
		return projectStopDate;
	}

	public void setProjectStopDate(Long projectStopDate) {
		this.projectStopDate = projectStopDate;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Boolean getPoAttached() {
		return poAttached;
	}

	public void setPoAttached(Boolean poAttached) {
		this.poAttached = poAttached;
	}

	public Boolean getProjectPlan() {
		return projectPlan;
	}

	public void setProjectPlan(Boolean projectPlan) {
		this.projectPlan = projectPlan;
	}

	public String getPoAttachment() {
		return poAttachment;
	}

	public void setPoAttachment(String poAttachment) {
		this.poAttachment = poAttachment;
	}

	public String getPlanAttachment() {
		return planAttachment;
	}

	public void setPlanAttachment(String planAttachment) {
		this.planAttachment = planAttachment;
	}

	public Double getPercentagePaymentReceived() {
		return percentagePaymentReceived;
	}

	public void setPercentagePaymentReceived(Double percentagePaymentReceived) {
		this.percentagePaymentReceived = percentagePaymentReceived;
	}

	public Double getPercentageCompleted() {
		return percentageCompleted;
	}

	public void setPercentageCompleted(Double percentageCompleted) {
		this.percentageCompleted = percentageCompleted;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getProjectPin() {
		return projectPin;
	}

	public void setProjectPin(String projectPin) {
		this.projectPin = projectPin;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public Long getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Long projectManager) {
		this.projectManager = projectManager;
	}

	public Long getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(Long accountManager) {
		this.accountManager = accountManager;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	public String getAccountManagerName() {
		return accountManagerName;
	}

	public void setAccountManagerName(String accountManagerName) {
		this.accountManagerName = accountManagerName;
	}

	public ProjectWorkflowLevelMapping getLevelList() {
		return levelList;
	}

	public void setLevelList(ProjectWorkflowLevelMapping levelList) {
		this.levelList = levelList;
	}

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public String getWorkflowname() {
		return workflowname;
	}

	public void setWorkflowname(String workflowname) {
		this.workflowname = workflowname;
	}

	public String getAssigntorole() {
		return assigntorole;
	}

	public void setAssigntorole(String assigntorole) {
		this.assigntorole = assigntorole;
	}

	public String getAssigntouser() {
		return assigntouser;
	}

	public void setAssigntouser(String assigntouser) {
		this.assigntouser = assigntouser;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	
    
}