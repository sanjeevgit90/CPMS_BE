package aurionpro.erp.ipms.assetmgmt.manufacturemaster;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/manufacture")
public class ManufacturerMasterController {

    @Autowired
    ManufacturerMasterRepository manufacturerRepo;

    @PreAuthorize("hasAuthority('Manufacturer_Master_VIEW')")
    @PostMapping("/manufacturerByfilter")
    public Iterable<ManufacturerMaster> getAllManufacturer( @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody ManufacturerMaster manufacturer){
    	ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

   	 	Example<ManufacturerMaster> manufacturerEx=Example.of(manufacturer,em);
   	 	if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
   	 	{
   	 		Pageable paging = PageRequest.of(page, size);
   	 		return manufacturerRepo.findAll(manufacturerEx,paging).getContent();
   	 	}
   	 	else
   	 	{
   	 		return manufacturerRepo.findAll(manufacturerEx);
   	 	}
    }

    @PreAuthorize("hasAuthority('Manufacturer_Master_ADD')")
     @PostMapping()
    public ManufacturerMaster createManufacturer(@Valid @RequestBody ManufacturerMaster manufacturer){
    	Optional<ManufacturerMaster> manufacturerTemp= manufacturerRepo.findById(manufacturer.getManufacturername());
	    	
 		 if(manufacturerTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Manufacturer already exists");
          }
 		 else
 		 {
 			 validate();
 			 return manufacturerRepo.save(manufacturer);
 		 }
    }
          
    @GetMapping("/getManufacturerList")
    public List<SelectionList> getManufacturerList(){
        return manufacturerRepo.getManufacturerList();
    } 
    
    @GetMapping("/getActiveManufacturerList")
    public List<SelectionList> getActiveManufacturerList(){
        return manufacturerRepo.getActiveManufacturerList();
    } 
    
    @PreAuthorize("hasAuthority('Manufacturer_Master_DELETE')")
    @DeleteMapping("/{manufacterername}")
    public ManufacturerMaster deleteManufacturer(@PathVariable(value = "manufacterername") String manufacterername){

        Optional<ManufacturerMaster> manuList= manufacturerRepo.findById(manufacterername);

        if(manuList== null)
        {
            throw new EntityNotFoundException("The Specified Manufacturer deos not exists");
        }

        if (manuList.get().getIsDeleted()){
            throw new EntityNotFoundException("Manufacturer already deleted");
        }
        else{
        	manuList.get().setIsDeleted(true);
            return manufacturerRepo.save(manuList.get());
        }
    }
    
    private void validate() {
    }

}