package aurionpro.erp.ipms.ordermgmt.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseOrderViewRepository extends JpaRepository<PurchaseOrderView, Long> {

	//@Query("select p.approvalStatus as approvalStatus, count(p.approvalStatus) as approvalStatusCount from PurchaseOrderMaster p")
	@Query("select p.approvalStatus as approvalStatus, count(p.approvalStatus) as approvalStatusCount from PurchaseOrderView p where (:account is null or p.accountName = :account) GROUP BY approvalStatus")
    public List<ApprovalReport> getApprovalCount(@Param("account") Long accountName);
	
	@Query("select p.approvalStatus as approvalStatus, count(p.approvalStatus) as approvalStatusCount from PurchaseOrderView p GROUP BY approvalStatus")
    public List<ApprovalReport> getApprovalCount();
	
	@Query("SELECT accName as name, count(purchaseOrderNo) as count, sum(grandTotal) as allTotalOfPo FROM PurchaseOrderView WHERE approvalStatus = 'APPROVED' GROUP BY accName ORDER BY accName")
    public List<ProjectVendorWiseReportDto> getProjectWiseReport();
	
	@Query("SELECT suppName as name, count(purchaseOrderNo) as count, sum(grandTotal) as allTotalOfPo FROM PurchaseOrderView WHERE approvalStatus = 'APPROVED' GROUP BY suppName ORDER BY suppName")
    public List<ProjectVendorWiseReportDto> getVendorWiseReport();
	
	@Query("SELECT accName as name, count(purchaseOrderNo) as count, sum(grandTotal) as allTotalOfPo FROM PurchaseOrderView WHERE approvalStatus = 'APPROVED' AND orderDate BETWEEN ?1 AND ?2 GROUP BY accName ORDER BY accName")
    public List<ProjectVendorWiseReportDto> getProjectWiseDateReport(Long fromDate, Long toDate);
	
	@Query("SELECT suppName as name, count(purchaseOrderNo) as count, sum(grandTotal) as allTotalOfPo FROM PurchaseOrderView WHERE approvalStatus = 'APPROVED' AND orderDate BETWEEN ?1 AND ?2 GROUP BY suppName ORDER BY suppName")
    public List<ProjectVendorWiseReportDto> getVendorWiseDateReport(Long fromDate, Long toDate);

	@Query(value="select * from ordermgmt.pofilterbysp(?1,?2,?3,?4,?5,?6,?7,?8)",  nativeQuery = true)
	public List<PurchaseOrderView> getallpobyfilterbysp(String purchaseOrderNo, Long accountName, Long organisationId,
			Long supplierName, Long department, Long fromDate, Long toDate, Long profileId);
}