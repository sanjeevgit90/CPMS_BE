package aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.assetmgmt.common.DCAssetView;


public interface InterDistrictDeliveryChallanRepository extends JpaRepository<InterDistrictDeliveryChallan,Long>{

	@Query(value="SELECT d.dcno FROM assetmgmt.interdistrictdc d where d.projectname =?1 order by d.createdDate desc limit 1", nativeQuery = true)
	List<String> getDCNumber(long projectName);

	Optional<InterDistrictDeliveryChallan> findByDcno(String dcno);

	@Query(value="SELECT d.dcid as dcid, d.assetid as assetid,a.assetname as assetname, "
			+ " a.assettag as assettag,  a.location as location, a.productid as productid,  a.serialno as serialno,"
			+ " p.category as category, p.productname as productname, p.subcategory as subcategory,"
			+ " p.manufacturer as manufacturer,p.model as model, a.assetstatus as assetstatus FROM assetmgmt.inter_dc_asset d"
			+ " LEFT JOIN assetmgmt.assetmaster a ON d.assetid = a.entityid "
			+ " LEFT JOIN assetmgmt.productmaster p ON a.productid = p.entityid "
			+ " where dcid=?1", nativeQuery = true)
	List<DCAssetView> findAssetByDcid(Long entityId);


}