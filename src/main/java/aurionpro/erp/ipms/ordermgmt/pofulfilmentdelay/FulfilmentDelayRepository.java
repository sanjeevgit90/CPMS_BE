package aurionpro.erp.ipms.ordermgmt.pofulfilmentdelay;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FulfilmentDelayRepository extends JpaRepository<FulfilmentDelay, Long> {

	public List<FulfilmentDelay> findByPurchaseOrderNo(long id);
}
