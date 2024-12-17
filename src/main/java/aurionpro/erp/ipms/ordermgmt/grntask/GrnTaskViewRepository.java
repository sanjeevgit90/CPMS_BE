package aurionpro.erp.ipms.ordermgmt.grntask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GrnTaskViewRepository extends JpaRepository<GrnTaskView, Long> {

	public List<GrnTaskView> findByGrnId(long grnId);
	
	@Query(value =  "SELECT t.entityid FROM ordermgmt.grn_task_view t where (t.project, t.assigntorole) "
	 		+ "in ( SELECT p.projectid,p.workflowlevel FROM projectmgmt.project_workflowlevel_mapping p "
	 		+ "LEFT JOIN projectmgmt.project pm ON p.projectid = pm.entityid "
	 		+ "where p.userid = ?1 and (pm.isdeleted =false) AND (pm.approvalstatus ='APPROVED'))", nativeQuery = true)
	List<Long> getTask(Long profileId);
}
