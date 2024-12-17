package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CollectionTaskViewRepository extends JpaRepository<CollectionTaskView,Long>{
	
	@Query("select t from CollectionTaskView t where t.collectionid=?1")
	List<CollectionTaskView> getHistoryById(long id);

	@Query(value= "SELECT t.taskid FROM CollectionTaskView t where (t.projectid, t.assigntorole) "
			+ " in ( SELECT p.projectid,p.workflowlevel FROM ProjectWorkflowLevelMapping p"
			+ " LEFT JOIN Project pm ON p.projectid = pm.entityId"
			+ " where p.userid = ?1 and (pm.isDeleted =false) AND (pm.approvalStatus ='APPROVED'))")
	List<Long> getTask(Long profileId);

}