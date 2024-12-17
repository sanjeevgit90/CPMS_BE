package aurionpro.erp.ipms.openbravo.openbravodetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenBravoProductDetailsRepository extends JpaRepository<OpenBravoProductDetailsView, Long> {

	public List<OpenBravoProductDetailsView> findByPurchaseOrder(Long poId);
}
