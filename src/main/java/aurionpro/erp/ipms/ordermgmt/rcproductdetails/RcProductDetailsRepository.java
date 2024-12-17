package aurionpro.erp.ipms.ordermgmt.rcproductdetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractMaster;

public interface RcProductDetailsRepository extends JpaRepository<RcProductDetails, Long> {

	//public List<ProductDetails> findByProductName(String productName);
	
	public List<RcProductDetails> findByRateContract(RateContractMaster rc);
	
	@Modifying
	@Transactional
	@Query("delete from RcProductDetails where rateContract = ?1")
	public int deleteProductListByRc(RateContractMaster rc);
}
