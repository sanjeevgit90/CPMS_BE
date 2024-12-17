package aurionpro.erp.ipms.common.citymaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/city")
public class CityMasterController {

    @Autowired
    CityMasterRepository cityRepo;

    @PreAuthorize("hasAuthority('City_Master_VIEW')")
    @PostMapping("/cityByFilter")
    public Iterable<CityMaster> getAllCity(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody CityMaster city){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<CityMaster> cityEx=Example.of(city,em);
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		 return cityRepo.findAll(cityEx, paging).getContent();
    	}
    	else
    	{
    		 return cityRepo.findAll(cityEx);
    	}
    }
    

    @PreAuthorize("hasAuthority('City_Master_ADD')")
    @PostMapping()
    public CityMaster createCity(@Valid @RequestBody CityMaster city) {
    	 Optional<CityMaster> cityTemp= cityRepo.findByIdCitynameAndIdDistrictAndIdState(city.getId().getCityname(), city.getId().getDistrict(),city.getId().getState());
	    	
   		 if(cityTemp.isPresent())
            {
                throw new EntityExistsException("The Specified City already exists");
            }
         return cityRepo.save(city);
    }
    
    @PreAuthorize("hasAuthority('City_Master_EDIT')")
    @PutMapping()
    public CityMaster updateCity(@Valid @RequestBody CityMaster city) {
    	Optional<CityMaster> cityTemp= cityRepo.findByIdCitynameAndIdDistrictAndIdState(city.getId().getCityname(), city.getId().getDistrict(),city.getId().getState());
    	 if (cityTemp == null)
    			 throw new EntityExistsException("The Specified City doesn't exists");
    	 validate();
 	 	return cityRepo.save(city);
    }
    
    private void validate() {
	
	}
   
   // @PreAuthorize("hasAuthority('City_Master_VIEW')")
    @GetMapping("/{cityname}/{district}/{state}")
    public Optional<CityMaster> getCityById(@PathVariable(value = "cityname") String cityname, @PathVariable(value = "district") String district, @PathVariable(value = "state") String state) 
    {
    	Optional<CityMaster> cityTemp= cityRepo.findByIdCitynameAndIdDistrictAndIdState(cityname, district, state);
    	
    	if (cityTemp == null)
    	{
    		new RuntimeException("Can not Find City with city name= "+cityname);
    	}
		return cityTemp;
    }
    
    @GetMapping("/getAllCityList")
    public List<SelectionList> getCityList(){
        return cityRepo.getCityList();
    }
    
    @GetMapping("/getActiveCity")
    public List<SelectionList> getActiveCity(){
        return cityRepo.getActiveCity();
    }  
   
    @GetMapping("/getCityFromDistrict/{district}")
    public List<SelectionList> getCityFromState(@PathVariable(value = "district") String district){
        return cityRepo.getCityFromDistrict(district);
    } 
    
    @GetMapping("/getActiveCityFromDistrict/{district}")
    public List<SelectionList> getActiveCityFromDistrict(@PathVariable(value = "district") String district){
        return cityRepo.getActiveCityFromDistrict(district);
    }
    
    @GetMapping("/getDistinctAssetCity")
    public List<SelectionList> getDistinctAssetCity(){
        return cityRepo.getDistinctAssetCity();
    }
    
    @PreAuthorize("hasAuthority('City_Master_DELETE')")
    @DeleteMapping("/{cityname}/{district}/{state}")
    public CityMaster deleteCity(@PathVariable(value = "cityname") String cityname, @PathVariable(value = "district") String district, @PathVariable(value = "state") String state){

    	Optional<CityMaster> cityList= cityRepo.findByIdCitynameAndIdDistrictAndIdState(cityname, district,state);
    	
        if(cityList== null)
        {
            throw new EntityNotFoundException("The Specified City deos not exists");
        }

        if (cityList.get().getIsDeleted()){
            throw new EntityNotFoundException("City already deleted");
        }
        else{
        	cityList.get().setIsDeleted(true);
            return cityRepo.save(cityList.get());
        }
    }

}