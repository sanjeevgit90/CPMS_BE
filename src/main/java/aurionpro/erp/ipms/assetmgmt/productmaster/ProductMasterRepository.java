package aurionpro.erp.ipms.assetmgmt.productmaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface ProductMasterRepository extends JpaRepository<ProductMaster,Long>{

	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p order by p.productname")
	List<SelectionList> getProductList();

	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p where p.isDeleted=false order by p.productname")
	List<SelectionList> getActiveProductList();

	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p where p.category != null and p.category = ?1 order by p.productname")
	List<SelectionList> getProductListFromCategory(String category);

	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p where p.category != null and p.category = ?1 and p.isDeleted=false order by p.productname")
	List<SelectionList> getActiveProductListFromCategory(String category);

	Optional<ProductMaster> findByProductname(String productname);

	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p where p.subcategory != null and p.subcategory = ?1 and p.isDeleted=false order by p.productname")
	List<SelectionList> getActiveProductListFromSubCategory(String subcategory);

	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p where p.subcategory != null and p.subcategory = ?1 order by p.productname")
	List<SelectionList> getProductListFromSubCategory(String subcategory);

	@Query("select p.entityId as selectionid, p.productname as selectionvalue from ProductMaster p where p.orgflag = ?1 order by p.productname")
	List<SelectionList> getProductListByOrg(Integer orgFlag);
}