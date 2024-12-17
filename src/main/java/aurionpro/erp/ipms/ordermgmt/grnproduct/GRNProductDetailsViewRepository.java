package aurionpro.erp.ipms.ordermgmt.grnproduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GRNProductDetailsViewRepository extends JpaRepository<GRNProductDetailsView, Long> {

	public List<GRNProductDetailsView> findByGrnId(long grnId);
}
