package aurionpro.erp.ipms.ordermgmt.purchaseordertask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.TaskCountList;

public interface PoTaskViewRepository extends JpaRepository<PoTaskView, Long> {

	public List<PoTaskView> findByPoId(long poId);

	 @Query(value =  "SELECT count(t.entityid), t.assigntorole as approvalLevel FROM ordermgmt.po_task_view t where t.approvalstatus='PENDING' and (t.account_name, t.assigntorole) "
		 		+ " in ( SELECT p.projectid,p.workflowlevel FROM projectmgmt.project_workflowlevel_mapping p"
		 		+ " LEFT JOIN projectmgmt.project pm ON p.projectid = pm.entityid "
		 		+ " where p.userid = ?1 and (pm.isdeleted =false) AND (pm.approvalstatus ='APPROVED')) group by t.assigntorole", nativeQuery = true )
	public List<TaskCountList> getPOTaskCount(Long profileId);
	 
	@Query(value =  "select distinct(p.createdby) from ordermgmt.po_task_view p where p.po_id = ?1 and p.createdby is not null", nativeQuery = true)
	public List<String> getAllEmailListFromPoTask(Long poId);
}
