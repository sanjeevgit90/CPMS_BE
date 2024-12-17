package aurionpro.erp.ipms.assetmgmt.modelmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface ModelMasterRepository extends JpaRepository<ModelMaster,String>{

	@Query("select m.modelname as selectionid, m.modelname as selectionvalue from ModelMaster m order by m.modelname")
	List<SelectionList> getModelList();

	@Query("select m.modelname as selectionid, m.modelname as selectionvalue from ModelMaster m where m.isDeleted=false order by m.modelname")
	List<SelectionList> getActiveModelList();

	@Query("select m.modelname as selectionid, m.modelname as selectionvalue from ModelMaster m where m.manufacture !=null and m.manufacture.manufacturername=?1 order by m.modelname")
	List<SelectionList> getModelListFromManufacturer(String manufacturer);

	@Query("select m.modelname as selectionid, m.modelname as selectionvalue from ModelMaster m where m.manufacture !=null and m.manufacture.manufacturername=?1 and m.isDeleted=false order by m.modelname")
	List<SelectionList> getActiveModelListFromManufacturer(String manufacturer);

}