package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CollectionTaggingRepository extends JpaRepository<CollectionTagging,Long>{

	@Query("select c from CollectionTagging c where c.invoiceid =?1")
	List<CollectionTagging> getCollectionByInvoice(Long id);

	@Query("select c from CollectionTagging c where c.projectid=?1 and c.isDeleted=false")
	List<CollectionTagging> getCollectionList(Long projectid);
	
	@Query("select sum(c.netamountcredieted) from CollectionTagging c where c.invoiceid=?1 "
			+ " and collectionstatus != 'REJECTED' and c.isDeleted=false")
	Double getCollection(Long invoiceid);
	
	@Query("select c from CollectionTagging c where c.invoiceid =?1 and c.isDeleted=false")
	List<CollectionTagging> getActiveCollectionByInvoice(Long id);


}