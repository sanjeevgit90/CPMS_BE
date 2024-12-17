package aurionpro.erp.ipms.ordermgmt.grntask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "grn_task_view", schema = "ordermgmt")
public class GrnTaskView {
	
	@Id
	@Column(name = "entityid")
	private Long entityId;
	
	@Column(name = "grn_id")
	private Long grnId;
    
    @Column(name = "grn_number")
    private String grnNumber;
    
    @Column(name = "grn_approval_status")
    private String grnApprovalStatus;
    
    @Column(name = "project")
	private Long project;

    private String stageName;
    private String workflowName;
    private String assignToRole;
    private String assignToUser;
    private String approvalStatus;
    private String remark;
	private Long createdDate;
    private String createdBy;
    private Long updatedDate;
    private String updatedBy;
    private Boolean isDeleted;
    private Long organizationId;

	public Long getGrnId() {
		return grnId;
	}
	public void setGrnId(Long grnId) {
		this.grnId = grnId;
	}
	public Long getEntityId() {
		return entityId;
	}
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	public String getGrnNumber() {
		return grnNumber;
	}
	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
	}
	public String getGrnApprovalStatus() {
		return grnApprovalStatus;
	}
	public void setGrnApprovalStatus(String grnApprovalStatus) {
		this.grnApprovalStatus = grnApprovalStatus;
	}
	public Long getProject() {
		return project;
	}
	public void setProject(Long project) {
		this.project = project;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getAssignToRole() {
		return assignToRole;
	}
	public void setAssignToRole(String assignToRole) {
		this.assignToRole = assignToRole;
	}
	public String getAssignToUser() {
		return assignToUser;
	}
	public void setAssignToUser(String assignToUser) {
		this.assignToUser = assignToUser;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
