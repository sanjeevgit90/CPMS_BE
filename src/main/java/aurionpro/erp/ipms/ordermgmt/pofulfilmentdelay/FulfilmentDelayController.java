package aurionpro.erp.ipms.ordermgmt.pofulfilmentdelay;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/fulfilmentdelay")
public class FulfilmentDelayController {

	@Autowired
	FulfilmentDelayRepository fulfilmentDelayRepo;
	
	 //@PreAuthorize("hasAuthority('Fulfilment_Delay_ADD')")
	@PostMapping()
    public FulfilmentDelay saveData(@Valid @RequestBody FulfilmentDelay request){
 		validateRequest(request);
        return fulfilmentDelayRepo.save(request);
    }
	
	 //@PreAuthorize("hasAuthority('Fulfilment_Delay_EDIT')")
		@PutMapping("/{id}")
    public FulfilmentDelay updateData(@PathVariable(value="id") long id, @Valid @RequestBody FulfilmentDelay request){
		if (id!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");

        validateRequest(request);
        
        Optional<FulfilmentDelay> tempObj=fulfilmentDelayRepo.findById(id);
        if (tempObj==null) {
        	throw new EntityNotFoundException("Entity does not exists.");
        }
        return fulfilmentDelayRepo.save(request);
    }
	
	@GetMapping("/{id}")
    public Optional<FulfilmentDelay> getFulfilmentDelayById(@PathVariable(value = "id") long id) {
    	Optional<FulfilmentDelay> obj = fulfilmentDelayRepo.findById(id);
    	if (obj == null) {
    		throw new EntityNotFoundException("Entity does not exists.");
    	}
		return obj;
    }
	
	//@PreAuthorize("hasAuthority('Fulfilment_Delay_DELETE')")
	@DeleteMapping("/{id}")
    public void deleteFulfilmentDelayById(@PathVariable(value = "id") long id){
		Optional<FulfilmentDelay> obj = fulfilmentDelayRepo.findById(id);
        if(obj == null) {
            throw new EntityNotFoundException("Entity deos not exists.");
        }
        fulfilmentDelayRepo.deleteById(id);
    }
	
	//@PreAuthorize("hasAuthority('Fulfilment_Delay_VIEW')")
	@PostMapping("/searchfulfilmentdelay")
    public List<FulfilmentDelay> searchFulfilmentDelay(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody FulfilmentDelay request){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<FulfilmentDelay> objEx = Example.of(request,em);
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return fulfilmentDelayRepo.findAll(objEx, paging).getContent();
    	}
    	else
    	{
    		return fulfilmentDelayRepo.findAll(objEx);
    	}
	
    }
	
	@GetMapping("/getfulfilmentdelaysbypoid/{poId}")
    public List<FulfilmentDelay> getFulfilmentDelaysByPoId(@PathVariable(value = "poId") long poId) {
    	List<FulfilmentDelay> objList = fulfilmentDelayRepo.findByPurchaseOrderNo(poId);
		return objList;
    }
	
	private void validateRequest(@Valid FulfilmentDelay request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getFulfilmentDate()))
			errorlist.add("Fulfilment date should not be empty/null.");
		if(StringUtils.isEmpty(request.getDelayReason()))
			errorlist.add("Delay reason should not be empty/null.");
		if(StringUtils.isEmpty(request.getNextdeliveryDate()))
			errorlist.add("Next delivery date should not be empty/null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Fulfilment delay entity is invalid", errorlist);
		}
	}
}
