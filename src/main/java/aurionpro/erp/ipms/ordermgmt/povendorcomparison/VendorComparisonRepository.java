package aurionpro.erp.ipms.ordermgmt.povendorcomparison;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorComparisonRepository extends JpaRepository<VendorComparison, Long> {

	public List<VendorComparison> findByPurchaseOrderNo(long id);
}
