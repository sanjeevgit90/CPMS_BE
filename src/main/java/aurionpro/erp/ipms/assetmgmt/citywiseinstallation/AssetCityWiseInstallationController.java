package aurionpro.erp.ipms.assetmgmt.citywiseinstallation;

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
@RequestMapping(value = "/ipms/ciyinstallationreport")
public class AssetCityWiseInstallationController {

    @Autowired
    AssetCityWiseInstallationService reportService;
    
    @GetMapping("cityinstallation/{id}")
    public List<AssetInstallationData> cityinstallation(@PathVariable(value = "id") String id) 
    {
    	return reportService.cityinstallation(id);
    }
    
    @PreAuthorize("hasAuthority('City_Installation_Report_ADD')")
    @PostMapping()
    public AssetCityWiseInstallationMaster saveReport(@Valid @RequestBody AssetCityWiseInstallationMaster constant){
    	     return reportService.saveReport(constant);
    }
   
    @PreAuthorize("hasAuthority('City_Installation_Report_VIEW')")
    @PostMapping("/reportByFilter")
   public List<AssetCityWiseInstallationView> reportByFilter(@RequestParam(name = "page", required = false) Integer page,
   		@RequestParam(name = "size", required = false) Integer size, @RequestBody AssetCityWiseInstallationView asset){
       return reportService.reportByFilter(asset, page, size);   
   }

    @PreAuthorize("hasAuthority('City_Installation_Report_EDIT')")
    @PutMapping()
   public AssetCityWiseInstallationMaster updateReport(@Valid @RequestBody AssetCityWiseInstallationMaster asset){
   	 validate();
		 return reportService.updateReport(asset);
   }
   
   // @PreAuthorize("hasAuthority('City_Installation_Report_VIEW')")
    @GetMapping("/{id}")
   public Optional<AssetCityWiseInstallationMaster> findReportById(@PathVariable(value = "id") Long id) 
   {
   	return reportService.findReportById(id);
   }
   
    @PreAuthorize("hasAuthority('City_Installation_Report_DELETE')")
    @DeleteMapping("/{id}")
   public AssetCityWiseInstallationMaster deleteReport(@PathVariable(value = "id") Long id){
          return reportService.deleteReport(id);
   }
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAuthority('City_Installation_Report_VIEW')")
    @PostMapping("/uploadInstallation/{folderName}")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder="cityInstallation/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PreAuthorize("hasAuthority('City_Installation_Report_VIEW')")
    @PutMapping("/disable/{id}")
    public AssetCityWiseInstallationMaster disablePrint(@PathVariable(value = "id") Long id, @RequestBody AssetCityWiseInstallationMaster dc ){
		 return reportService.disablePrint(id, dc);
    }
    @PreAuthorize("hasAuthority('City_Installation_Report_VIEW')")
    @GetMapping("/enableUpload/{id}")
    public AssetCityWiseInstallationMaster enableUpload(@PathVariable(value = "id") Long id ){
		 return reportService.enableUpload(id);
    }
    
  
    private void validate() {
    }

}