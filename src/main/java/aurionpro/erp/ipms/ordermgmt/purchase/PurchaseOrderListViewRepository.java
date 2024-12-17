package aurionpro.erp.ipms.ordermgmt.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseOrderListViewRepository extends JpaRepository<PurchaseOrderListView, Long> {

	@Query(value="select * from ordermgmt.pofilterlistbysp(?1,?2,?3,?4,?5,?6,?7,?8,?9)",  nativeQuery = true)
	List<PurchaseOrderListView> getallpobyfilterbysp(String purchaseOrderNo, Long accountName, Long organisationId,
			Long supplierName, Long department, Long fromDate, Long toDate, Long profileId, String approvalStatus);

	}