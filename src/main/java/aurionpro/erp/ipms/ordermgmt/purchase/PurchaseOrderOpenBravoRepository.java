package aurionpro.erp.ipms.ordermgmt.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PurchaseOrderOpenBravoRepository extends JpaRepository<PurchaseOrderOpenBravoDetails, Long> {

	@Modifying
	@Transactional
	@Query("delete from PurchaseOrderOpenBravoDetails where purchaseOrderMaster = ?1")
	public void deleteByPurchaseOrderMaster(PurchaseOrderMaster po);
	
	public PurchaseOrderOpenBravoDetails findByPurchaseOrderMaster(PurchaseOrderMaster po);
}
