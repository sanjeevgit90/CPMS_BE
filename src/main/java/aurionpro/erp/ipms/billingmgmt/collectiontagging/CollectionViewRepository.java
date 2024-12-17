package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface CollectionViewRepository extends JpaRepository<CollectionView,Long>{

	 @Query(value="select distinct(trim(to_char(to_timestamp(collectiondate / 1000.0), 'Month'))) as selectionvalue from CollectionView "
		 	+ " where collectionstatus='APPROVED' "
		 	+ " and (TO_DATE(to_char(to_timestamp(collectiondate / 1000.0), 'DD/MM/YYYY'), 'DD/MM/YYYY') >= TO_DATE(?1, 'YYYY-MM-DD') "
		 	+ " and TO_DATE(to_char(to_timestamp(collectiondate / 1000.0), 'DD/MM/YYYY'), 'DD/MM/YYYY') < TO_DATE(?2, 'YYYY-MM-DD')) "
		 	+ " group by collectiondate")
	List<SelectionList> monthlyOfCollectionReport(String startYear, String endYear);

	 @Query(value="select * from billingmgmt.collectionview "
		 	+ " where collectionstatus='APPROVED'"
		 	+ " and trim(to_char(to_timestamp(collectiondate / 1000.0), 'Month'))\\:\\: varchar = ?1",nativeQuery = true)
	 List<CollectionView> monthlyCollectionReport(String month);



}