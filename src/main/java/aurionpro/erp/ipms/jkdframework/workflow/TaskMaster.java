package aurionpro.erp.ipms.jkdframework.workflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "taskmaster")
@Inheritance(strategy = InheritanceType.JOINED)
public class TaskMaster extends JKDEntityAuditWithId implements Cloneable {

    @NotBlank(message = "Stage name cannot be Blank")
    @Column(nullable = false, updatable = false)
    private String stageName;

    @NotBlank(message = "Workflow name cannot be Blank")
    @Column(nullable = false, updatable = false)
    private String workflowName;

    @Column(updatable = false)
    private String assignToRole;

    @Column(updatable = false)
    private String assignToUser;

    private String approvalStatus;
    private String remark;

    public TaskMaster() {
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    

}