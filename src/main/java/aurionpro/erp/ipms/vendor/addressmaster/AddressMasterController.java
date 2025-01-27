package aurionpro.erp.ipms.vendor.addressmaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@RestController
@RequestMapping(value = "/ipms/address")
public class AddressMasterController {

	@Autowired
	AddressMasterRepository addressMasterRepository;
	
	@PostMapping("/addaddress")
    public AddressMaster createAddressMaster(@RequestBody AddressMaster addressMasterRequest){
		validateRequest(addressMasterRequest);
		if(StringUtils.isEmpty(addressMasterRequest.getAddress2()))
			addressMasterRequest.setAddress2(null);
		if(StringUtils.isEmpty(addressMasterRequest.getLandmark()))
			addressMasterRequest.setLandmark(null);
		
		addressMasterRequest.setFullAddress(getFullAddress(addressMasterRequest));
        return addressMasterRepository.save(addressMasterRequest);
    }
	
	@PutMapping("/{addressId}")
    public AddressMaster updateAddressMaster(@PathVariable(value="addressId") long addressId, @RequestBody AddressMaster addressMasterRequest){
		if (addressId!=addressMasterRequest.getEntityId())
			throw new RuntimeException("Request mismatch");
		
		validateRequest(addressMasterRequest);

        Optional<AddressMaster> addressTemp = addressMasterRepository.findById(addressId);

        if (addressTemp==null) {
        	throw new RuntimeException("Address entity does not exists.");
        }

		if(StringUtils.isEmpty(addressMasterRequest.getAddress2()))
			addressMasterRequest.setAddress2(null);
		if(StringUtils.isEmpty(addressMasterRequest.getLandmark()))
			addressMasterRequest.setLandmark(null);

		addressMasterRequest.setFullAddress(getFullAddress(addressMasterRequest));
        return addressMasterRepository.save(addressMasterRequest);
    }
	
	@GetMapping("/getalladdress")
    public List<AddressMaster> getAllAddress(){
        return addressMasterRepository.findAll();
    }
	
	@GetMapping("/getaddressbyid/{id}")
    public Optional<AddressMaster> getAddressById(@PathVariable(value="id") long addressId){
        return addressMasterRepository.findById(addressId);
    }
	
	@GetMapping("/getalladdressbypartyid/{id}")
    public List<SelectionList> getAllAddressByPartyId(@PathVariable(value="id") long partyId){
		/*PartyMaster partyMasterParent = new PartyMaster();
		partyMasterParent.setEntityId(partyId);*/
		return addressMasterRepository.getAllAddressByPartyId(partyId);
    }
	
	@GetMapping("/serachaddressbyparty/{id}")
    public List<AddressMaster> searchAddressByParty(@PathVariable(value="id") long partyId){
		PartyMaster partyMasterParent = new PartyMaster();
		partyMasterParent.setEntityId(partyId);
		return addressMasterRepository.findByPartyMasterParent(partyMasterParent);
    }
	
	@GetMapping("/deleteaddressbyid/{id}")
    public void deleteAddressById(@PathVariable(value="id") long gstId){
        AddressMaster address = addressMasterRepository.findById(gstId).get();
        if(address == null)
        	throw new RuntimeException("Address entity does not exists.");
        
        address.setIsDeleted(!address.getIsDeleted());
        addressMasterRepository.save(address);
    }
	
	@GetMapping("/comparestatesofaddress/{firstAddId}/{secondAddId}")
    public boolean compareStateOfAddress(@PathVariable(value="firstAddId") long firstAddId, @PathVariable(value="secondAddId") long secondAddId){
		return addressMasterRepository.compareStateOfAddress(firstAddId, secondAddId);
    }
	
	private String getFullAddress(AddressMaster request) {
		String fullAddress = "";
		fullAddress += request.getAddress1() + ", ";
		fullAddress += (StringUtils.isEmpty(request.getAddress2())) ? "" : request.getAddress2()+", ";
		fullAddress += (StringUtils.isEmpty(request.getLandmark())) ? "" : request.getLandmark()+", ";
		fullAddress += request.getCity() + ", " + request.getDistrict() + ", " + request.getState() + " - " + request.getPinCode() + ". " + request.getCountry() + ".";
		return fullAddress;
	}
	
	private boolean validateRequest(AddressMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getAddressType()))
			errorlist.add("Address type cannot be null.");
		if(StringUtils.isEmpty(request.getAddress1()))
			errorlist.add("Address line 1 cannot be null.");
		if(StringUtils.isEmpty(request.getState()))
			errorlist.add("State cannot be null.");
//		if(StringUtils.isEmpty(request.getDistrict()))
//			errorlist.add("District cannot be null.");
//		if(StringUtils.isEmpty(request.getCity()))
//			errorlist.add("City cannot be null.");
		if(StringUtils.isEmpty(request.getCountry()))
			errorlist.add("Country cannot be null.");
		if(StringUtils.isEmpty(request.getPartyMasterParent().getEntityId()))
			errorlist.add("Party cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Address entity is invalid", errorlist);
		}
		return true;
    }
}