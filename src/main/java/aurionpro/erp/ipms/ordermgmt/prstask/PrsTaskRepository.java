package aurionpro.erp.ipms.ordermgmt.prstask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrsTaskRepository extends JpaRepository<PrsTask, Long> {
	
	
	@Query(value =  "SELECT t.taskid FROM ordermgmt.prs_task_view t where (t.project_name, t.assigntorole) "
		 		+ " in ( SELECT p.projectid,p.workflowlevel FROM projectmgmt.project_workflowlevel_mapping p"
		 		+ " LEFT JOIN projectmgmt.project pm ON p.projectid = pm.entityid "
		 		+ " where p.userid = ?1 and (pm.isdeleted =false) AND (pm.approvalstatus ='APPROVED'))", nativeQuery = true )
	List<Long> getTask(Long profileId);

	List<PrsTask> findByPrsId(long prsId);

	//public List<PrsTask> findByPrsId(long prsId);
}
