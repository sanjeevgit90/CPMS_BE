package aurionpro.erp.ipms.jkdframework.workflow;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="workflowmaster")
public class WorkflowMaster extends JKDEntityAudit {

    @Id
    private String workflowName;
    private String startStageName;
    private String assignToRole;
    private String assignToUser;
    private String entityStatus;

    @OneToMany(mappedBy = "workflowName", cascade = CascadeType.ALL)
    private List<WorkflowStage> workflowStages;

    public WorkflowMaster() {
    }

    public WorkflowMaster(String wfName) {
        this.workflowName=wfName;
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

    public String getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(String entityStatus) {
        this.entityStatus = entityStatus;
    }

    public List<WorkflowStage> getWorkflowStages() {
        return workflowStages;
    }

    public void setWorkflowStages(List<WorkflowStage> workflowStages) {
        this.workflowStages = workflowStages;
        if (this.workflowStages!=null)
            this.workflowStages.forEach(c->c.setWorkflowName(this));
    }

    public String getStartStageName() {
        return startStageName;
    }

    public void setStartStageName(String startStageName) {
        this.startStageName = startStageName;
    }

}