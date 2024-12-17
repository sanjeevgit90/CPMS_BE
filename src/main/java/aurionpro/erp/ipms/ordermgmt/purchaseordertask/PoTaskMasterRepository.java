package aurionpro.erp.ipms.ordermgmt.purchaseordertask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PoTaskMasterRepository extends JpaRepository<PoTaskMaster, Long> {

	/*@Query("select * from PoTaskMaster where ")
	public getSingleTask();*/
	
	@Modifying
	@Transactional
	@Query("update TaskMaster set approvalStatus = 'PULLED', remark = 'Pulled By PO Head' where entityId = ?1")
	public void updateTaskPulledByPoHead(long taskId);
	
	@Query("select p from PoTaskMaster p where p.poId = ?1 and p.approvalStatus = ?2")
	public List<PoTaskMaster> getTaskByPoIdAndStatus(long poId, String status);

	public List<PoTaskMaster> findByPoId(Long poId);
}
