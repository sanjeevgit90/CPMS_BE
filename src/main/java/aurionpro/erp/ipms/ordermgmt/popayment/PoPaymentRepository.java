package aurionpro.erp.ipms.ordermgmt.popayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PoPaymentRepository extends JpaRepository<PoPayment, Long> {

	public List<PoPayment> findByPurchaseOrderNo(long id);
}