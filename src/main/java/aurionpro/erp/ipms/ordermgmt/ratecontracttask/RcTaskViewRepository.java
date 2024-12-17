package aurionpro.erp.ipms.ordermgmt.ratecontracttask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.TaskCountList;

public interface RcTaskViewRepository extends JpaRepository<RcTaskView, Long> {

	public List<RcTaskView> findByRcId(long rcId);

	 @Query(value =  "SELECT count(t.entityid), t.assigntorole as approvalLevel FROM ordermgmt.rc_task_view t where t.approvalstatus='PENDING' and (t.account_name, t.assigntorole) "
		 		+ " in ( SELECT p.projectid,p.workflowlevel FROM projectmgmt.project_workflowlevel_mapping p"
		 		+ " LEFT JOIN projectmgmt.project pm ON p.projectid = pm.entityid "
		 		+ " where p.userid = ?1 and (pm.isdeleted =false) AND (pm.approvalstatus ='APPROVED')) group by t.assigntorole", nativeQuery = true )
	public List<TaskCountList> getRCTaskCount(Long profileId);
	 
	@Query(value =  "select distinct(p.createdby) from ordermgmt.rc_task_view p where p.po_id = ?1 and p.createdby is not null", nativeQuery = true)
	public List<String> getAllEmailListFromRcTask(Long poId);
}
