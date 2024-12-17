package aurionpro.erp.ipms.ticketmgmt.supportteam;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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
import aurionpro.erp.ipms.ticketmgmt.ticketclassification.TicketClassificationView;
import aurionpro.erp.ipms.ticketmgmt.vehiclemaster.VehicleMaster;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/supportteam")
public class SupportTeamController {

    @Autowired
    SupportTeamRepository supportTeamRepository;
    
	@Autowired
    FileUploadService uploadService;


    
//    @PreAuthorize("hasAuthority('Support_Team_VIEW')")
//    @GetMapping()
//    public Iterable<SupportTeam> getAllLicense(){
//        return supportTeamRepository.findAll();
//    }
    
    @GetMapping("/getAllSupportTeamList")
    public List<SelectionList> getAllSupportTeamList(){
        return supportTeamRepository.getAllSupportTeamList();
    }
     
    @GetMapping("/getActiveSupportTeamList")
    public List<SelectionList> getActiveSupportTeamList(){
        return supportTeamRepository.getActiveSupportTeamList();
    }
    
    //@PreAuthorize("hasAuthority('Support_Team_VIEW')")
    @PostMapping("/supportTeamByFilter")
    public Iterable<SupportTeam> getAllsupportTeam(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody SupportTeam supportTeam){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<SupportTeam> classEx=Example.of(supportTeam,em);
    	 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
     	{
     		Pageable paging = PageRequest.of(page, size);
     		 return supportTeamRepository.findAll(classEx, paging).getContent();
     	}
     	else
     	{
     		 return supportTeamRepository.findAll(classEx);
     	}
       
    }
    
    @PreAuthorize("hasAuthority('Support_Team_ADD')")
    @PostMapping()
    public SupportTeam createSupportTeam(@RequestBody SupportTeam SupportTeam){
    	Optional<SupportTeam> supportTemp= supportTeamRepository.findById(SupportTeam.getEmployeeId());
    	
 		 if(supportTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Employee already exists");
          }
 		 else
 		 {
 			 validate();
 			 return supportTeamRepository.save(SupportTeam);
 		 }
   }
  
    
    @PreAuthorize("hasAuthority('Support_Team_EDIT')")
     @PutMapping("/{employeeId}")
    public SupportTeam updateSupportTeam(@PathVariable(value="employeeId") String employeeId, @RequestBody SupportTeam supportTeam){
    	if (!(StringUtils.isEmpty(employeeId)))
   		{
   		 if (!(employeeId.equalsIgnoreCase(supportTeam.getEmployeeId())))
   		 throw new EntityExistsException("Request Mismatch");
   		
   		 Optional<SupportTeam> licenseTemp= supportTeamRepository.findById(supportTeam.getEmployeeId());
   	    	 
   		 if (licenseTemp == null)
   			 throw new EntityExistsException("The Specified Employee ID doesn't exists");
    		 
   		}
    	else
    	{
    		throw new EntityExistsException("Request Mismatch");
    	}
    		validate();
    		return supportTeamRepository.save(supportTeam);
   	 	
    }
    
    
    private void validate() {
    }
    
    
//    @PreAuthorize("hasAuthority('Support_Team_DELETE')")
//    @DeleteMapping()
//    public SupportTeam deleteSupportTeam(@RequestBody SupportTeam supportTeam){
//
//        Optional<SupportTeam> licenseList= supportTeamRepository.findById(supportTeam.getEmployeeId());
//
//        if(licenseList == null)
//        {
//            throw new EntityNotFoundException("The Specified Employee ID deos not exists");
//        }
//
//        if (licenseList.get().getIsDeleted()){
//            throw new EntityNotFoundException("Employee ID already deleted");
//        }
//        else{
//        	supportTeam.setIsDeleted(true);
//            return supportTeamRepository.save(supportTeam);
//        }
//    }
    
    @PreAuthorize("hasAuthority('Support_Team_DELETE')")
    @DeleteMapping("{employeeId}")
    public SupportTeam deleteSupportTeam(@PathVariable(value = "employeeId") String employeeId){

        Optional<SupportTeam> supportList= supportTeamRepository.findById(employeeId);

        if(supportList== null)
        {
            throw new EntityNotFoundException("The Specified Employee Id deos not exists");
        }

        if (supportList.get().getIsDeleted()){
            throw new EntityNotFoundException("Employee Id already deleted");
        }
        else{
        	supportList.get().setIsDeleted(true);
            return supportTeamRepository.save(supportList.get());
        }
    }


    
    @GetMapping("/{employeeId}")
    public Optional<SupportTeam> getSupportTeamByEmployeeId(@PathVariable(value = "employeeId") String employeeId)
    {
    	Optional<SupportTeam> supportTeamObj = supportTeamRepository.findById(employeeId);
       	if (supportTeamObj == null)
    	{
       		new RuntimeException("Can not find Support Team with Employee ID"+ employeeId);
    	}
		return supportTeamObj;
    }
    
    @PostMapping("/idProofAttachment")
    public FileResponse UploadIdProofAttachment(@RequestParam("file") MultipartFile file){
        String idDocument="vehicle/idProofAttachment";
        return uploadService.UploadSingleFile(idDocument, file);
    }
    
    @PostMapping("/addressProofAttachment")
    public FileResponse UploadfileAddressProo(@RequestParam("file") MultipartFile file){
        String addressDocument="vehicle/addressProofAttachment";
        return uploadService.UploadSingleFile(addressDocument, file);
    }
    	

}  