package aurionpro.erp.ipms.ordermgmt.rcproductdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RcProductDetailsViewRepository extends JpaRepository<RcProductDetailsView, Long> {

	public List<RcProductDetailsView> findByRateContract(long rateContract);
}
