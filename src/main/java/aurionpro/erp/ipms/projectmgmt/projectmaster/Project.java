package aurionpro.erp.ipms.projectmgmt.projectmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="project", schema = "projectmgmt")
public class Project extends JKDEntityAuditWithId {

	@Column(length = 50)
	private String projectType;
	
	@Column(length = 50)
	private String projectName;
	
	@Column(length = 50)
	private String clientName;

	@Column(length = 50)
	private String departmentName;
	
	@Column(length = 50)
	private String projectLocation;
	
	@Column(length = 500)
	private String remark;
	
	private Long projectStartDate;
	
	private Long projectStopDate;

	@Column(length = 10)
	private String projectStatus;

	private Boolean poAttached;
	
	private Boolean projectPlan;

	@Column(length = 250)
	private String poAttachment;
	
	@Column(length = 250)
	private String planAttachment;
    
  //  @Size(min = 0, max = 100)
	private Double percentagePaymentReceived;
	//@Size(min = 0, max = 100)
	private Double percentageCompleted;
    
    @Column(length = 50)
	private String approvalStatus;
	
	@Column(length = 10)
	private String projectPin;
	
    @Column(length = 20)
	private String completionStatus;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double budget;
	
	private Long organization;
	
	@Column(length = 50)
	private String practiceName;
	
	private Long projectManager;
	
	private Long accountManager;
	
    public Project() {
    }
    
    public Project(Long id, String projectName) {
    	this.setEntityId(id);
    	this.projectName = projectName;
    	
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
        this.projectName = projectName == null ? null: projectName.trim();
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
}