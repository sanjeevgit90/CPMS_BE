package aurionpro.erp.ipms.openbravo.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/obproject")
public class OpenBravoProjectController {

	@Autowired
	OpenBravoProjectRepository openBravoProjectRepo;

	@PostMapping()
    public OpenBravoProject createProject(@Valid @RequestBody OpenBravoProject request){
		validate(request);
		Optional<OpenBravoProject> tcheck = openBravoProjectRepo.findById(request.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("Project ID already exists.");
        }
        return openBravoProjectRepo.save(request);
    }
    
    private boolean validate(OpenBravoProject request) {
    	List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Project entity is invalid", errorlist);
		}
		return true;
	}
}
