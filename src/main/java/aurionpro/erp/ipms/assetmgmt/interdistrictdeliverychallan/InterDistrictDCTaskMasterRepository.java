package aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface InterDistrictDCTaskMasterRepository extends JpaRepository<InterDistrictDCTaskMaster,Long>{

	List<InterDistrictDCTaskMaster> findByDcno(String dcno);

}