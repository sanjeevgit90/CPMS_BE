package aurionpro.erp.ipms.ordermgmt.prs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface PrsRepository extends JpaRepository<Prs, Long> {

	public List<Prs> findByPrsNo(String prsNo);
	
	public List<Prs> findByPurchaseOrderNo(long poId);
	
	@Query("select sum(p.invoiceAmount) as prsAmount from Prs p where p.purchaseOrderNo = ?1 and p.entityId <> ?2 and p.approvalStatus='APPROVED'")
	public Object getTotalAmountOfPrsByPoId(long poId, long prsId);
	
	@Query("select p.entityId as selectionid, p.prsNo as selectionvalue from Prs p")
    public List<SelectionList> selectionPrsList();
	
	@Query(value="select p.attachments from ordermgmt.prs_attachment p where p.prsid = ?1", nativeQuery = true)
    public List<String> getAttachmentList(long prsId);
}