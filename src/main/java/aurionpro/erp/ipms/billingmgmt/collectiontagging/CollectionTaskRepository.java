package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CollectionTaskRepository extends JpaRepository<CollectionTaskMaster,Long>{

	List<CollectionTaskMaster> findByCollectionid(Long id);

}