package aurionpro.erp.ipms.common.locationmaster;

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
@RequestMapping(value = "/ipms/location")
public class LocationMasterController {

    @Autowired
    LocationMasterRepository locationRepo;
   
    @PreAuthorize("hasAuthority('UniqueSiteID_Master_VIEW')")
    @PostMapping("/locationByFilter")
    public Iterable<LocationMaster> getAllLocation(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody LocationMaster location){
    	ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

   	 Example<LocationMaster> locEx=Example.of(location,em);
       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		 return locationRepo.findAll(locEx, paging).getContent();
    	}
    	else
    	{
    		 return locationRepo.findAll(locEx);
    	}
    }
    

    @PreAuthorize("hasAuthority('UniqueSiteID_Master_ADD')")
     @PostMapping()
    public LocationMaster createLocation(@Valid @RequestBody LocationMaster location) {
    	 Optional<LocationMaster> locationTemp= locationRepo.findById(location.getLocationid());
	    	
 		 if(locationTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Location Id already exists");
          }
 		 validate();
    	 return locationRepo.save(location);
    }
    
    private void validate() {
	
    }

    @PreAuthorize("hasAuthority('UniqueSiteID_Master_EDIT')")
    @PutMapping()
    public LocationMaster updateModel(@Valid  @RequestBody LocationMaster location) {
		Optional<LocationMaster> locationTemp= locationRepo.findById(location.getLocationid());
 	    	 
 		 if (locationTemp == null)
 			 throw new EntityExistsException("The Specified Location Id doesn't exists");
 	    	validate();
    	 return locationRepo.save(location);
    }
    

    @GetMapping()
    public Optional<LocationMaster> getLocationById(@RequestParam(name = "locationid") String locationid) 
    {
    	Optional<LocationMaster> locObj = locationRepo.findById(locationid);
    	
    	if (locObj == null)
    	{
    		new RuntimeException("Can not Find Location Id = "+locationid);
    	}
		return locObj;
    }
    
    @GetMapping("/getAllLocationList")
    public List<SelectionList> getAllLocationList(){
        return locationRepo.getAllLocationList();
    }
    
    @GetMapping("/getActiveLocationList")
    public List<SelectionList> getActiveLocationList(){
        return locationRepo.getActiveLocationList();
    }  
    
    @GetMapping("/getWarehouseList")
    public List<SelectionList> getWarehouseList(){
        return locationRepo.getWarehouseList();
    }  
   
    @GetMapping("/getLocationFromCity/{city}")
    public List<SelectionList> getLocationFromCity(@PathVariable(value = "city") String city){
        return locationRepo.getLocationFromCity(city);
    } 
    
    @GetMapping("/getActiveLocationFromCity/{city}")
    public List<SelectionList> getActiveLocationFromCity(@PathVariable(value = "city") String city){
        return locationRepo.getActiveLocationFromCity(city);
    }
    
    @GetMapping("/getLocationFromPoliceStation/{policeStation}")
    public List<SelectionList> getLocationFromPoliceStation(@PathVariable(value = "policeStation") String policeStation){
        return locationRepo.getLocationFromPoliceStation(policeStation);
    } 
    
    @GetMapping("/getActiveLocationFromPoliceStation/{policeStation}")
    public List<SelectionList> getActiveLocationFromPoliceStation(@PathVariable(value = "policeStation") String policeStation){
        return locationRepo.getActiveLocationFromPoliceStation(policeStation);
    }
    
    @GetMapping("/getActiveLocationFromDisrict/{district}")
    public List<SelectionList> getActiveLocationFromDistict(@PathVariable(value = "district") String district){
        return locationRepo.getActiveLocationFromDistict(district);
    }
    
    @GetMapping("/getDistinctAssestLocations/{city}")
    public List<SelectionList> getDistinctAssestLocations(@PathVariable(value = "city") String city){
        return locationRepo.getDistinctAssestLocations(city);
    }

    @PreAuthorize("hasAuthority('UniqueSiteID_Master_DELETE')")
    @DeleteMapping()
    public LocationMaster deleteLocation(@RequestParam(name = "locationid") String locationid) {

        Optional<LocationMaster> locList= locationRepo.findById(locationid);

        if(locList== null)
        {
            throw new EntityNotFoundException("The Specified Location deos not exists");
        }

        if (locList.get().getIsDeleted()){
            throw new EntityNotFoundException("Location already deleted");
        }
        else{
        	locList.get().setIsDeleted(true);
            return locationRepo.save(locList.get());
        }
    }
}