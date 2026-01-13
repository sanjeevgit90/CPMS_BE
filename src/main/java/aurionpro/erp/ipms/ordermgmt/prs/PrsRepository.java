package aurionpro.erp.ipms.ordermgmt.prs;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	

	 @Modifying
	    @Transactional
	    @Query(
	        "UPDATE Prs p " +
	        "SET p.bookEntryNo = :bookEntryNo " +
	        "WHERE p.id = :entityId"
	    )
	    int updateBookEntryNo(
	            @Param("entityId") Long entityId,
	            @Param("bookEntryNo") String bookEntryNo
	    );

	    @Modifying
	    @Transactional
	    @Query(
	        "UPDATE Prs p " +
	        "SET p.paymentDoneDate = :paymentDoneDate " +
	        "WHERE p.id = :entityId"
	    )
	    int updatePaymentDoneDate(
	            @Param("entityId") Long entityId,
	            @Param("paymentDoneDate") Long paymentDoneDate
	    );

	    @Modifying
	    @Transactional
	    @Query(
	        "UPDATE Prs p " +
	        "SET p.paymentBookEntryNo = :paymentBookEntryNo " +
	        "WHERE p.id = :entityId"
	    )
	    int updatePaymentBookEntryNo(
	            @Param("entityId") Long entityId,
	            @Param("paymentBookEntryNo") String paymentBookEntryNo
	    );
	    
	    @Modifying
	    @Transactional
	    @Query(
	        "UPDATE Prs p " +
	        "SET p.paymentDoneDate = :paymentDoneDate, " +
	        "    p.paymentBookEntryNo = :paymentBookEntryNo " +
	        "WHERE p.id = :entityId"
	    )
	    int updatePaymentDoneDateAndBookEntryNo(
	            @Param("entityId") Long entityId,
	            @Param("paymentDoneDate") Long paymentDoneDate,
	            @Param("paymentBookEntryNo") String paymentBookEntryNo
	    );

}