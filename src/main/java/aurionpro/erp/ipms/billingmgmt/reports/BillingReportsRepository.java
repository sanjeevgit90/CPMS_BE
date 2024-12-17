package aurionpro.erp.ipms.billingmgmt.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import aurionpro.erp.ipms.billingmgmt.collectiontagging.CollectionTagging;


public interface BillingReportsRepository extends JpaRepository<CollectionTagging,Long>{

}
		