package aurionpro.erp.ipms.ordermgmt.productdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderView;

public interface ProductsReportViewRepository extends JpaRepository<ProductsReportView, Long> {

	@Query("select purchaseOrderNo from ProductsReportView where category = ?1")
	public List<Long> getPoIdListByCategory(String category);
	
	@Query("select purchaseOrderNo from ProductsReportView where subCategory = ?1")
	public List<Long> getPoIdListBySubCategory(String subCategory);
	
	@Query("select purchaseOrderNo from ProductsReportView where productId = ?1")
	public List<Long> getPoIdListByProduct(long productId);
	
	@Query("select p from PurchaseOrderView p where p.entityId in (:poIdList) and (:account is null or p.accName = :account)")
	public List<PurchaseOrderView> getCatWiseReportData(@Param("poIdList") List<Long> poIdList, @Param("account") String account);
	
	@Query("select p from PurchaseOrderView p where (:account is null or p.accName = :account)")
	public List<PurchaseOrderView> getCatWiseReportData(@Param("account") String account);
}
