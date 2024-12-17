package aurionpro.erp.ipms.ordermgmt.ratecontracttask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RcTaskMasterRepository extends JpaRepository<RcTaskMaster, Long> {

	List<RcTaskMaster> findByRcId(Long rcId);

	/*@Query("select * from PoTaskMaster where ")
	public getSingleTask();*/
}
