package aurionpro.erp.ipms.assetmgmt.hsncodemaster;

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
@RequestMapping(value = "/ipms/hsn")
public class HsnMasterController {

    @Autowired
    HsnMasterRepository hsnRepo;

    @PreAuthorize("hasAuthority('HSN_Master_VIEW')")
    @PostMapping("/hsnFilter")
    public Iterable<HsnCodeMaster> GetHSN(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody HsnCodeMaster hsnMaster){
    	
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<HsnCodeMaster> hsnEx=Example.of(hsnMaster,em);

        
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return hsnRepo.findAll(hsnEx, paging);
    	}
    	else
    	{
    		return hsnRepo.findAll(hsnEx);
    	}
    }

    @PreAuthorize("hasAuthority('HSN_Master_ADD')")
    @PostMapping()
    public HsnCodeMaster CreateHsnMaster(@Valid @RequestBody HsnCodeMaster hsnMaster){
             
        Optional<HsnCodeMaster> hsnTemp= hsnRepo.findByHsncode(hsnMaster.getHsncode());
    	
 		 if(hsnTemp.isPresent())
          {
              throw new EntityExistsException("The Specified HSN Code already exists");
          }
 		 else
 		 {
 			 validated();
 			return hsnRepo.save(hsnMaster);
 		 }
        
    }

    @PreAuthorize("hasAuthority('HSN_Master_EDIT')")
    @PutMapping()
    public HsnCodeMaster UpdateHsnMaster(@Valid @RequestBody HsnCodeMaster hsnEntity){
      
        Optional<HsnCodeMaster> hsntemp=hsnRepo.findById(hsnEntity.getEntityId());
    		
   		 	if (hsntemp == null)
   			 throw new EntityExistsException("The Specified HSN Code doesn't exists"); 
   		 	
   		 	if(!(hsntemp.get().getHsncode().equalsIgnoreCase(hsnEntity.getHsncode())))
	            throw new RuntimeException("HSN Code does not match with the Entity Code");	 
   		
    	validated();
    	return hsnRepo.save(hsnEntity);
    }

    private void validated(){
    	
    }
    
//    @PreAuthorize("hasAuthority('HSN_Master_VIEW')")
    @GetMapping("/{id}")
    public Optional<HsnCodeMaster> getHSNById(@PathVariable(value = "id") Long id) {
    	 Optional<HsnCodeMaster> hsnObj=hsnRepo.findById(id);
    	
    	if (hsnObj == null)
    	{
    		throw new RuntimeException("Can not Find HSNCode");
    	}
		return hsnObj;
    }
    
    @GetMapping("/getHsnCodeList")
    public List<SelectionList> getHsnCodeList(){
        return hsnRepo.getHsnCodeList();
    } 
    
    @GetMapping("/getActiveHsnCodeList")
    public List<SelectionList> getActiveHsnCodeList(){
        return hsnRepo.getActiveHsnCodeList();
    } 
     
    @PreAuthorize("hasAuthority('HSN_Master_DELETE')")
    @DeleteMapping("/{id}")
    public HsnCodeMaster deleteHsnCode(@PathVariable(value = "id") Long id){

    	Optional<HsnCodeMaster> hsnObj=hsnRepo.findById(id);
    	 
        if(hsnObj== null)
        {
            throw new EntityNotFoundException("The Specified HSN Code deos not exists");
        }

        if (hsnObj.get().getIsDeleted()){
            throw new EntityNotFoundException("HSN Code already deleted");
        }
        else{
        	hsnObj.get().setIsDeleted(true);
            return hsnRepo.save(hsnObj.get());
        }
    }

    @PostMapping("/getByHsnCode")
    public Optional<HsnCodeMaster> getHSNByCode(@RequestBody HsnCodeMaster hsnEntity) {
    	Optional<HsnCodeMaster> hsnObj=hsnRepo.findByHsncode(hsnEntity.getHsncode());
    	if (hsnObj == null){
    		throw new RuntimeException("Can not find HSN code");
    	}
		return hsnObj;
    }
}