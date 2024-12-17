package aurionpro.erp.ipms.common.locationmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface LocationMasterRepository extends JpaRepository<LocationMaster,String>{

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l  order by l.locationid")
	List<SelectionList> getAllLocationList();

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l where l.isDeleted=false order by l.locationid")
	List<SelectionList> getActiveLocationList();

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l where l.city=?1 order by l.locationid")
	List<SelectionList> getLocationFromCity(String city);

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l where l.city=?1 and l.isDeleted=false order by l.locationid")
	List<SelectionList> getActiveLocationFromCity(String city);

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l where l.policestation=?1 order by l.locationid")
	List<SelectionList> getLocationFromPoliceStation(String policeStation);

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l where l.policestation=?1 and l.isDeleted=false order by l.locationid")
	List<SelectionList> getActiveLocationFromPoliceStation(String policeStation);

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l where l.district=?1 and l.isDeleted=false order by l.locationid")
	List<SelectionList> getActiveLocationFromDistict(String district);

	@Query("SELECT DISTINCT location as selectionid , location as selectionvalue FROM AssetView "
			+ " where city = ?1 and location NOT IN(SELECT DISTINCT av.location FROM AssetView av "
			+ " JOIN AssetLocationWiseInstallationMaster imc on(av.location=imc.location) AND "
			+ " (imc.printflag=false))")
	List<SelectionList> getDistinctAssestLocations(String city);

	@Query("select l.locationid as selectionid, l.locationid as selectionvalue from LocationMaster l where l.warehouse=true and l.isDeleted=false order by l.locationid")
	List<SelectionList> getWarehouseList();

}