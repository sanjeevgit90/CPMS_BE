package aurionpro.erp.ipms.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.common.citymaster.CityMasterRepository;
import aurionpro.erp.ipms.common.geographymaster.GeographyMaster;
import aurionpro.erp.ipms.common.geographymaster.GeographyRepository;
import aurionpro.erp.ipms.common.locationmaster.LocationMasterRepository;
import aurionpro.erp.ipms.common.policestationmaster.PoliceStationMasterRepository;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@Service()
public class GeographySelectionService {
	
	@Autowired
	GeographyRepository geoRepo;

    @Autowired
    CityMasterRepository cityRepo;
    
    @Autowired
    PoliceStationMasterRepository policestationRepo;
    
    @Autowired
    LocationMasterRepository locationRepo;
       
    public List<SelectionList> getAllState(){
		return geoRepo.getAllState();
	}
	
	public List<SelectionList> getActiveState(){
		return geoRepo.getActiveState();
	}
	
	public List<SelectionList> getAllDistrict(){
		return geoRepo.getAllDistrict();
	}
	
	public List<SelectionList> getActiveDistrict(){
		return geoRepo.getActiveDistrict();
	}

	public List<SelectionList> getAllDistrictByState(String state) {
		return geoRepo.getAllDistrictByState(state);
	}

	public List<SelectionList> getActiveDistrictByState(String state) {
		return geoRepo.getActiveDistrictByState(state);
	}

	public List<GeographyMaster> getGeographyList() {
		return geoRepo.findAll();
	}

	public List<GeographyMaster> getActiveGeographyList() {
		return geoRepo.getActiveGeographyList();
	}

	public List<SelectionList> getCityList() {
		return cityRepo.getCityList();
	}

	public List<SelectionList> getActiveCity() {
		return cityRepo.getActiveCity();
	}

	public List<SelectionList> getCityFromDistrict(String district) {
		return cityRepo.getCityFromDistrict(district);
	}

	public List<SelectionList> getActiveCityFromDistrict(String district) {
		return cityRepo.getActiveCityFromDistrict(district);
	}

	public List<SelectionList> getAllPoliceList() {
		return policestationRepo.getAllPoliceList();
	}

	public List<SelectionList> getActivePoliceStationList() {
		return policestationRepo.getActivePoliceStationList();
	}

	public List<SelectionList> getPoliceStationFromCity(String city) {
		return policestationRepo.getPoliceStationFromCity(city);
	}

	public List<SelectionList> getActivePoliceStationFromCity(String city) {
		return policestationRepo.getActivePoliceStationFromCity(city);
	}

	public List<SelectionList> getAllLocationList() {
		return locationRepo.getAllLocationList();
	}

	public List<SelectionList> getActiveLocationList() {
		return locationRepo.getActiveLocationList();
	}

	public List<SelectionList> getLocationFromCity(String city) {
		return locationRepo.getLocationFromCity(city);
	}

	public List<SelectionList> getActiveLocationFromCity(String city) {
		return locationRepo.getActiveLocationFromCity(city);
	}

	public List<SelectionList> getLocationFromPoliceStation(String policeStation) {
		return locationRepo.getLocationFromPoliceStation(policeStation);
	}

	public List<SelectionList> getActiveLocationFromPoliceStation(String policeStation) {
		return locationRepo.getActiveLocationFromPoliceStation(policeStation);
	}
	
}
