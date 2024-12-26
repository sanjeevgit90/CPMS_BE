package aurionpro.erp.ipms.openbravo.gstmaster;

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
import aurionpro.erp.ipms.openbravo.vendor.party.OpenBravoPartyRepository;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@RestController
@RequestMapping(value = "/ipms/obgst")
public class OpenBravoGstController {
	
	@Autowired
	OpenBravoGstRepository gstMasterRepo;
	
	@Autowired
	OpenBravoPartyRepository openBravoPartyRepo;

	@PostMapping("/addgst")
    public OpenBravoGstMaster createGSTMaster(@Valid @RequestBody OpenBravoGstMaster gstMasterRequest){
		validateRequest(gstMasterRequest);
        Optional<OpenBravoGstMaster> tcheck = gstMasterRepo.findById(gstMasterRequest.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("GST ID already exists.");
        }
		
		//for replacing open bravo id's with our id, name in the request
		Long partyId = openBravoPartyRepo.getPartyIdFromOpenBravoId(gstMasterRequest.getGst().getPartyId());
		if(StringUtils.isEmpty(partyId)) {
			throw new RuntimeException("Party does not exists.");
		}
		PartyMaster pm = new PartyMaster();
		pm.setEntityId(partyId);
		gstMasterRequest.getGst().setPartyMasterParent(pm);
		
        return gstMasterRepo.save(gstMasterRequest);
    }
	
	private boolean validateRequest(OpenBravoGstMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("GST entity is invalid", errorlist);
		}
		return true;
    }
}
