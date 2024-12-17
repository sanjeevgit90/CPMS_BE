package aurionpro.erp.ipms.ordermgmt.purchaseordertask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.TaskCountList;

public interface AllTaskViewRepository extends JpaRepository<AllTaskView, Long> {

	//@Query(value = "select count(id) from assetmgmt.all_task_view where po_id = :po_id and task_status= 'REJECTED'", nativeQuery = true)
	@Query(value = "select count(entityId) from AllTaskView where poId = ?1 and approvalStatus = 'REJECTED'")
	public int getRejectedCount(long id);

	@Query(value =  "SELECT t.entityid FROM ordermgmt.all_task_view t where (t.account_name, t.assigntorole) "
		 		+ " in ( SELECT p.projectid,p.workflowlevel FROM projectmgmt.project_workflowlevel_mapping p"
		 		+ " LEFT JOIN projectmgmt.project pm ON p.projectid = pm.entityid "
		 		+ " where p.userid = ?1 and (pm.isdeleted =false)AND (pm.approvalstatus ='APPROVED'))", nativeQuery = true )
	public List<Long> getTask(Long profileId);
	
	@Query(value =  "SELECT count(t.entityid), t.assigntorole as approvalLevel FROM ordermgmt.all_task_view t where t.approvalstatus='PENDING' and (t.account_name, t.assigntorole) "
	 		+ " in (SELECT p.projectid,p.workflowlevel FROM projectmgmt.project_workflowlevel_mapping p"
	 		+ " LEFT JOIN projectmgmt.project pm ON p.projectid = pm.entityid "
	 		+ " where p.userid = ?1 and (pm.isdeleted = false) AND (pm.approvalstatus = 'APPROVED')) group by t.assigntorole", nativeQuery = true)
	public List<TaskCountList> getTaskCount(Long profileId);

	@Query(value="select * from ordermgmt.potaskfilterlistbysp(?1,?2,?3,?4,?5,?6,?7)",  nativeQuery = true)
	public List<AllTaskView> getallpotaskbyfilterbysp(String purchaseOrderNo, Long accountName, Long supplierName,
			String approvalStatus, String assignToRole, String stagename, Long profileId);
}
