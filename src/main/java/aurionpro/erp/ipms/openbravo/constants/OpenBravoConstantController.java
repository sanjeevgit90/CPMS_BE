package aurionpro.erp.ipms.openbravo.constants;

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
import aurionpro.erp.ipms.openbravo.organisation.OpenBravoOrganisationRepository;

@RestController
@RequestMapping(value = "/ipms/obconstant")
public class OpenBravoConstantController {

	@Autowired
	OpenBravoConstantRepository constantMasterRepo;
	
	@Autowired
	OpenBravoOrganisationRepository openBravoOrganisationRepo;
	
	@PostMapping()
    public OpenBravoConstantMaster addConstant(@Valid @RequestBody OpenBravoConstantMaster request){
		validateRequest(request);
		Optional<OpenBravoConstantMaster> tcheck = constantMasterRepo.findById(request.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("Constant ID already exists.");
        }
		
		//for replacing open bravo id's with our orgid in the request
		if(!StringUtils.isEmpty(request.getConstant().getObOrganisationId())){
			if(!request.getConstant().getObOrganisationId().equalsIgnoreCase("0")) {
				Long orgId = openBravoOrganisationRepo.getOrgIdFromOpenBravoId(request.getConstant().getObOrganisationId());
				if(StringUtils.isEmpty(orgId)) {
					throw new RuntimeException("Organisation does not exists.");
				}
				request.getConstant().setOrganisationId(orgId);
			} else {
				request.getConstant().setOrganisationId(0L);
			}
		} else {
			request.getConstant().setOrganisationId(0L);
		}

        return constantMasterRepo.save(request);
    }
	
	private boolean validateRequest(OpenBravoConstantMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getOpenBravoId()))
			errorlist.add("Open bravo ID cannot be null.");
        
		if(errorlist.size()>0) {
			throw new EntityValidationException("Constant entity is invalid", errorlist);
		}
        return true;
    }
}
