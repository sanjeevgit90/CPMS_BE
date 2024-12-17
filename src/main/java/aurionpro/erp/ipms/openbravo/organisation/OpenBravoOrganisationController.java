package aurionpro.erp.ipms.openbravo.organisation;

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
@RequestMapping(value = "/ipms/oborganisation")
public class OpenBravoOrganisationController {

	@Autowired
	OpenBravoOrganisationRepository openBravoOrganisationRepo;

	@PostMapping()
    public OpenBravoOrganisation createOrganisation(@Valid @RequestBody OpenBravoOrganisation request){
		validate(request);
		Optional<OpenBravoOrganisation> tcheck = openBravoOrganisationRepo.findById(request.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("Organisation ID already exists.");
        }
        return openBravoOrganisationRepo.save(request);
    }
    
    private boolean validate(OpenBravoOrganisation request) {
    	List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Orgnisation entity is invalid", errorlist);
		}
		return true;
	}
}
