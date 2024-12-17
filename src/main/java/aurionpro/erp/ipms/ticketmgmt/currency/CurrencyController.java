package aurionpro.erp.ipms.ticketmgmt.currency;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/currencyMaster")

public class CurrencyController {

		@Autowired
	    CurrencyRepository currencyRepository;

		@PreAuthorize("hasAuthority('Currency_Master_VIEW')")
		@PostMapping("/currencyByFilter")
	    public List<CurrencyMaster> getCurrencyByFilter(@RequestBody CurrencyMaster currencyMaster){
	        
	        ExampleMatcher em=ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

	        Example<CurrencyMaster> currencyEx=Example.of(currencyMaster,em);

	        return currencyRepository.findAll(currencyEx);
	        
	    }

	    
	    @GetMapping("/getAllCurrencyList")
	    public List<SelectionList> getAllCurrencyList(){
	        return currencyRepository.getAllCurrencyList();
	    }
	     
	    @GetMapping("/getActiveCurrencyList")
	    public List<SelectionList> getActiveCurrencyList(){
	        return currencyRepository.getActiveCurrencyList();
	    }

	    
	    @PreAuthorize("hasAuthority('Currency_Master_ADD')")
		@PostMapping()
	    public CurrencyMaster createCurrencyMaster(@Valid @RequestBody CurrencyMaster currencyMaster){
	    	Optional<CurrencyMaster> currencyTemp= currencyRepository.findById(currencyMaster.getCurrencyName());
	    	
	  		 if(currencyTemp.isPresent())
	           {
	               throw new EntityExistsException("The Specified Currency Name already exists");
	           }
	  		 else
	  		 {
	  			 validate();
	  			 return currencyRepository.save(currencyMaster);
	  		 }
	    }
	   
	    
	    
	    private void validate() {
	    }
	    
	    
	    @PreAuthorize("hasAuthority('Currency_Master_DELETE')")
		@DeleteMapping("/{currencyName}")
	    public CurrencyMaster deleteCurrency(@PathVariable(value = "currencyName") String currencyName){
	        CurrencyMaster curr = currencyRepository.findById(currencyName).get();
	        if(curr == null)
	        	throw new RuntimeException("Currency entity does not exists.");
	        
	        curr.setIsDeleted(!curr.getIsDeleted());
	        return currencyRepository.save(curr);
	    }
	    
	    @GetMapping("/{currencyName}")
	    public Optional<CurrencyMaster> getCurrencyByCurrencyName(@PathVariable(value = "currencyName") String currencyName)
	    {
	    	Optional<CurrencyMaster> currencyObj = currencyRepository.findById(currencyName);
	       	if (currencyObj == null)
	    	{
	       		new RuntimeException("The currency name "+ currencyName +" does not exists");
	    	}
			return currencyObj;
	    }
	    
}
