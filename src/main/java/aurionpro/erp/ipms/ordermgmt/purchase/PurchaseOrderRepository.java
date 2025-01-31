package aurionpro.erp.ipms.ordermgmt.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractMaster;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderMaster, Long> {

	public List<PurchaseOrderMaster> findByPurchaseOrderNo(String purchaseOrderNo);
	
	@Query(value="select p.entityid as selectionid, p.purchase_order_no as selectionvalue from ordermgmt.purchase_order_master p"
			+ " where p.account_name in (select id from projectmgmt.getproject(?1)) order by p.purchase_order_no", nativeQuery = true)
    public List<SelectionList> selectionPoList(Long profileId);
	
	@Query(value="select p.entityid as selectionid, p.purchase_order_no as selectionvalue from ordermgmt.purchase_order_master p"
			+ " where p.account_name in (select id from projectmgmt.getproject(?1)) and p.approval_status = 'APPROVED' order by p.purchase_order_no", nativeQuery = true)
    public List<SelectionList> selectionApprovedPoList(Long profileId);
	
	/* DML not supported
	@Query("update PurchaseOrderMaster p set p.amendedPoId = ?2 where p.entityId = ?1")
    public int updateAmendedPoId(long oldPoId, long newPoId);*/
	
	@Modifying
	@Transactional
	@Query("update PurchaseOrderMaster set verify = ?1, verifyDate = ?2, verfiedPOAttachment = ?3, verifiedBy = ?4, poCopy = ?5 where entityId = ?6")
	public int updateVerifyFlag(String verify, long verifyDate, String verfiedPOAttachment, String verifiedBy, String poCopy, long poId);
	
	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p where p.entityId in (select r.productName from RcProductDetails r where r.rateContract = ?1) order by p.productname")
	List<SelectionList> getListOfProductsInRc(RateContractMaster rc);

	@Modifying
	@Transactional
	@Query("update PurchaseOrderMaster set mailSentDate = ?2, mailSentBy = ?3,approvalStatus = 'APPROVED & EMAIL SENT' where entityId = ?1")
	public void updateMailSentStatus(long poId, long sentDate, String sentBy);
	
	@Query(value="select p.attachment from ordermgmt.purchase_order_attachments p where p.purchaseorderid = ?1", nativeQuery = true)
    public List<String> getAttachmentList(long poId);
	
	@Query(value="select p.entityid as selectionid, p.purchase_order_no as selectionvalue from ordermgmt.purchase_order_master p where p.approval_status = 'APPROVED' and p.entityid in (select distinct(g.po_no) from ordermgmt.grn_master g) order by p.purchase_order_no", nativeQuery = true)
    public List<SelectionList> getAllPoListFromGrn();
}