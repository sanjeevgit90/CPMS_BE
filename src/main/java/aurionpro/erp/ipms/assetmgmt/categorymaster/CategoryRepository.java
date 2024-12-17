package aurionpro.erp.ipms.assetmgmt.categorymaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface CategoryRepository extends JpaRepository<CategoryMaster,String>{

	@Query("select c from CategoryMaster c")
	List<CategoryMaster> getCategoryList();

	@Query("select c from CategoryMaster c where c.isDeleted=false")
	List<CategoryMaster> getActiveCategoryList();

	@Query("select c.categoryname as selectionid, c.categoryname as selectionvalue from CategoryMaster c where c.parent=null order by c.categoryname")
	List<SelectionList> getAllParentCategory();

	@Query("select c.categoryname as selectionid, c.categoryname as selectionvalue from CategoryMaster c where c.parent!=null order by c.categoryname")
	List<SelectionList> getAllSubCategory();

	@Query("select c.categoryname as selectionid, c.categoryname as selectionvalue from CategoryMaster c where c.parent!=null and c.isDeleted=false order by c.categoryname")
	List<SelectionList> getActiveSubCategory();

	@Query("select c.categoryname as selectionid, c.categoryname as selectionvalue from CategoryMaster c where c.parent=null and c.isDeleted=false order by c.categoryname")
	List<SelectionList> getActiveParentCategory();

	@Query("select c.categoryname as selectionid, c.categoryname as selectionvalue from CategoryMaster c where c.parent!=null and c.parent.categoryname=?1 order by c.categoryname")
	List<SelectionList> getAllSubCategoryfromParent(String parent);

	@Query("select c.categoryname as selectionid, c.categoryname as selectionvalue from CategoryMaster c where c.parent!=null and c.parent.categoryname=?1 and c.isDeleted=false order by c.categoryname")
	List<SelectionList> getActiveSubCategoryfromParent(String parent);

}