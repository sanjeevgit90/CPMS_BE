package aurionpro.erp.ipms.assetmgmt.oemdeliverychallan;

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
import aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan.InterDistrictDeliveryChallan;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/oemdc")
public class OEMDeliveryChallanController {

    @Autowired
    OEMDeliveryChallanService dcservice;
    
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_ADD')")
    @PostMapping()
    public OEMDeliveryChallan createDC(@Valid @RequestBody OEMDeliveryChallan dc){
    		 validate();
 			 return dcservice.createDC(dc);
    }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_ADD')")
    @GetMapping("/submitDC/{id}")
    public OEMDeliveryChallan submitDC(@PathVariable(value="id") Long id){
    		 validate();
 			 return dcservice.submitDC(id);
    }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_EDIT')")
    @PutMapping()
    public OEMDeliveryChallan updateDC(@Valid @RequestBody OEMDeliveryChallan dc){	 
    	validate();
		 return dcservice.updateDC(dc);
    }
    
  //  @PreAuthorize("hasAuthority('OEM_Delivery_Challan_VIEW')")
    @GetMapping("/{id}")
    public Optional<OEMDeliveryChallan> findDCById(@PathVariable(value = "id") Long id) 
    {
    	return dcservice.findDCById(id);
    }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_DELETE')")
    @DeleteMapping("/{id}")
    public OEMDeliveryChallan deleteDC( @PathVariable(value="id") Long id){
           return dcservice.deleteDC(id);
    }
       
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_VIEW')")
    @PutMapping("/disable/{id}")
    public OEMDeliveryChallan disablePrint( @PathVariable(value="id") Long id, @RequestBody OEMDeliveryChallan dc){
    	 validate();
		 return dcservice.disablePrint(id, dc);
    }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_VIEW')")
    @GetMapping("/enableUpload/{id}")
   public OEMDeliveryChallan enableUpload(@PathVariable(value = "id") Long id ){
		 return dcservice.enableUpload(id);
   }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_VIEW')")
    @GetMapping("/taskByid/{id}")
    public Optional<OEMDCTaskMaster> taskByid(@PathVariable(value = "id") Long id) {
    		return dcservice.taskByid(id);
    }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_EDIT')")
    @PostMapping("/processDC")
    public OEMDeliveryChallan processDC(@Valid @RequestBody OEMDCTaskMaster dc){
    		return dcservice.processDC(dc);
    }
    
    @GetMapping("/receiveAsset/{id}/{status}")
    public OEMDeliveryChallan receiveAssetByOEM( @PathVariable(value="id") Long id, @Valid @PathVariable(value="status") String status ){
		 return dcservice.receiveAssetByOEM(id, status);
    }
       
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_VIEW')")
    @PostMapping("/dcByFilter")
    public List<OEMDeliveryChallanView> searchDC(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody OEMDeliveryChallanView dc){
    		return dcservice.searchDC(dc, page, size);
    }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_VIEW')")
    @GetMapping("/viewDc/{id}")
    public Optional<OEMDeliveryChallanView> viewDCById(@PathVariable(value = "id") Long id) 
    {
    	return dcservice.viewDCById(id);
    }
    
    @PreAuthorize("hasAuthority('OEM_Delivery_Challan_VIEW')")
    @PostMapping("/uploadDCAttachment/{folderName}")
    public FileResponse uploadAttachment(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder="OEMDC/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    
    private void validate() {
    }
    @PostMapping("/addCourierDetails/{id}")
    public OEMDeliveryChallan addCourierDetails( @PathVariable(value = "id") Long id, @RequestBody CourierDetailsRequest dc){
    		return dcservice.addCourierDetails(dc, id);
    }
    
    @GetMapping("/getCourierDetails/{id}")
    public CourierDetailsRequest getCourierDetails( @PathVariable(value = "id") Long id){
    		return dcservice.getCourierDetails(id);
    }
}