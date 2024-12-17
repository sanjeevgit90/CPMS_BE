package aurionpro.erp.ipms.ordermgmt.productdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderMaster;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

	//public List<ProductDetails> findByProductName(String productName);
	
	public List<ProductDetails> findByPurchaseOrder(PurchaseOrderMaster po);
	
	public List<ProductDetails> findByPurchaseOrderOrderByCreatedDateAsc(PurchaseOrderMaster po);
	
	//to be checked if working or not
	@Modifying
	@Transactional
	@Query("delete from ProductDetails where purchaseOrder = ?1")
	public int deleteProductListByPoId(PurchaseOrderMaster po);
}
