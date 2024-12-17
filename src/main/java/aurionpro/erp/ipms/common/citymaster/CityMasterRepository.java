package aurionpro.erp.ipms.common.citymaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface CityMasterRepository extends  JpaRepository<CityMaster,CityIdentity>{
	
	@Query("select c.id.cityname as selectionid, c.id.cityname as selectionvalue from CityMaster c order by c.id.cityname")
	List<SelectionList> getCityList();
	
	@Query("select c.id.cityname as selectionid, c.id.cityname as selectionvalue from CityMaster c where c.isDeleted=false order by c.id.cityname")
	List<SelectionList> getActiveCity();

	@Query("select c.id.cityname as selectionid, c.id.cityname as selectionvalue from CityMaster c where c.id.district!=null and c.id.district=?1 order by c.id.cityname")
	List<SelectionList> getCityFromDistrict(String district);
	
	@Query("select c.id.cityname as selectionid, c.id.cityname as selectionvalue from CityMaster c where c.id.district!=null and c.id.district=?1 and c.isDeleted=false order by c.id.cityname")
	List<SelectionList> getActiveCityFromDistrict(String district);

	Optional<CityMaster> findByIdCitynameAndIdDistrictAndIdState(String cityname, String district, String state);

	@Query("SELECT DISTINCT city as selectionvalue, city as selectionid FROM AssetView "
			+ " where location NOT IN(SELECT DISTINCT av.city FROM AssetView av "
			+ " JOIN AssetCityWiseInstallationMaster imc on(av.city=imc.city) AND "
			+ " (imc.printflag=false))")
	List<SelectionList> getDistinctAssetCity();

}