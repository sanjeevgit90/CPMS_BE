package aurionpro.erp.ipms.openbravo.vendor.party;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.InternalServerErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.authentication.JwtUtil;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.openbravo.dto.BankDetailDTO;
import aurionpro.erp.ipms.openbravo.dto.BankDetailsListDTO;
import aurionpro.erp.ipms.openbravo.dto.GstDetailDTO;
import aurionpro.erp.ipms.openbravo.dto.PartyMasterDTO;
import aurionpro.erp.ipms.openbravo.gstmaster.OpenBravoGstMaster;
import aurionpro.erp.ipms.openbravo.gstmaster.OpenBravoGstRepository;
import aurionpro.erp.ipms.openbravo.organisation.OpenBravoOrganisationRepository;
import aurionpro.erp.ipms.openbravo.vendor.address.OpenBravoAddressMaster;
import aurionpro.erp.ipms.openbravo.vendor.address.OpenBravoAddressRepository;
import aurionpro.erp.ipms.vendor.addressmaster.AddressMaster;
import aurionpro.erp.ipms.vendor.gstmaster.GstMaster;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@RestController
@RequestMapping(value = "/ipms/obparty")
public class OpenBravoPartyController {

	@Autowired
	OpenBravoPartyRepository partyMasterRepo;
	
	@Autowired
	OpenBravoAddressRepository addressMasterRepo;
	
	@Autowired
	OpenBravoGstRepository gstMasterRepo;
	
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

	
    @PostMapping("V2/vendor/add")
	public  String addVendor(@RequestBody PartyMasterDTO dtoRequest, HttpServletRequest httpServletRequest) {
    	
    	try {
			Long clientId = jwtUtil.getUserIdFromJWT(httpServletRequest);
			
			OpenBravoPartyMaster openbravoParty = new  OpenBravoPartyMaster();
			PartyMaster party = new PartyMaster();
			openbravoParty.setOpenBravoId(dtoRequest.getCaseId());			
		  if("Bank Name".equals(dtoRequest.getBankDetails().getBankDetails().get(0).getKey())) {
			party.setBankName(dtoRequest.getBankDetails().getBankDetails().get(0).getValue());
		   }
		  if("Bank Branch".equals(dtoRequest.getBankDetails().getBankDetails().get(1).getKey())) {
			party.setBranchNameandAddress(dtoRequest.getBankDetails().getBankDetails().get(1).getValue());
		  }
			party.setAccountNo(dtoRequest.getBankDetails().getBankAccountNumber());
			String accountType = dtoRequest.getBankDetails().getAccountType();
			if ("1".equals(accountType)) {
			    party.setAccountType("Saving");  
			} else if ("2".equals(accountType)) {
			    party.setAccountType("Current");
			}
			party.setContactPersonName(dtoRequest.getMerchantDetails().getContactPersonName());
			party.setDateOfIncorporation(dtoRequest.getBusinessDetails().getDateOfIncorporation());
			party.setEmailId(dtoRequest.getCompanyDetails().getEmail());
			party.setIfscNeftCode(dtoRequest.getBankDetails().getBankIfscCode());
			party.setMobileNo(dtoRequest.getCompanyDetails().getPhoneNumber());
			party.setPanNo(dtoRequest.getBusinessDetails().getCompanyPan());
			party.setPartyName(dtoRequest.getCompanyDetails().getCompanyName());
			party.setPartyType(dtoRequest.getMerchantDetails().getPartyType());
			party.setTanNo(dtoRequest.getBusinessDetails().getCompanyTan());
			party.setOrganisationId(391925L);
			Random random = new Random();
			int randomFourDigitNumber = 1000 + random.nextInt(9000);
			party.setPartyNo(String.valueOf(randomFourDigitNumber));
			party.setSmeRegNo("NA");
			party.setNatureOfServiceProviding("NA");
			openbravoParty.setParty(party);
			openbravoParty.setClientId(clientId);
			OpenBravoPartyMaster openbravopartynew = partyMasterRepo.save(openbravoParty);
			
			if(openbravopartynew.getParty().getEntityId() != null) {
				
				List<GstDetailDTO> gstDetails = dtoRequest.getGstDetails();
			  if(gstDetails != null && !gstDetails.isEmpty()) {
				  
				  for(GstDetailDTO gstDetailDTO : gstDetails) {			  
					  OpenBravoGstMaster openbravoGst = new OpenBravoGstMaster();
					GstMaster gst = new GstMaster();
					String openBravogstID = UUID.randomUUID().toString().replace("-", "");
					openbravoGst.setOpenBravoId(openBravogstID);
					gst.setGstNo(gstDetailDTO.getGstNumber());
					gst.setState(gstDetailDTO.getGstState());
					gst.setStatus("ACTIVE");
					gst.setPartyMasterParent(openbravopartynew.getParty());
					openbravoGst.setGst(gst);
					openbravoGst.setClientId(clientId);
					gstMasterRepo.save(openbravoGst);
					
					OpenBravoAddressMaster openbravoAddress = new OpenBravoAddressMaster();
					AddressMaster address = new AddressMaster();
					String openBravoaddressID = UUID.randomUUID().toString().replace("-", "");
					openbravoAddress.setOpenBravoId(openBravoaddressID);
					address.setAddress1(gstDetailDTO.getAddressLine1());
					address.setAddress2(gstDetailDTO.getAddressLine2());
					address.setAddressType(gstDetailDTO.getAddressType());
					address.setFullAddress(gstDetailDTO.getAddressLine1());
					address.setCity(gstDetailDTO.getCity());
					address.setContactNo(gstDetailDTO.getPhoneNumber().getNumber());
					address.setCountry(gstDetailDTO.getCountry());
					address.setState(gstDetailDTO.getGstState());
					address.setPinCode(gstDetailDTO.getPinCode());
					address.setStatus("ACTIVE");
					address.setPartyMasterParent(openbravopartynew.getParty());
					openbravoAddress.setAddress(address);
					
					openbravoAddress.setClientId(clientId);
					addressMasterRepo.save(openbravoAddress);
				  }
			  }
			}
		} catch (Exception e) {
	
			throw new InternalServerErrorException("Vendor is Not Created Successfully");
//			e.printStackTrace();
		}   	
       return "Vendor save Succesfully";
		
		
		
    	
    	
	}
}
