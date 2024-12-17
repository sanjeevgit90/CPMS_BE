package aurionpro.erp.ipms.ordermgmt.vendorinvoicepayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendorInvoicePaymentRepository extends JpaRepository<VendorInvoicePayment, Long> {

	public List<VendorInvoicePayment> findByPrsId(long prsId);
	
	@Query("select sum(p.amount) as totalPayments from VendorInvoicePayment p where p.prsId = ?1 and p.entityId <> ?2")
	public Object getTotalPaymentsOfPrsById(long prsId, long paymentId);
}