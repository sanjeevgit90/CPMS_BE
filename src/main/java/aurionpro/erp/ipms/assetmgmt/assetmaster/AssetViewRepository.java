package aurionpro.erp.ipms.assetmgmt.assetmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.assetmgmt.common.AssetCountList;
import aurionpro.erp.ipms.assetmgmt.common.AssetInstallationData;


public interface AssetViewRepository extends JpaRepository<AssetView,Long>{

	@Query(value="select a.city as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false and a.projectid in (select id from projectmgmt.getproject(?1))"
			+ " GROUP BY a.city order by a.city", nativeQuery = true )
	List<AssetCountList> getCityWiseAssetCount(Long profileId);

	@Query(value="select a.location as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1))"
			+ " GROUP BY a.location order by a.location", nativeQuery = true)
	List<AssetCountList> getLocationWiseAssetCount(Long profileId);

	@Query(value="select a.category as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) "
			+ " GROUP BY a.category order by a.category", nativeQuery = true)
	List<AssetCountList> getCategoryWiseAssetCount(Long profileId);

	@Query(value="select a.subcategory as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) "
			+ " GROUP BY a.subcategory order by a.subcategory", nativeQuery = true)
	List<AssetCountList> getSubCategoryWiseAssetCount(Long profileId);

	@Query(value="select a.city as parent, a.subcategory as child, count(a.assetname) as count from assetmgmt.assetview a"
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) "
			+ " GROUP BY a.subcategory,a.city order by a.city,a.subcategory", nativeQuery = true)
	List<AssetCountList> getCityandSubCategoryWiseAssetCount(Long profileId);

	@Query(value="select a.projectname as parent, count(a.assetname) as count from assetmgmt.assetview a"
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1))"
			+ " GROUP BY a.projectname order by a.projectname", nativeQuery = true)
	List<AssetCountList> getProjectWiseAssetCount(Long profileId);

	@Query(value="select a.location as parent, a.subcategory as child, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) "
			+ " GROUP BY a.subcategory, a.location order by a.subcategory, a.location", nativeQuery = true)
	List<AssetCountList> geLocationandSubCategoryWiseAssetCount(Long profileId);

	@Query("SELECT productid as product, manufacturer as manufacturer, model as model, count(productid) as count, serialno as serialno, location as  location,  policestation as policestation,assetstatus as status  FROM AssetView "
			+ " where location=?1 GROUP BY productid, manufacturer, model, serialno, location,  policestation, assetstatus")
	List<AssetInstallationData> locationinstallation(String id);

	@Query("SELECT subcategory as product, count(productid) as count FROM AssetView WHERE city=?1 GROUP BY subcategory")
	List<AssetInstallationData> cityinstallation(String id);
	
	@Query(value="select a.city as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false and a.projectid in (select id from projectmgmt.getproject(?1)) and a.district= ?2 and a.state=?3"
			+ " GROUP BY a.city order by a.city", nativeQuery = true )
	List<AssetCountList> getCityWiseAssetCountWithDistrict(Long profileId, String district, String state);

	@Query(value="select a.location as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) and a.district= ?2 and a.state=?3" 
			+ " GROUP BY a.location order by a.location", nativeQuery = true)
	List<AssetCountList> getLocationWiseAssetCount(Long profileId, String district, String state);

	@Query(value="select a.category as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) and a.district= ?2 and a.state=?3" 
			+ " GROUP BY a.category order by a.category", nativeQuery = true)
	List<AssetCountList> getCategoryWiseAssetCount(Long profileId, String district, String state);

	@Query(value="select a.subcategory as parent, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) and a.district= ?2 and a.state=?3 "
			+ " GROUP BY a.subcategory order by a.subcategory", nativeQuery = true)
	List<AssetCountList> getSubCategoryWiseAssetCount(Long profileId, String district, String state);

	@Query(value="select a.projectname as parent, count(a.assetname) as count from assetmgmt.assetview a"
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) and a.district= ?2 and a.state=?3 "
			+ " GROUP BY a.projectname order by a.projectname", nativeQuery = true)
	List<AssetCountList> getProjectWiseAssetCount(Long profileId, String district, String state);

	@Query(value="select a.location as parent, a.subcategory as child, count(a.assetname) as count from assetmgmt.assetview a "
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) and a.district= ?2 and a.state=?3"
			+ " GROUP BY a.subcategory, a.location order by a.subcategory, a.location", nativeQuery = true)
	List<AssetCountList> geLocationandSubCategoryWiseAssetCount(Long profileId, String district, String state);

	@Query(value="select a.city as parent, a.subcategory as child, count(a.assetname) as count from assetmgmt.assetview a"
			+ " where a.isdeleted=false  and a.projectid in (select id from projectmgmt.getproject(?1)) and a.district= ?2 and a.state=?3 "
			+ " GROUP BY a.subcategory,a.city order by a.city,a.subcategory", nativeQuery = true)
	List<AssetCountList> getCityandSubCategoryWiseAssetCount(Long profileId, String district, String state);

	
	@Query(value="select a.entityid from assetmgmt.assetview a where a.isdeleted=false and a.assetstatus ='DAMAGED' "
			+ "or a.assetstatus ='DEFECTIVE' and a.projectid in (select id from projectmgmt.getproject(?1))", nativeQuery = true)
	List<Long> getOEMDCAssetList(Long profileId);

	@Query(value="select a.entityid from assetmgmt.assetview a "
			+ " where a.isdeleted=false and a.assetstatus !='DAMAGED' and a.assetstatus !='DEFECTIVE' "
			+ " and a.projectid in (select id from projectmgmt.getproject(?1)) "
			+ " and a.entityid not in (select d.assetid from assetmgmt.delivery_challan_asset d "
			+ " LEFT JOIN assetmgmt.deliverchallan dc ON d.dcid = dc.entityid "
			+ " LEFT JOIN assetmgmt.assetmaster a ON d.assetid = a.entityid where dc.printflag = false) "
			+ " and a.entityid not in (select d.assetid from assetmgmt.inter_dc_asset d "
			+ " LEFT JOIN assetmgmt.interdistrictdc dc ON d.dcid = dc.entityid "
			+ " LEFT JOIN assetmgmt.assetmaster a ON d.assetid = a.entityid where dc.dcstatus = 'IDDC_PENDING') " 
			+ " and a.entityid not in (select d.assetid from assetmgmt.oem_dc_asset d "
			+ " LEFT JOIN assetmgmt.assetmaster a ON d.assetid = a.entityid"
			+ " where a.assetstatus ='DAMAGED' and a.assetstatus ='DEFECTIVE' and a.assetstatus ='SCRAPPED')", nativeQuery = true)
	List<Long> getDCAssetList(Long profileId);
	
	@Query(value="select * from assetmgmt.assetfilter(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13)",  nativeQuery = true)
	List<AssetView> getAssetByFilter(String assetname, Long projectid, String category, String state, String district, String city, String location,
			Long userId, String serialno, String assettag, String subcategory, String policestation, Boolean roleFlag);

}