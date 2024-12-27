package aurionpro.erp.ipms.openbravo.vendor.address;

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
import aurionpro.erp.ipms.openbravo.vendor.party.OpenBravoPartyRepository;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@RestController
@RequestMapping(value = "/ipms/obaddress")
public class OpenBravoAddressController {

	@Autowired
	OpenBravoAddressRepository addressMasterRepo;
	
	@Autowired
	OpenBravoPartyRepository openBravoPartyRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/addaddress")
    public OpenBravoAddressMaster createAddressMaster(@Valid @RequestBody OpenBravoAddressMaster addressMasterRequest,HttpServletRequest httpServletRequest){
		Long clientId = jwtUtil.getUserIdFromJWT(httpServletRequest);
		validateRequest(addressMasterRequest);
        Optional<OpenBravoAddressMaster> tcheck = addressMasterRepo.findById(addressMasterRequest.getOpenBravoId());
		if (tcheck.isPresent()){
            throw new RuntimeException("Address ID already exists.");
        }
		
		//for replacing open bravo id's with our id, name in the request
//		Long partyId = openBravoPartyRepo.getPartyIdFromOpenBravoId(addressMasterRequest.getAddress().getPartyId());
//		if(StringUtils.isEmpty(partyId)) {
//			throw new RuntimeException("Party does not exists.");
//		}
//		PartyMaster pm = new PartyMaster();
//		pm.setEntityId(partyId);
//		addressMasterRequest.getAddress().setPartyMasterParent(pm);
		addressMasterRequest.setClientId(clientId);
        return addressMasterRepo.save(addressMasterRequest);
    }
	
	//not used
	/*private String getFullAddress(AddressMaster request) {
		String fullAddress = "";
		fullAddress += request.getAddress1() + ", ";
		fullAddress += (StringUtils.isEmpty(request.getAddress2())) ? "" : request.getAddress2()+", ";
		fullAddress += (StringUtils.isEmpty(request.getLandmark())) ? "" : request.getLandmark()+", ";
		fullAddress += request.getCity() + ", " + request.getDistrict() + ", " + request.getState() + " - " + request.getPinCode() + ". " + request.getCountry() + ".";
		return fullAddress;
	}*/
	
	private boolean validateRequest(OpenBravoAddressMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getOpenBravoId()))
			errorlist.add("Open bravo id cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Address entity is invalid", errorlist);
		}
		return true;
    }
}
