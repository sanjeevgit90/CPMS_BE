package aurionpro.erp.ipms.ticketmgmt.licensemaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/licensemaster")
public class LicenseController {

    @Autowired
    LicenseRepository licenseRepository;


    @PreAuthorize("hasAuthority('Licence_Master_VIEW')")
	@GetMapping()
    public Iterable<LicenseMaster> getAllLicense(){
        return licenseRepository.findAll();
    }
    
    @GetMapping("/getAllLicenseList")
    public List<SelectionList> getAllLicenseList(){
        return licenseRepository.getAllLicenseList();
    }
     
    @GetMapping("/getActiveLicenseList")
    public List<SelectionList> getActiveLicenseList(){
        return licenseRepository.getActiveLicenseList();
    }
    
    @PreAuthorize("hasAuthority('Licence_Master_ADD')")
    @PostMapping()
    public LicenseMaster createLicense(@RequestBody LicenseMaster licenseMaster){
    	Optional<LicenseMaster> licenseTemp= licenseRepository.findById(licenseMaster.getLicenseTag());
    	
 		 if(licenseTemp.isPresent())
          {
              throw new EntityExistsException("The Specified License already exists");
          }
 		 else
 		 {
 			 validate();
 			 return licenseRepository.save(licenseMaster);
 		 }
   }
  
    @PreAuthorize("hasAuthority('Licence_Master_EDIT')")
    @PutMapping("/{licenseTag}")
    public LicenseMaster updateLicense(@PathVariable(value="licenseTag") String licenseTag, @RequestBody LicenseMaster licenseMaster){
    	if (!(StringUtils.isEmpty(licenseTag)))
   		{
   		 if (!(licenseTag.equalsIgnoreCase(licenseMaster.getLicenseTag())))
   		 throw new EntityExistsException("Request Mismatch");
   		
   		 Optional<LicenseMaster> licenseTemp= licenseRepository.findById(licenseMaster.getLicenseTag());
   	    	 
   		 if (licenseTemp == null)
   			 throw new EntityExistsException("The Specified License doesn't exists");
    		 
   		}
    	else
    	{
    		throw new EntityExistsException("Request Mismatch");
    	}
    		validate();
    		return licenseRepository.save(licenseMaster);
   	 	
    }
    
    
    private void validate() {
    }
    
    

//    @DeleteMapping()
//    public LicenseMaster deleteLicense(@RequestBody LicenseMaster licenseMaster){
//
//        Optional<LicenseMaster> licenseList= licenseRepository.findById(licenseMaster.getLicenseTag());
//
//        if(licenseList == null)
//        {
//            throw new EntityNotFoundException("The Specified License deos not exists");
//        }
//
//        if (licenseList.get().isDeleted()){
//            throw new EntityNotFoundException("Ticket license already deleted");
//        }
//        else{
//        	licenseMaster.setDeleted(true);
//            return licenseRepository.save(licenseMaster);
//        }
//    }
    
    @PreAuthorize("hasAuthority('Licence_Master_DELETE')")
    @DeleteMapping("{licenseTag}")
    public LicenseMaster DeleteDepartment(@PathVariable(value = "licenseTag") String licenseTag){

        Optional<LicenseMaster> licenceList= licenseRepository.findById(licenseTag);

        if(licenceList == null)
        {
            throw new EntityNotFoundException("The Specified Department deos not exists");
        }

        if (licenceList.get().getIsDeleted()){
            throw new EntityNotFoundException("Department already deleted");
        }
        else{
        	licenceList.get().setIsDeleted(true);
            return licenseRepository.save(licenceList.get());
        }
    }



    
    @GetMapping("/{licenseTag}")
    public Optional<LicenseMaster> getLicenseByLicenseNo(@PathVariable(value = "licenseTag") String licenseTag)
    {
    	Optional<LicenseMaster> licenseObj = licenseRepository.findById(licenseTag);
       	if (licenseObj == null)
    	{
       		new RuntimeException("Can not find vehicle with license Tag"+ licenseTag);
    	}
		return licenseObj;
    }
    @Autowired
    FileUploadService uploadService;

	
    @PostMapping("/uploadAttachment")
    public FileResponse uploadPaymentAdvice(@RequestParam("file") MultipartFile file){
        String subFolder="License";
        return uploadService.UploadSingleFile(subFolder, file);
    }

}  