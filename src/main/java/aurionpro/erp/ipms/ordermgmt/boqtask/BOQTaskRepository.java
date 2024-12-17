package aurionpro.erp.ipms.ordermgmt.boqtask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BOQTaskRepository extends JpaRepository<BOQTask, Long> {

	@Query("select b from BOQTask b where b.approvalStatus <> 'PENDING'")
	public List<BOQTask> getAllBoqHistoryTasks();
	
	@Query("select b from BOQTask b where b.boqId = ?1")
	public List<BOQTask> getBoqTaskHistoryById(long boqId);
}
