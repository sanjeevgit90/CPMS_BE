package aurionpro.erp.ipms.billingmgmt.billingschedule;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface BillingScheduleRepository extends JpaRepository<BillingSchedule,ScheduleIdentity>{

	Optional<BillingSchedule> findByIdProjectidAndIdMilestoneno(Long projectid, String milestoneno);

	@Query("select bs.id.milestoneno as selectionid, bs.id.milestoneno as selectionvalue from BillingSchedule bs where bs.id.projectid=?1 order by bs.id.milestoneno")
	List<SelectionList> getBillingScheduleList(Long projectid);

	@Query("select bs.id.milestoneno as selectionid, bs.id.milestoneno as selectionvalue from BillingSchedule bs where bs.id.projectid=?1 and bs.isDeleted=false order by bs.id.milestoneno")
	List<SelectionList> getActiveBillingScheduleList(Long projectid);

	@Query("select bs from BillingSchedule bs where bs.id.projectid=?1 and bs.isDeleted=false")
	List<BillingSchedule> getScheduleList(Long projectid);

	@Query("select sum(bs.amountofbilling) from BillingSchedule bs where bs.id.projectid=?1 and bs.isDeleted=false")
	Double verifyMilestone(Long projectid);

}