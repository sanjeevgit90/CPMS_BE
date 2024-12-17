package aurionpro.erp.ipms.assetmgmt.modelmaster;

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
@RequestMapping(value = "/ipms/model")
public class ModelMasterController {

    @Autowired
    ModelMasterRepository modelRepo;

    @PreAuthorize("hasAuthority('Model_Master_VIEW')")
    @PostMapping("/modelByfilter")
    public Iterable<ModelMaster> getAllModel(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody ModelMaster model){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<ModelMaster> modelEx=Example.of(model,em);

        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		 return modelRepo.findAll(modelEx, paging).getContent();
    	}
    	else
    	{
    		 return modelRepo.findAll(modelEx);
    	}
    }

    @PreAuthorize("hasAuthority('Model_Master_ADD')")
    @PostMapping()
    public ModelMaster createModel(@Valid @RequestBody ModelMaster model){
    	 Optional<ModelMaster> modelTemp= modelRepo.findById(model.getModelname());
	    	
   		 if(modelTemp.isPresent())
            {
                throw new EntityExistsException("The Specified Model already exists");
            }
   		 else
   		 {
   			 validate();
   			 return modelRepo.save(model);
   		 }
       
    }
    
    private void validate() {
		
	}

    @PreAuthorize("hasAuthority('Model_Master_EDIT')")
    @PutMapping()
    public ModelMaster updateModel(@Valid @RequestBody ModelMaster model){
       
		Optional<ModelMaster> modelObj = modelRepo.findById(model.getModelname());
   	    	 
   		 	if (modelObj == null)
   			 throw new EntityExistsException("The Specified Model doesn't exists"); 
   		validate();
    	return modelRepo.save(model);
    }
    
  //  @PreAuthorize("hasAuthority('Model_Master_VIEW')")
    @GetMapping("/{modelName}")
    public Optional<ModelMaster> getModelById(@PathVariable(value = "modelName") String modelName){
    	Optional<ModelMaster> modelObj = modelRepo.findById(modelName);
    	
    	if (modelObj == null)
    	{
    		throw new RuntimeException("Can not Find Model with model Name= "+modelName);
    	}
		return modelObj;
    }
    
    @GetMapping("/getModelList")
    public List<SelectionList> getModelList(){
        return modelRepo.getModelList();
    } 
    
    @GetMapping("/getActiveModelList")
    public List<SelectionList> getActiveModelList(){
        return modelRepo.getActiveModelList();
    } 
    
    @GetMapping("/getModelListFromManufacturer/{manufacturer}")
    public List<SelectionList> getModelListFromManufacturer(@PathVariable(value = "manufacturer") String manufacturer){
        return modelRepo.getModelListFromManufacturer(manufacturer);
    } 
    
    @GetMapping("/getActiveModelListFromManufacturer/{manufacturer}")
    public List<SelectionList> getActiveModelListFromManufacturer(@PathVariable(value = "manufacturer") String manufacturer){
        return modelRepo.getActiveModelListFromManufacturer(manufacturer);
    } 
    
    @PreAuthorize("hasAuthority('Model_Master_DELETE')")
    @DeleteMapping("/{modelname}")
    public ModelMaster deleteModel(@PathVariable(value = "modelname") String modelname){

        Optional<ModelMaster> modelList= modelRepo.findById(modelname);

        if(modelList== null)
        {
            throw new EntityNotFoundException("The Specified Model deos not exists");
        }

        if (modelList.get().getIsDeleted()){
            throw new EntityNotFoundException("Model already deleted");
        }
        else{
        	modelList.get().setIsDeleted(true);
            return modelRepo.save(modelList.get());
        }
    }

}