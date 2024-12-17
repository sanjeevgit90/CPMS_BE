package aurionpro.erp.ipms.assetmgmt.assetmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AssetAuditTrailRepository extends JpaRepository<AssetAuditTrail,Long>{

	//@Query("select a from AssetAuditTrail a where a.assetid=?1")
	//List<AssetAuditTrail> getAuditTrail(Long id);
	
	
	 @Query("SELECT NEW AssetAuditTrail(a.entityId as entityId , a.assetname as assetname,a.assetstatus as assetstatus, a.assettag as assettag,"
	 		+ " a.deliverychallanno as deliverychallanno, a.depreciation as depreciation, a.eol as eol ,"
	 		+ " a.location as location, a.orderno as orderno, a.purchasedate as purchasedate, a.serialno as serialno,"
	 		+ " a.warrantytilldate as warrantytilldate,p.productname as product, a.category ,a.subcategory,"  
			+ "  pr.projectName as project, a.operation as operation, a.assetid as assetid,v.partyName as vendor,"
			+ "	 a.city, a.policestation, a.district,a.state) FROM AssetAuditTrail a"
			+ "  LEFT JOIN ProductMaster p ON a.productid = p.entityId"
			+ "  LEFT JOIN LocationMaster l ON a.location = l.locationid"
			+ "  LEFT JOIN Project pr ON a.projectname = pr.entityId"
			+ "  LEFT JOIN PartyMaster v ON a.vendorname = v.entityId "
			+ "  where a.assetid =?1")
	List<AssetAuditTrail> getAuditTrail(Long id);
	



}