package aurionpro.erp.ipms.assetmgmt.assetconstantmaster;

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
@RequestMapping(value = "/ipms/assetconstant")
public class AssetConstantMasterController {

    @Autowired
    AssetConstantMasterRepository constantRepo;
    
    @PreAuthorize("hasAuthority('AssetConstantMaster_VIEW')")
    @PostMapping("/constantByFilter")
    public Iterable<AssetConstantMaster> getAllConstant(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody AssetConstantMaster constant){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<AssetConstantMaster> constantEx=Example.of(constant,em);

     	if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
     		Pageable paging = PageRequest.of(page, size);
            return constantRepo.findAll(constantEx, paging).getContent();
    	}
    	else
    	{
            return constantRepo.findAll(constantEx);
    	}
    }
   
    @PreAuthorize("hasAuthority('AssetConstantMaster_ADD')")
    @PostMapping()
    public AssetConstantMaster createConstant(@Valid @RequestBody AssetConstantMaster constant){
    	Optional<AssetConstantMaster> constantTemp= constantRepo.findConstant(constant.getConstantname(), constant.getConstantnamefor());
	    	
 		 if(constantTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Name already exists");
          }
 		 else
 		 {
 			 validate();
 			 return constantRepo.save(constant);
 		 }
    }
    
    @PreAuthorize("hasAuthority('AssetConstantMaster_EDIT')")
    @PutMapping()
    public AssetConstantMaster updateConstant(@Valid @RequestBody AssetConstantMaster constant){
    	Optional<AssetConstantMaster> constantTemp= constantRepo.findById(constant.getEntityId());
   	    	 
   		 if (constantTemp == null)
   			 throw new EntityExistsException("The Specified Constant Name doesn't exists");
   		 
   		Optional<AssetConstantMaster> constantCheck= constantRepo.findConstant(constant.getConstantname(), constant.getConstantnamefor());
    	
		 if(constantCheck.isPresent())
         {
             throw new EntityExistsException("The Specified Name already exists");
         }
		 
    	 validate();
		 return constantRepo.save(constant);
    }
    
      @GetMapping("/{constantname}")
    public Optional<AssetConstantMaster> getConstantByName(@PathVariable(value = "constantname") Long constantname) 
    {
    	Optional<AssetConstantMaster> constantObj = constantRepo.findById(constantname);
    	
    	if (constantObj == null)
    	{
    		throw new RuntimeException("Can not find name= "+constantname);
    	}
    	
		return constantObj;
    }
      
    @GetMapping("/getEolList")
    public List<SelectionList> getEolList(){
        return constantRepo.getEolList();
    } 
    
    @GetMapping("/getActiveEolList")
    public List<SelectionList> getActiveEolList(){
        return constantRepo.getActiveEolList();
    } 
    
    @GetMapping("/getDepreciationList")
    public List<SelectionList> getDepreciationList(){
        return constantRepo.getDepreciationList();
    } 
    
    @GetMapping("/getActiveDepreciationList")
    public List<SelectionList> getActiveDepreciationList(){
        return constantRepo.getActiveDepreciationList();
    } 
    
    @PreAuthorize("hasAuthority('AssetConstantMaster_DELETE')")
    @DeleteMapping("/{constantname}")
    public AssetConstantMaster deleteConstant(@PathVariable(value = "constantname") Long constantname){

        Optional<AssetConstantMaster> constantList= constantRepo.findById(constantname);

        if(constantList== null)
        {
            throw new EntityNotFoundException("The Specified Name deos not exists");
        }

        if (constantList.get().getIsDeleted()){
            throw new EntityNotFoundException("Record already deleted");
        }
        else{
        	constantList.get().setIsDeleted(true);
            return constantRepo.save(constantList.get());
        }
    }
    
    private void validate() {
    }

}