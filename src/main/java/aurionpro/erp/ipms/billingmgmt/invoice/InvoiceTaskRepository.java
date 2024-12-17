package aurionpro.erp.ipms.billingmgmt.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceTaskRepository extends JpaRepository<InvoiceTaskMaster,Long>{

	List<InvoiceTaskMaster> findByInvoiceid(Long id);

}