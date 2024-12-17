package aurionpro.erp.ipms.assetmgmt.assetmaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface AssetMasterRepository extends JpaRepository<AssetMaster,Long>{

	Optional<AssetMaster> findByAssetname(String assetname);

	@Query(value="select a.entityId as selectionid, a.assetname as selectionvalue from assetmgmt.assetmaster a "
			+ " where a.projectname in (select id from projectmgmt.getproject(?1)) order by a.assetname", nativeQuery = true)
	List<SelectionList> getAssetList(Long userid);

	@Query(value="select a.entityId as selectionid, a.assetname as selectionvalue from assetmgmt.assetmaster a "
			+ " where a.projectname in (select id from projectmgmt.getproject(?1)) and a.isDeleted=false order by a.assetname" , nativeQuery = true)
	List<SelectionList> getActiveAssetList(Long userid);
	
	@Modifying
	@Query(value="update assetmgmt.assetmaster set location=?2 where entityid in(?1)", nativeQuery = true)
	void updateAssetStatus(List<Long> assetId, String shippedto);

	@Modifying
	@Query(value="update assetmgmt.assetmaster set assetstatus=?2 where entityid =?1", nativeQuery = true)
	void receiveAssetByOEM(Long id, String status);

	@Query(value="select a.entityId as selectionid, a.assetname as selectionvalue from assetmgmt.assetmaster a where "
			+ "a.location=?1 and a.isDeleted=false and a.projectname in (select id from projectmgmt.getproject(?2)) order by a.assetname", nativeQuery =true )
	List<SelectionList> getAssetByLocation(String name, Long userid);
    
	@Query("select a.entityId as selectionid, a.assettag as selectionvalue from AssetMaster a where a.location=?1 and a.isDeleted=false")
	List<SelectionList> getAssetTagByLocation(String name);
    
	@Query("select a.entityId as selectionid, a.assettag as selectionvalue from AssetMaster a where a.entityId=?1 and a.isDeleted=false")
	List<SelectionList> getAssetTagByAsset(Long entityId);

	@Query(value="select a from AssetMaster a where a.assetname=?1 and a.serialno=?2 and a.assettag =?3")
	AssetMaster findAsset(String assetname, String serialno, String assettag);

	
}