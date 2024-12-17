package aurionpro.erp.ipms.assetmgmt.manufacturemaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface ManufacturerMasterRepository extends JpaRepository<ManufacturerMaster,String>{

	@Query("select m.manufacturername as selectionid, m.manufacturername as selectionvalue from ManufacturerMaster m order by m.manufacturername")
	List<SelectionList> getManufacturerList();

	@Query("select m.manufacturername as selectionid, m.manufacturername as selectionvalue from ManufacturerMaster m where m.isDeleted=false order by m.manufacturername")
	List<SelectionList> getActiveManufacturerList();

}