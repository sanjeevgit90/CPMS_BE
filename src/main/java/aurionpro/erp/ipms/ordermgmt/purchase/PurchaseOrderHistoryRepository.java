package aurionpro.erp.ipms.ordermgmt.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PurchaseOrderHistoryRepository extends JpaRepository<PurchaseOrderHistoryDetails, Long> {

	public PurchaseOrderHistoryDetails findByPurchaseOrderMaster(PurchaseOrderMaster po);
	
	@Modifying
	@Transactional
	@Query("delete from PurchaseOrderHistoryDetails where purchaseOrderMaster = ?1")
	public void deleteByPurchaseOrderMaster(PurchaseOrderMaster po);
}
