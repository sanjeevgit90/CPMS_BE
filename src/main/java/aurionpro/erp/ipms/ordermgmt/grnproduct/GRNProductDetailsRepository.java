package aurionpro.erp.ipms.ordermgmt.grnproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GRNProductDetailsRepository extends JpaRepository<GRNProductDetails, Long> {
	@Modifying
	@Transactional
	@Query("delete from GRNProductDetails where grnId = ?1")
	void deleteByGrnId(long id);
}
