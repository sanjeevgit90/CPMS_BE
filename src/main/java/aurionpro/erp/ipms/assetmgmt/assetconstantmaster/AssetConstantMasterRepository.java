package aurionpro.erp.ipms.assetmgmt.assetconstantmaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface AssetConstantMasterRepository extends JpaRepository<AssetConstantMaster,Long>{

	@Query("select c.constantname as selectionid, c.constantname as selectionvalue from AssetConstantMaster c where c.constantnamefor='EOL' order by c.constantname")
	List<SelectionList> getEolList();

	@Query("select c.constantname as selectionid, c.constantname as selectionvalue from AssetConstantMaster c where c.constantnamefor='DEPRECIATION' order by c.constantname")
	List<SelectionList> getDepreciationList();

	@Query("select c.constantname as selectionid, c.constantname as selectionvalue from AssetConstantMaster c where c.constantnamefor='EOL' and c.isDeleted=false order by c.constantname")
	List<SelectionList> getActiveEolList();

	@Query("select c.constantname as selectionid, c.constantname as selectionvalue from AssetConstantMaster c where c.constantnamefor='DEPRECIATION' and c.isDeleted=false order by c.constantname")
	List<SelectionList> getActiveDepreciationList();
	
	@Query("select c from AssetConstantMaster c where c.constantnamefor=?2 and c.constantname=?1")
	Optional<AssetConstantMaster> findConstant(String constantname, String constantnamefor);

}