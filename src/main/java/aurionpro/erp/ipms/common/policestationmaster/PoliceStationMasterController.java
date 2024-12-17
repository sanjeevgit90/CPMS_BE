package aurionpro.erp.ipms.common.policestationmaster;

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
@RequestMapping(value = "/ipms/policestation")
public class PoliceStationMasterController {

    @Autowired
    PoliceStationMasterRepository policestationRepo;
    

    @PreAuthorize("hasAuthority('Police_Station_Master_VIEW')")
    @PostMapping("/policeByFilter")
    public Iterable<PoliceStationMaster> getAllPolicestation(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody PoliceStationMaster police){
    	ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

   	    Example<PoliceStationMaster> policeEx=Example.of(police,em);
       
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		 return policestationRepo.findAll(policeEx, paging).getContent();
    	}
    	else
    	{
    		 return policestationRepo.findAll(policeEx);
    	}
    }

    @PreAuthorize("hasAuthority('Police_Station_Master_ADD')")
    @PostMapping()
    public PoliceStationMaster createPolicestation(@Valid @RequestBody PoliceStationMaster police) {
    	Optional<PoliceStationMaster> policeTemp= policestationRepo.findById(police.getPolicestationname());
	    	
 		 if(policeTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Police Station Name already exists");
          }
    	 return policestationRepo.save(police);
    }
    
    @PreAuthorize("hasAuthority('Police_Station_Master_EDIT')")
    @PutMapping("/{policestationname}")
    public PoliceStationMaster updatePolicestation(@PathVariable(value="policestationname") String policestationname, @Valid @RequestBody PoliceStationMaster police) {
    	if (!(StringUtils.isEmpty(policestationname)))
  		{
  		 if (!(policestationname.equalsIgnoreCase(police.getPolicestationname())))
  		 throw new EntityExistsException("Request Mismatch");
  		
  		 Optional<PoliceStationMaster> policeTemp= policestationRepo.findById(police.getPolicestationname());
  	    	 
  		 if (policeTemp == null)
  			 throw new EntityExistsException("The Specified Police Station Name doesn't exists");
   		}
  	  else
  	  	{
  		  throw new EntityExistsException("Request Mismatch");
  	  	}
  	 
   	 	return policestationRepo.save(police);
    }
    
    
   // @PreAuthorize("hasAuthority('Police_Station_Master_VIEW')")
    @GetMapping("/{policestationname}")
    public Optional<PoliceStationMaster> getPolicestationById(@PathVariable(value = "policestationname") String policestationname) 
    {
    	Optional<PoliceStationMaster> policeObj = policestationRepo.findById(policestationname);
    	
    	if (policeObj == null)
    	{
    		new RuntimeException("Can not Find police station with name= "+policestationname);
    	}
		return policeObj;
    }
    
    @GetMapping("/getAllPoliceStationList")
    public List<SelectionList> getAllPoliceList(){
        return policestationRepo.getAllPoliceList();
    }
    
    @GetMapping("/getActivePoliceStationList")
    public List<SelectionList> getActivePoliceStationList(){
        return policestationRepo.getActivePoliceStationList();
    }  
   
    @GetMapping("/getPoliceStationFromCity/{city}")
    public List<SelectionList> getPoliceStationFromCity(@PathVariable(value = "city") String city){
        return policestationRepo.getPoliceStationFromCity(city);
    } 
    
    @GetMapping("/getActivePoliceStationFromCity/{city}")
    public List<SelectionList> getActivePoliceStationFromCity(@PathVariable(value = "city") String city){
        return policestationRepo.getActivePoliceStationFromCity(city);
    }
    
    @PreAuthorize("hasAuthority('Police_Station_Master_DELETE')")
    @DeleteMapping("/{police}")
    public PoliceStationMaster deleteLocation(@PathVariable(value = "police") String police){

        Optional<PoliceStationMaster> policeList= policestationRepo.findById(police);

        if(policeList== null)
        {
            throw new EntityNotFoundException("The Specified Police Station deos not exists");
        }

        if (policeList.get().getIsDeleted()){
            throw new EntityNotFoundException("Police Station already deleted");
        }
        else{
        	policeList.get().setIsDeleted(true);
            return policestationRepo.save(policeList.get());
        }
    }

}