package aurionpro.erp.ipms.billingmgmt.reports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.billingmgmt.billingschedule.ScheduleIdentity;


public interface BillingComparsionRepository extends JpaRepository<BillingComparsion,ScheduleIdentity>{

	//@Query("select b from BillingComparsion b where b.id.projectid = ?1" )
	@Query(value="select * from billingmgmt.billingcomparsion  where projectid = ?1", nativeQuery = true )
	List<BillingComparsion> billingComparsionReport(Long projectid);


}