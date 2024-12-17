package aurionpro.erp.ipms.jkdframework.workflow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="workflowstage")
public class WorkflowStage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ipms_seq")
    @SequenceGenerator(name = "ipms_seq", sequenceName ="entity_sequence", allocationSize = 1, initialValue = 1)
    private Long stageId;
    
    private String stageName;

    @ManyToOne()
    @JoinColumn(name="workflowName")
    @JsonIgnoreProperties("workflowStages")
    private WorkflowMaster workflowName;

    private String nextStageName;
    private String approvalStatus;
    private String entityStatus;
    private String assignToRole;
    private String assignToUser;

    public WorkflowStage() {
    }

    
    public WorkflowMaster getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(WorkflowMaster workflowName) {
        this.workflowName = workflowName;
    }

    public String getNextStageName() {
        return nextStageName;
    }

    public void setNextStageName(String nextStageName) {
        this.nextStageName = nextStageName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(String entityStatus) {
        this.entityStatus = entityStatus;
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

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }


}