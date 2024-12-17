package aurionpro.erp.ipms.ordermgmt.ratecontract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RateContractHistoryRepository extends JpaRepository<RateContractHistoryDetails, Long> {

	public RateContractHistoryDetails findByRateContractMaster(RateContractMaster rc);
	
	@Modifying
	@Transactional
	@Query("delete from RateContractHistoryDetails where rateContractMaster = ?1")
	public void deleteByRateContract(RateContractMaster rc);
}
