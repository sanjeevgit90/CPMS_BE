package aurionpro.erp.ipms.ordermgmt.boqproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.ordermgmt.boq.BOQ;

public interface BOQProductChildRepository extends JpaRepository<BOQProductChild, Long> {

	@Query("delete from BOQProductChild p where p.boq = ?1 and p.productId = ?2")
	public void deleteBOQProductChild(BOQ boq, long prodId);
}
