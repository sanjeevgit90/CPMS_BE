package aurionpro.erp.ipms.ordermgmt.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/constant")
public class ConstantMasterController {

	@Autowired
	ConstantMasterRepository constantMasterRepo;
	
	@Autowired
	ConstantMasterViewRepository constantMasterViewRepo;
	
	 @PreAuthorize("hasAuthority('Constant_Master_ADD')")
	@PostMapping()
    public ConstantMaster addConstant(@Valid @RequestBody ConstantMaster request){
		validateRequest(request);
		return constantMasterRepo.save(request);
    }
	
	 @PreAuthorize("hasAuthority('Constant_Master_EDIT')")	
	@PutMapping("/{id}")
    public ConstantMaster updateConstant(@PathVariable(value="id") long id, @Valid @RequestBody ConstantMaster request){
		validateRequest(request);
        if (id!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");

        Optional<ConstantMaster> constantTemp=constantMasterRepo.findById(id);

        if (constantTemp==null) {
        	throw new RuntimeException("Constant entity does not exists.");
        }

        return constantMasterRepo.save(request);
    }
	
	 //@PreAuthorize("hasAuthority('Constant_Master_VIEW')")
	@PostMapping("/getallconstants")
    public List<ConstantMasterView> getAllConstants(@RequestBody ConstantMasterView request){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.EXACT);
		Example<ConstantMasterView> constantEx = Example.of(request,em);
		return constantMasterViewRepo.findAll(constantEx);
        //return constantMasterRepo.findAll();
    }
	
	//@GetMapping("/getconstantbyid/{id}")
	@GetMapping("/{id}")
    public Optional<ConstantMaster> getConstantById(@PathVariable(value="id") long id){
        return constantMasterRepo.findById(id);
    }
	
	 @PreAuthorize("hasAuthority('Constant_Master_DELETE')")
	@DeleteMapping("/{id}")
    public void deleteConstantById(@PathVariable(value="id") long id){
		Optional<ConstantMaster> cm = constantMasterRepo.findById(id);
		if(cm == null)
			throw new RuntimeException("Constant entity does not exists.");

		constantMasterRepo.deleteById(id);
    }
	
	@GetMapping("/constantselectionlist")
    public List<SelectionList> getSelectionConstantList(){
        return constantMasterRepo.selectionConstantList();
    }
	
	@GetMapping("/constantbyorgselectionlist/{orgId}")
    public List<SelectionList> getSelectionConstantList(@PathVariable(value="orgId") long orgId){
        return constantMasterRepo.selectionConstantByOrgList(orgId);
    }
	
	private boolean validateRequest(ConstantMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getType()))
			errorlist.add("Type cannot be null.");
		if(StringUtils.isEmpty(request.getValue()))
			errorlist.add("Value cannot be null.");
		if(StringUtils.isEmpty(request.getOrganisationId()))
			errorlist.add("Organisation cannot be null.");
        
		if(errorlist.size()>0) {
			throw new EntityValidationException("Constant entity is invalid", errorlist);
		}
        return true;
    }
}
