package aurionpro.erp.ipms.openbravo.vendor.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.authentication.JwtUtil;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.openbravo.organisation.OpenBravoOrganisationRepository;

@RestController
@RequestMapping(value = "/ipms/obparty")
public class OpenBravoPartyController {

	@Autowired
	OpenBravoPartyRepository partyMasterRepo;
	
	@Autowired
	OpenBravoOrganisationRepository openBravoOrganisationRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/addparty")
    public OpenBravoPartyMaster createPartyMaster(@Valid @RequestBody OpenBravoPartyMaster partyMasterRequest, HttpServletRequest httpServletRequest){
		
		Long clientId = jwtUtil.getUserIdFromJWT(httpServletRequest);
		validateRequest(partyMasterRequest);
		Optional<OpenBravoPartyMaster> tcheck = partyMasterRepo.findById(partyMasterRequest.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("Party ID already exists.");
        }
		
		//for replacing open bravo id's with our orgid in the request
//		if(StringUtils.isEmpty(partyMasterRequest.getParty().getObOrganisationId())){
//			throw new RuntimeException("OB-Organisation is required.");
//		} else {
//			Long orgId = openBravoOrganisationRepo.getOrgIdFromOpenBravoId(partyMasterRequest.getParty().getObOrganisationId());
//			if(StringUtils.isEmpty(orgId)) {
//				throw new RuntimeException("Organisation does not exists.");
//			}
//			partyMasterRequest.getParty().setOrganisationId(orgId);
//		}
		partyMasterRequest.setClientId(clientId);
        return partyMasterRepo.save(partyMasterRequest);
    }
	
	private boolean validateRequest(OpenBravoPartyMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		/*if(StringUtils.isEmpty(request.getPartyType()))
			errorlist.add("Party type cannot be null.");
		if(StringUtils.isEmpty(request.getPartyNo()))
			errorlist.add("Party no. cannot be null.");
		if(StringUtils.isEmpty(request.getPartyName()))
			errorlist.add("Party name cannot be null.");
		if(StringUtils.isEmpty(request.getContactPersonName()))
			errorlist.add("Contact person name cannot be null.");
		if(StringUtils.isEmpty(request.getMobileNo()))
			errorlist.add("Mobile no. cannot be null.");
		if(StringUtils.isEmpty(request.getEmailId()))
			errorlist.add("Email id cannot be null.");
		if(StringUtils.isEmpty(request.getNatureOfServiceProviding()))
			errorlist.add("Nature of service providing cannot be null.");
		if(StringUtils.isEmpty(request.getSmeRegNo()))
			errorlist.add("SME Registration no. cannot be null.");
		if(StringUtils.isEmpty(request.getPanNo()))
			errorlist.add("PAN no. cannot be null.");
		if(StringUtils.isEmpty(request.getTanNo()))
			errorlist.add("TAN no. cannot be null.");*/
		/*if(StringUtils.isEmpty(request.getContactId()))
			errorlist.add("Contact ID cannot be null.");
		if(StringUtils.isEmpty(request.getPriceList()))
			errorlist.add("Price list cannot be null.");*/
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Party entity is invalid", errorlist);
		}
		return true;
	}
}
