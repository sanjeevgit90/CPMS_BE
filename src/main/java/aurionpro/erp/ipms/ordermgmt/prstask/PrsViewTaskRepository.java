package aurionpro.erp.ipms.ordermgmt.prstask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrsViewTaskRepository extends JpaRepository<PrsTaskView, Long> {


	@Query("select p from PrsTaskView p where p.approvalStatus <> 'PENDING'")
	public List<PrsTaskView> getAllPrsHistoryTasks();
	
	@Query("select p from PrsTaskView p where p.prsId = ?1")
	public List<PrsTaskView> getPrsTaskHistoryById(long prsId);
	
	@Query("select distinct(p.createdBy) from PrsTaskView p where p.prsId = ?1 and p.createdBy is not null")
	public List<String> getAllEmailListFromPrsTask(Long prsId);
}