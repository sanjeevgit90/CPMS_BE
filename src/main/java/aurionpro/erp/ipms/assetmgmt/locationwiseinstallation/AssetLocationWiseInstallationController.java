package aurionpro.erp.ipms.assetmgmt.locationwiseinstallation;

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

import aurionpro.erp.ipms.assetmgmt.common.AssetInstallationData;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/locationinstallationreport")
public class AssetLocationWiseInstallationController {

    @Autowired
    AssetLocationWiseInstallationService reportService;
    
    @GetMapping("locationinstallation")
    public List<AssetInstallationData> locationinstallation(@RequestParam(name = "locationid") String id) 
    {
    	return reportService.locationinstallation(id);
    }
    
    @PreAuthorize("hasAuthority('USID_Installation_Report_ADD')")
    @PostMapping()
    public AssetLocationWiseInstallationMaster saveReport(@Valid @RequestBody AssetLocationWiseInstallationMaster constant){
    	     return reportService.saveReport(constant);
    }
   
    @PreAuthorize("hasAuthority('USID_Installation_Report_VIEW')")
    @PostMapping("/reportByFilter")
   public List<AssetLocationWiseInstallationView> reportByFilter(@RequestParam(name = "page", required = false) Integer page,
	@RequestParam(name = "size", required = false) Integer size, @RequestBody AssetLocationWiseInstallationView asset){
       return reportService.reportByFilter(asset, page, size);   
   }

    @PreAuthorize("hasAuthority('USID_Installation_Report_EDIT')")
    @PutMapping()
   public AssetLocationWiseInstallationMaster updateReport(@Valid @RequestBody AssetLocationWiseInstallationMaster asset){
   	 validate();
		 return reportService.updateReport(asset);
   }
   
  //  @PreAuthorize("hasAuthority('USID_Installation_Report_VIEW')")
     @GetMapping("/{id}")
   public Optional<AssetLocationWiseInstallationMaster> findReportById(@PathVariable(value = "id") Long id) 
   {
   	return reportService.findReportById(id);
   }
   
    @PreAuthorize("hasAuthority('USID_Installation_Report_DELETE')")
    @DeleteMapping("/{id}")
   public AssetLocationWiseInstallationMaster deleteReport(@PathVariable(value = "id") Long id){
          return reportService.deleteReport(id);
   }
    
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAuthority('USID_Installation_Report_VIEW')")
    @PostMapping("/uploadInstallation/{folderName}")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder="usidInstallation";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PreAuthorize("hasAuthority('USID_Installation_Report_VIEW')")
    @PutMapping("/disable/{id}")
    public AssetLocationWiseInstallationMaster disablePrint(@PathVariable(value = "id") Long id, @RequestBody AssetLocationWiseInstallationMaster dc ){
		 return reportService.disablePrint(id, dc);
    }
    @PreAuthorize("hasAuthority('USID_Installation_Report_VIEW')")
    @GetMapping("/enableUpload/{id}")
    public AssetLocationWiseInstallationMaster enableUpload(@PathVariable(value = "id") Long id ){
		 return reportService.enableUpload(id);
    }
    
  
    private void validate() {
    }

}