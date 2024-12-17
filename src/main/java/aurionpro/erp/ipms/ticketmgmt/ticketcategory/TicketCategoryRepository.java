package aurionpro.erp.ipms.ticketmgmt.ticketcategory;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory,String> {

	@Query("select t.categoryName as selectionid, t.categoryName as selectionvalue from TicketCategory t")
	List<SelectionList> getAllCategoryList();

	@Query("select t.categoryName as selectionid, t.categoryName as selectionvalue from TicketCategory t where t.isDeleted=false and t.parentCategoryId=null")
	List<SelectionList> getActiveCategoryList();
	
	@Query("select a.categoryName as selectionid, a.categoryName as selectionvalue from TicketCategory a where a.parentCategoryId=?1 and a.isDeleted=false")
	List<SelectionList> getSubcategoryByCategory(String name);
	
}
