package aurionpro.erp.ipms.openbravo.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/obcategory")
public class OpenBravoCategoryController {

	@Autowired
	OpenBravoCategoryRepository categoryRepo;

    @PostMapping()
    public OpenBravoCategoryMaster createCategory( @Valid @RequestBody OpenBravoCategoryMaster category){
    	Optional<OpenBravoCategoryMaster> categoryTemp= categoryRepo.findById(category.getOpenBravoId());
	    	
		if (categoryTemp.isPresent()) {
			throw new EntityExistsException("The specified category already exists");
		} else {
			validate(category);
			return categoryRepo.save(category);
		}
    }
    
    private boolean validate(OpenBravoCategoryMaster category) {
    	List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(category.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Category entity is invalid", errorlist);
		}
		return true;
    }
}
