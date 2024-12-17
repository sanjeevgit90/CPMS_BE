package aurionpro.erp.ipms.billingmgmt.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface InvoiceTaskViewRepository extends JpaRepository<InvoiceTaskView,Long>{

	@Query("select t from InvoiceTaskView t where t.invoiceid=?1")
	List<InvoiceTaskView> getHistoryById(long id);

	@Query(value= "SELECT t.taskid FROM InvoiceTaskView t where (t.projectid, t.assigntorole) "
			+ " in ( SELECT p.projectid,p.workflowlevel FROM ProjectWorkflowLevelMapping p"
			+ " LEFT JOIN Project pm ON p.projectid = pm.entityId"
			+ " where p.userid = ?1 and (pm.isDeleted =false) AND (pm.approvalStatus ='APPROVED'))")
	List<Long> getTask(Long profileId);

}