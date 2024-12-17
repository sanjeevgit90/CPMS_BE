package aurionpro.erp.ipms.ordermgmt.productdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsViewRepository extends JpaRepository<ProductDetailsView, Long> {

	public List<ProductDetailsView> findByPurchaseOrder(long purchaseOrder);
	
	public List<ProductDetailsView> findByPurchaseOrderOrderByCreatedDateAsc(long purchaseOrder);
}
