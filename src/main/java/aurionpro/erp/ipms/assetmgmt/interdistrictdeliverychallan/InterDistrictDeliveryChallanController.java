package aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.assetmgmt.deliverychallan.CourierDetailsRequest;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/interdistrictdc")
public class InterDistrictDeliveryChallanController {

    @Autowired
    InterDistrictDeliveryChallanService dcservice;
    
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAuthority('Inter_District_DC_ADD')")
     @PostMapping()
    public InterDistrictDeliveryChallan createDC(@Valid @RequestBody InterDistrictDeliveryChallan dc){
    		 validate();
 			 return dcservice.createDC(dc);
    }
    
    @PreAuthorize("hasAuthority('Inter_District_DC_ADD')")
    @GetMapping("/submitDC/{id}")
    public InterDistrictDeliveryChallan submitDC(@PathVariable(value = "id") Long id){
    		 validate();
 			 return dcservice.submitDC(id);
    }
    
    @PreAuthorize("hasAuthority('Inter_District_DC_EDIT')")
    @PutMapping()
    public InterDistrictDeliveryChallan updateDC(@Valid @RequestBody InterDistrictDeliveryChallan dc){
    	 validate();
		 return dcservice.updateDC(dc);
    }
    
  //  @PreAuthorize("hasAuthority('Inter_District_DC_VIEW')")
    @GetMapping("/{id}")
    public Optional<InterDistrictDeliveryChallan> findDCById(@PathVariable(value = "id") Long id) 
    {
    	return dcservice.findDCById(id);
    }
    
    @PreAuthorize("hasAuthority('Inter_District_DC_DELETE')")
     @DeleteMapping("/{id}")
    public InterDistrictDeliveryChallan deleteDC(@PathVariable(value = "id") Long id){
           return dcservice.deleteDC(id);
    }
       
    
     @PreAuthorize("hasAuthority('Inter_District_DC_VIEW')")
     @PutMapping("/disable/{id}")
    public InterDistrictDCView disablePrint(@PathVariable(value = "id") Long id, @RequestBody InterDistrictDeliveryChallan dc ){
		 return dcservice.disablePrint(id, dc);
    }
     
     @PreAuthorize("hasAuthority('Inter_District_DC_VIEW')")
     @GetMapping("/enableUpload/{id}")
    public InterDistrictDeliveryChallan enableUpload(@PathVariable(value = "id") Long id ){
		 return dcservice.enableUpload(id);
    }
    
         
    @PreAuthorize("hasAuthority('Inter_District_DC_EDIT')")
    @PostMapping("/processDC")
    public InterDistrictDeliveryChallan processDC(@Valid @RequestBody InterDistrictDCTaskMaster dc){
    		return dcservice.processDC(dc);
    }
    
    @PreAuthorize("hasAuthority('Inter_District_DC_VIEW')")
    @GetMapping("/taskByid/{id}")
    public Optional<InterDistrictDCTaskMaster> taskByid(@PathVariable(value = "id") Long id) {
    		return dcservice.taskByid(id);
    }
    
    @PreAuthorize("hasAuthority('Inter_District_DC_VIEW')")
    @PostMapping("/dcByFilter")
    public List<InterDistrictDCView> searchDC(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody InterDistrictDCView dc){
    		return dcservice.searchDC(dc, page, size);
    }
    
    @PreAuthorize("hasAuthority('Inter_District_DC_VIEW')")
     @GetMapping("/viewDc/{id}")
    public Optional<InterDistrictDCView> viewDCById(@PathVariable(value = "id") Long id) 
    {
    	return dcservice.viewDCById(id);
    }
    
    @PreAuthorize("hasAuthority('Inter_District_DC_VIEW')")
    @PostMapping("/uploadDCAttachment/{folderName}")
    public FileResponse uploadAttachment(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder="InterDC/"+ folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    private void validate() {
    }
    @PostMapping("/addCourierDetails/{id}")
    public InterDistrictDeliveryChallan addCourierDetails( @PathVariable(value = "id") Long id, @RequestBody CourierDetailsRequest dc){
    		return dcservice.addCourierDetails(dc, id);
    }
    
    @GetMapping("/getCourierDetails/{id}")
    public CourierDetailsRequest getCourierDetails( @PathVariable(value = "id") Long id){
    		return dcservice.getCourierDetails(id);
    }
}