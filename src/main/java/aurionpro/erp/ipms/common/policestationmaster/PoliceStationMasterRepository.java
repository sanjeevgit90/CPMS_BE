package aurionpro.erp.ipms.common.policestationmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface PoliceStationMasterRepository extends JpaRepository<PoliceStationMaster,String>{

	@Query("select p.policestationname as selectionid, p.policestationname as selectionvalue from PoliceStationMaster p order by p.policestationname ")
	List<SelectionList> getAllPoliceList();

	@Query("select p.policestationname as selectionid, p.policestationname as selectionvalue from PoliceStationMaster p where p.isDeleted=false order by p.policestationname")
	List<SelectionList> getActivePoliceStationList();

	@Query("select p.policestationname as selectionid, p.policestationname as selectionvalue from PoliceStationMaster p where p.city!=null and p.city=?1 order by p.policestationname")
	List<SelectionList> getPoliceStationFromCity(String city);

	@Query("select p.policestationname as selectionid, p.policestationname as selectionvalue from PoliceStationMaster p where p.city!=null and p.city=?1 and p.isDeleted=false order by p.policestationname")
	List<SelectionList> getActivePoliceStationFromCity(String city);

}