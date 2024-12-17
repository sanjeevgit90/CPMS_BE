package aurionpro.erp.ipms.vendor.partymaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/party")
public class PartyMasterController {

	@Autowired
	PartyMasterRepository partyMasterRepo;
	
	@PreAuthorize("hasAuthority('Party_Master_ADD')")
	@PostMapping("/addparty")
    public PartyMaster createPartyMaster(@RequestBody PartyMaster partyMasterRequest){
		validateRequest(partyMasterRequest);
		List<PartyMaster> tcheck = partyMasterRepo.findByPartyNo(partyMasterRequest.getPartyNo());
		if (tcheck.size()>0){
            throw new RuntimeException("Party entity already exists.");
        }
        return partyMasterRepo.save(partyMasterRequest);
    }

	@PreAuthorize("hasAuthority('Party_Master_EDIT')")
	@PutMapping("/{partyId}")
    public PartyMaster updatePartyMaster(@PathVariable(value="partyId") long partyId, @RequestBody PartyMaster partyMasterRequest){
        if (partyId!=partyMasterRequest.getEntityId())
        	throw new RuntimeException("Request mismatch");
        
        validateRequest(partyMasterRequest);

        Optional<PartyMaster> partyTemp=partyMasterRepo.findById(partyId);

        if (partyTemp==null) {
        	throw new RuntimeException("Party entity does not exists.");
        }
        /*if (!partyTemp.get().getPartyName().equalsIgnoreCase(partyMasterRequest.getPartyName())) {
        	throw new EntityValidationException("Party entity is invalid", "Party name mismatch.");
        }*/
        return partyMasterRepo.save(partyMasterRequest);
    }
	
	@PreAuthorize("hasAuthority('Party_Master_VIEW')")
	@PostMapping("/getallparty")
    public List<PartyMaster> getAllParty(@RequestBody PartyMaster partyMasterRequest){
		ExampleMatcher em = ExampleMatcher.matching().withIgnorePaths("dateOfIncorporation").withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PartyMaster> partyEx = Example.of(partyMasterRequest,em);
		return partyMasterRepo.findAll(partyEx);
        //return partyMasterRepo.findAll();
    }
	
	@GetMapping("/getpartybyid/{id}")
    public Optional<PartyMaster> getPartyById(@PathVariable(value="id") long partyId){
        return partyMasterRepo.findById(partyId);
    }
	
	@PreAuthorize("hasAuthority('Party_Master_DELETE')")
	@GetMapping("/deletepartybyid/{id}")
    public void deletePartyById(@PathVariable(value="id") long partyId){
		PartyMaster partyTemp=partyMasterRepo.findById(partyId).get();
        if (partyTemp==null) {
        	throw new RuntimeException("Party entity does not exists.");
        }
        
        partyTemp.setIsDeleted(!partyTemp.getIsDeleted());
        partyMasterRepo.save(partyTemp);
    }
	
	@GetMapping("/getpartyselectionlist")
    public List<SelectionList> getPartySelectionList(){
        return partyMasterRepo.selectionPartyList();
    }
	
	@GetMapping("/getpartybyorgselectionlist/{orgId}")
    public List<SelectionList> getPartyByOrgSelectionList(@PathVariable(value="orgId") long orgId){
        return partyMasterRepo.selectionPartyListByOrg(orgId);
    }
	
	//for uploading attachments
	@Autowired
    FileUploadService uploadService;
	
	@PostMapping("/uploadIncorporationCertificate")
    public FileResponse uploadIncorporationCertificate(@RequestParam("file") MultipartFile file){
        String subFolder="Party/Incorporation Certificate";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	@PostMapping("/uploadPartyPan")
    public FileResponse uploadPartyPan(@RequestParam("file") MultipartFile file){
        String subFolder="Party/PAN";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	@PostMapping("/uploadPartyTan")
    public FileResponse uploadPartyTan(@RequestParam("file") MultipartFile file){
        String subFolder="Party/TAN";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	@PostMapping("/uploadPartyArn")
    public FileResponse uploadPartyArn(@RequestParam("file") MultipartFile file){
        String subFolder="Party/ARN";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	@PostMapping("/uploadPartyCancelledCheque")
    public FileResponse uploadPartyCancelledCheque(@RequestParam("file") MultipartFile file){
        String subFolder="Party/Cancelled Cheque";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	private boolean validateRequest(PartyMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getPartyType()))
			errorlist.add("Party type cannot be null.");
		if(StringUtils.isEmpty(request.getPartyNo()))
			errorlist.add("Party no. cannot be null.");
		if(StringUtils.isEmpty(request.getPartyName()))
			errorlist.add("Party name cannot be null.");
		if(StringUtils.isEmpty(request.getOrganisationId()))
			errorlist.add("Organisation cannot be null.");
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
			errorlist.add("TAN no. cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Party entity is invalid", errorlist);
		}
		return true;
	}
}
