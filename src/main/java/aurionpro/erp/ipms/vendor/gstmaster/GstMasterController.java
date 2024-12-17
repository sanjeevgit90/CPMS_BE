package aurionpro.erp.ipms.vendor.gstmaster;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

@RestController
@RequestMapping(value = "/ipms/gst")
public class GstMasterController {
	
	@Autowired
	GstMasterRepository gstMasterRepo;
	
	@PostMapping("/addgst")
    public GstMaster createGstMaster(@RequestBody GstMaster gstMasterRequest){
		validateRequest(gstMasterRequest);

		//for checking if gstin exist for selected state (for a party)
		/*List<GstMaster> gstList = gstMasterRepo.findByPartyMasterParent(gstMasterRequest.getPartyMasterParent());
		for(GstMaster g : gstList) {
			if(gstMasterRequest.getState().equalsIgnoreCase(g.getState()))
				throw new RuntimeException("Gst entity already exist for the selected state.");
		}*/
        return gstMasterRepo.save(gstMasterRequest);
    }

	@PutMapping("/{gstId}")
    public GstMaster updateGstMaster(@PathVariable(value="gstId") long gstId, @RequestBody GstMaster gstMasterRequest){
        if (gstId!=gstMasterRequest.getEntityId())
        	throw new RuntimeException("Request mismatch");
        
        validateRequest(gstMasterRequest);

        Optional<GstMaster> gstTemp = gstMasterRepo.findById(gstId);

        if (gstTemp==null) {
        	throw new RuntimeException("Gst entity does not exists.");
        }
        return gstMasterRepo.save(gstMasterRequest);
    }
	
	@GetMapping("/getallgst")
    public List<GstMaster> getAllGst(){
        return gstMasterRepo.findAll();
    }
	
	@GetMapping("/getgstbyid/{id}")
    public Optional<GstMaster> getGstById(@PathVariable(value="id") long gstId){
        return gstMasterRepo.findById(gstId);
    }
	
	@GetMapping("/getallgstbypartyid/{id}")
    public List<GstMaster> getAllGstByPartyId(@PathVariable(value="id") long partyId){
        //return addressMasterRepository.findById(addressId);
		PartyMaster partyMasterParent = new PartyMaster();
		partyMasterParent.setEntityId(partyId);
		return gstMasterRepo.findByPartyMasterParent(partyMasterParent);
    }
	
	@GetMapping("/deletegstbyid/{id}")
    public void deleteGstById(@PathVariable(value="id") long gstId){
        GstMaster gst = gstMasterRepo.findById(gstId).get();
        if(gst == null)
        	throw new RuntimeException("Gst entity does not exists.");
        
        gst.setIsDeleted(!gst.getIsDeleted());
        gstMasterRepo.save(gst);
    }
	
	@GetMapping("/gstselectionlist")
    public List<SelectionList> getSelectionGstList(@PathVariable(value="id") long partyId){
        return gstMasterRepo.selectionGstList(partyId);
    }
	
	@GetMapping("getGstFromAddress/{partyId}/{id}")
    public GstMaster getGstByPartyAndPartyId(@PathVariable(value="partyId") long partyId, @PathVariable(value="id") long id){
        return gstMasterRepo.getGstByPartyAndPartyId(partyId, id);
    }
	
	//for uploading attachments
	@Autowired
    FileUploadService uploadService;
	
	@PostMapping("/uploadGstCertificate")
    public FileResponse uploadGstCertificate(@RequestParam("file") MultipartFile file){
        String subFolder="Party/GST";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	private boolean validateRequest(GstMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getGstNo()))
			errorlist.add("GSTIN cannot be null.");
		if(StringUtils.isEmpty(request.getState()))
			errorlist.add("State cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("GST entity is invalid", errorlist);
		}
		return true;
	}
}
