package aurionpro.erp.ipms.billingmgmt.invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface InvoiceRepository extends JpaRepository<InvoiceMaster,Long>{

	@Query("select a from InvoiceMaster a where a.entityId=?1")
	InvoiceMaster viewInvoice(Long id);

	@Query("select i from InvoiceMaster i where i.milestoneno =?2 and i.projectid=?1")
	List<InvoiceMaster> getInvoiceByMilestoneid(Long projectid, String milestoneno);

    @Query("select i.entityId as selectionid,  i.invoiceno as selectionvalue from InvoiceMaster i where i.projectid=?1 and i.invoicestatus='ACKNOWLEDGED' order by  i.invoiceno ")
    List<SelectionList> getInvoiceListFromProject(Long projectid);

    @Query("select i.entityId as selectionid,  i.invoiceno as selectionvalue from InvoiceMaster i where i.projectid=?1 and i.invoicestatus='ACKNOWLEDGED' and i.isDeleted = false order by  i.invoiceno")
    List<SelectionList> getActiveInvoiceFromProject(Long projectid);

    @Query("select i from InvoiceMaster i where i.projectid=?1")
     List<InvoiceMaster> invoiceFromProject(Long projectid);

}