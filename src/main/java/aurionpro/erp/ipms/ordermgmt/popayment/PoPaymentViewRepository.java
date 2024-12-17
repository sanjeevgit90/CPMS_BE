package aurionpro.erp.ipms.ordermgmt.popayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PoPaymentViewRepository extends JpaRepository<PoPaymentView, Long> {

	@Query(value="select * from ordermgmt.payment_schedule_view p "
			+ "where EXTRACT(month FROM to_timestamp(p.schedule_date/1000)) = ?1 AND EXTRACT(year FROM to_timestamp(p.schedule_date/1000)) = ?2 and p.payment_date is null", nativeQuery = true)
	 List<PoPaymentView> getMonthWisePaymentReport(int month, int year);
}
