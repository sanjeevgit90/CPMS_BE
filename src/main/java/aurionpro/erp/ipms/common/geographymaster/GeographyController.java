package aurionpro.erp.ipms.common.geographymaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/ipms/geograpghy")
public class GeographyController {

    @Autowired
    GeographyRepository geographyRepo;

    @PreAuthorize("hasAuthority('Geography_Master_VIEW')")
    @PostMapping("/geographyByFilter")
    public Iterable<GeographyMaster> getAllGeography(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody GeographyMaster geography){
    	ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

    	Example<GeographyMaster> geoEx=Example.of(geography,em);
    	
    	if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
            return geographyRepo.findAll(geoEx, paging).getContent();
    	}
    	else
    	{
    		 return geographyRepo.findAll(geoEx);
    	}
    	
    }
   
    @PreAuthorize("hasAuthority('Geography_Master_ADD')")
    @PostMapping()
    public GeographyMaster createGeography(@RequestBody @Valid GeographyMaster geography) {
    	Optional<GeographyMaster> geoTemp= geographyRepo.findByIdGeographynameAndIdParentgeography(geography.getId().getGeographyname(),geography.getId().getParentgeography());
    	
		 if(geoTemp.isPresent())
        {
            throw new EntityExistsException("The Specified Geography already exists");
        }
		 validate();
    	 return geographyRepo.save(geography);
    }
    
    @PreAuthorize("hasAuthority('Geography_Master_EDIT')")
	@PutMapping()
    public GeographyMaster updateGeography(@Valid @RequestBody GeographyMaster geography) {
		Optional<GeographyMaster> geoTemp= geographyRepo.findByIdGeographynameAndIdParentgeography(geography.getId().getGeographyname(),geography.getId().getParentgeography());
    	 
		if(geoTemp == null)
 			 throw new EntityExistsException("The Specified Geography doesn't exists");
 		 validate();
 	 	return geographyRepo.save(geography);
    }
    
    private void validate() {
	
	}

   // @PreAuthorize("hasAuthority('Geography_Master_VIEW')")
	@GetMapping("{geographyname}/{state}")
    public Optional<GeographyMaster> getGeographyByName(@PathVariable(value = "geographyname") String geographyname, @PathVariable(value = "state") String state) 
    {
		Optional<GeographyMaster> geoObj = geographyRepo.findByIdGeographynameAndIdParentgeography(geographyname, state);
       	if (geoObj == null)
    	{
       		new RuntimeException("Can not find geography with geography name"+ geographyname);
    	}
       	
		return geoObj;
    }
    
    @GetMapping("/getGeographyList")
    public List<GeographyMaster> getGeographyList(){
        return geographyRepo.getGeographyList();
    } 
    
    @GetMapping("/getActiveGeographyList")
    public List<GeographyMaster> getActiveGeographyList(){
        return geographyRepo.getActiveGeographyList();
    } 
    
    @GetMapping("/getAllState")
    public List<SelectionList> getAllState(){
        return geographyRepo.getAllState();
    }   
    
    @GetMapping("/getAllDistrict")
    public List<SelectionList> getAllDistrict(){
        return geographyRepo.getAllDistrict();
    }
    
    @GetMapping("/getActiveState")
    public List<SelectionList> getActiveState(){
        return geographyRepo.getActiveState();
    }  
    
    @GetMapping("/getActiveDistrict")
    public List<SelectionList> getActiveDistrict(){
        return geographyRepo.getActiveDistrict();
    }   
    
    @GetMapping("/getAllDistrict/{state}")
    public List<SelectionList> getAllDistrictByState(@PathVariable(value = "state") String state){
        return geographyRepo.getAllDistrictByState(state);
    } 
    
    @GetMapping("/getActiveDistrict/{state}")
    public List<SelectionList> getActiveDistrictByState(@PathVariable(value = "state") String state){
        return geographyRepo.getActiveDistrictByState(state);
    }
    
    @PreAuthorize("hasAuthority('Geography_Master_DELETE')")
    @DeleteMapping("{geographyname}/{state}")
    public GeographyMaster deleteGeo(@PathVariable(value = "geographyname") String geographyname, @PathVariable(value = "state") String state){

    	Optional<GeographyMaster> geoList= geographyRepo.findByIdGeographynameAndIdParentgeography(geographyname, state);

        if(geoList== null)
        {
            throw new EntityNotFoundException("The Specified Geography deos not exists");
        }

        if (geoList.get().getIsDeleted()){
            throw new EntityNotFoundException("Geography already deleted");
        }
        else{
        	geoList.get().setIsDeleted(true);
            return geographyRepo.save(geoList.get());
        }
    }

}