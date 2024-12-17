package aurionpro.erp.ipms.assetmgmt.oemdeliverychallan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OEMDCTaskMasterRepository extends JpaRepository<OEMDCTaskMaster,Long>{

	List<OEMDCTaskMaster> findByDcno(String dcno);

}