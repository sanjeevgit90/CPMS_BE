package aurionpro.erp.ipms.projectmgmt.projectmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProjectApprovalTaskViewRepository extends JpaRepository<ProjectTaskView,Long>{

	 @Query(value =  "SELECT t.taskid FROM projectmgmt.project_task_view t where (t.projectid, t.assigntorole) "
	 		+ " in ( SELECT p.projectid,p.workflowlevel FROM projectmgmt.project_workflowlevel_mapping p"
	 		+ " LEFT JOIN projectmgmt.project pm ON p.projectid = pm.entityid "
	 		+ " where p.userid = ?1 and (pm.isdeleted =false))", nativeQuery = true )
	List<Long> getTaskList(Long profileId);
	 
	

}