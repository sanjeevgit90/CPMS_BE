package aurionpro.erp.ipms.jkdframework.workflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StageRepository extends JpaRepository<WorkflowStage,Long>  {

    //List<WorkflowStage> findByWorkflowNameAndStageNameAndApprovalStatus(WorkflowMaster workflowName,String stageName, String approvalStatus);

    @Query("select s from WorkflowStage s where s.stageName = ?1 and s.approvalStatus=?2 and s.workflowName.workflowName=?3")
	public WorkflowStage GetWorkflowStage(String stageName, String approvalStatus,String workflowName);
}