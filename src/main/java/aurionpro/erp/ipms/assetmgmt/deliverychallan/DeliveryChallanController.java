package aurionpro.erp.ipms.assetmgmt.deliverychallan;

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

import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/dc")
public class DeliveryChallanController {

    @Autowired
    DeliveryChallanService dcservice;
    
    @Autowired
    FileUploadService uploadService;
    
    @PreAuthorize("hasAuthority('Delivery_Challan_VIEW')")
    @PostMapping("/dcByFilter")
    public List<DeliveryChallanView> searchDC( @RequestParam(name = "page", required = false) Integer page,
       		@RequestParam(name = "size", required = false) Integer size, @RequestBody DeliveryChallanView dc){
    		return dcservice.searchDC(dc, page, size);
    }

    @PreAuthorize("hasAuthority('Delivery_Challan_ADD')")
    @PostMapping()
    public DeliveryChallan createDC(@Valid @RequestBody DeliveryChallan dc){
    		 validate();
 			 return dcservice.createDC(dc);
    }
    
    @PreAuthorize("hasAuthority('Delivery_Challan_EDIT')")
    @PutMapping()
    public DeliveryChallan updateDC(@Valid @RequestBody DeliveryChallan dc){
    	 validate();
		 return dcservice.updateDC(dc);
    }
    
   // @PreAuthorize("hasAuthority('Delivery_Challan_VIEW')")
    @GetMapping("/{id}")
    public Optional<DeliveryChallan> findDCById(@PathVariable(value = "id") Long id) 
    {
    	return dcservice.findDCById(id);
    }
    
    @PreAuthorize("hasAuthority('Delivery_Challan_DELETE')")
    @DeleteMapping("/{id}")
    public DeliveryChallan deleteDC(@PathVariable(value = "id") Long id) 
    {
           return dcservice.deleteDC(id);
    }
       
    @PreAuthorize("hasAuthority('Delivery_Challan_VIEW')")
    @PutMapping("/disable/{id}")
    public DeliveryChallanView disablePrint(@PathVariable(value = "id") Long id, @RequestBody DeliveryChallan dc ){
		 return dcservice.disablePrint(id, dc);
    }
    @PreAuthorize("hasAuthority('Delivery_Challan_VIEW')")
    @GetMapping("/enableUpload/{id}")
    public DeliveryChallan enableUpload(@PathVariable(value = "id") Long id ){
		 return dcservice.enableUpload(id);
    }
    
    @PreAuthorize("hasAuthority('Delivery_Challan_VIEW')")
    @PostMapping("/uploadDCAttachment/{folderName}")
    public FileResponse uploadAttachment(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder="DC/"+ folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PreAuthorize("hasAuthority('Delivery_Challan_VIEW')")
    @GetMapping("/viewDc/{id}")
    public Optional<DeliveryChallanView> viewDCById(@PathVariable(value = "id") Long id) 
    {
    	return dcservice.viewDCById(id);
    }
    
    private void validate() {
    }
    
    @PostMapping("/addCourierDetails/{id}")
    public DeliveryChallan addCourierDetails( @PathVariable(value = "id") Long id, @RequestBody CourierDetailsRequest dc){
    		return dcservice.addCourierDetails(dc, id);
    }
    
    @GetMapping("/getCourierDetails/{id}")
    public CourierDetailsRequest getCourierDetails( @PathVariable(value = "id") Long id){
    		return dcservice.getCourierDetails(id);
    }

}