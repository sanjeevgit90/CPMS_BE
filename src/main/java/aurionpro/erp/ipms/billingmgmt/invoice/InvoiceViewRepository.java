package aurionpro.erp.ipms.billingmgmt.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface InvoiceViewRepository extends JpaRepository<InvoiceView,Long>{

	 @Query(value="select distinct(trim(to_char(to_timestamp(invoicedate / 1000.0), 'Month'))) as selectionvalue from InvoiceView "
	 		+ " where (invoicestatus='ACKNOWLEDGED' or invoicestatus='SUBMISSION_PENDING' ) "
	 		+ " and (TO_DATE(to_char(to_timestamp(invoicedate / 1000.0), 'DD/MM/YYYY'), 'DD/MM/YYYY') >= TO_DATE(?1, 'YYYY-MM-DD') "
	 		+ " and TO_DATE(to_char(to_timestamp(invoicedate / 1000.0), 'DD/MM/YYYY'), 'DD/MM/YYYY') < TO_DATE(?2, 'YYYY-MM-DD')) "
	 		+ " group by invoicedate")
     List<SelectionList> monthlyOfBillingReport(String startYear, String endYear);

	 @Query(value="select * from billingmgmt.invoiceview i"
	 		+ " where (invoicestatus='ACKNOWLEDGED' or invoicestatus='SUBMISSION_PENDING') "
	 		+ " and trim(to_char(to_timestamp(invoicedate / 1000.0), 'Month')) \\:\\: varchar = ?1",nativeQuery = true)
	 List<InvoiceView> monthlyBillingReport(String month);

}