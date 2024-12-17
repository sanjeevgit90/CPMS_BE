package aurionpro.erp.ipms.jkdframework.workflow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface WorkflowConfigRepository extends JpaRepository<WorkflowConfig,String> {

	@Query("select w.workflowName as selectionid, w.workflowName as selectionvalue from WorkflowConfig w where w.workflowType = ?1 order by w.workflowName")
    public List<SelectionList> selectionWorkflowListByWorkflowType(String workflowType);
	
	@Query("select w.workflowName as selectionid, w.workflowName as selectionvalue from WorkflowConfig w where w.workflowType = ?1 and w.projectType = ?2 and w.organisation = ?3 order by w.workflowName")
    public List<SelectionList> selectionWorkflowListByProjectTypeAndOrg(String workflowType, String projectType, String organisation);
	
	@Query("select w.workflowName as selectionid, w.workflowName as selectionvalue from WorkflowConfig w where w.workflowType = ?1 and w.projectType = ?2 order by w.workflowName")
    public List<SelectionList> selectionWorkflowListByProjectType(String workflowType, String projectType);
	
	@Query("select w.workflowName as selectionid, w.workflowName as selectionvalue from WorkflowConfig w where w.workflowType = ?1 and w.organisation = ?2 order by w.workflowName")
    public List<SelectionList> selectionWorkflowListByOrg(String workflowType, String organisation);
}
