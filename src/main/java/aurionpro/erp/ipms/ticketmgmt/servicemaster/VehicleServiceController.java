package aurionpro.erp.ipms.ticketmgmt.servicemaster;

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
import aurionpro.erp.ipms.ticketmgmt.vehiclemaster.VehicleMaster;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/vehicleservice")
public class VehicleServiceController {

    @Autowired
    VehicleServiceRepository vehicleServiceRepository;
    
    @Autowired
    FileUploadService uploadService;


    @PreAuthorize("hasAuthority('Vehicle_Service_VIEW')")
    @GetMapping()
    public Iterable<VehicleServiceMaster> getAllVehicleService(){
        return vehicleServiceRepository.findAll();
    }
    
    @GetMapping("/getAllVehicleServiceList")
    public List<SelectionList> getAllVehicleServiceList(){
        return vehicleServiceRepository.getAllVehicleServiceList();
    }
     
    @GetMapping("/getActiveVehicleList")
    public List<SelectionList> getActiveVehicleList(){
        return vehicleServiceRepository.getActiveVehicleList();
    }
    
    @PreAuthorize("hasAuthority('Vehicle_Service_ADD')")
     @PostMapping()
    public VehicleServiceMaster createVehicle(@RequestBody VehicleServiceMaster vehicleServiceMaster){
    	Optional<VehicleServiceMaster> serviceTemp= vehicleServiceRepository.findById(vehicleServiceMaster.getVehicleRegistrationNumber());
    	
 		 if(serviceTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Vehicle Registration No already exists");
          }
 		 else
 		 {
 			 validate();
 			 return vehicleServiceRepository.save(vehicleServiceMaster);
 		 }
   }
  
    
    @PreAuthorize("hasAuthority('Vehicle_Service_EDIT')")
    @PutMapping("/{vehicleRegistrationNumber}")
    public VehicleServiceMaster updateVehicelService(@PathVariable(value="vehicleRegistrationNumber") String vehicleRegistrationNumber, @RequestBody VehicleServiceMaster vehicleServiceMaster){
    	if (!(StringUtils.isEmpty(vehicleServiceMaster)))
   		{
   		 if (!(vehicleRegistrationNumber.equalsIgnoreCase(vehicleServiceMaster.getVehicleRegistrationNumber())))
   		 throw new EntityExistsException("Request Mismatch");
   		
   		 Optional<VehicleServiceMaster> licenseTemp= vehicleServiceRepository.findById(vehicleServiceMaster.getVehicleRegistrationNumber());
   	    	 
   		 if (licenseTemp == null)
   			 throw new EntityExistsException("The Specified Registration No doesn't exists");
    		 
   		}
    	else
    	{
    		throw new EntityExistsException("Request Mismatch");
    	}
    		validate();
    		return vehicleServiceRepository.save(vehicleServiceMaster);
   	 	
    }
    
    
    private void validate() {
    }
    
    
//    @PreAuthorize("hasAuthority('Vehicle_Service_DELETE')")
//    @DeleteMapping()
//    public VehicleServiceMaster deleteVehicleService(@RequestBody VehicleServiceMaster vehicleServiceMaster){
//
//        Optional<VehicleServiceMaster> licenseList= vehicleServiceRepository.findById(vehicleServiceMaster.getVehicleRegistrationNumber());
//
//        if(licenseList == null)
//        {
//            throw new EntityNotFoundException("The Specified Registration No deos not exists");
//        }
//
//        if (licenseList.get().getIsDeleted()){
//            throw new EntityNotFoundException("Ticket Registration No already deleted");
//        }
//        else{
//        	vehicleServiceMaster.setIsDeleted(true);
//            return vehicleServiceRepository.save(vehicleServiceMaster);
//        }
//    }
    
    //@PreAuthorize("hasAuthority('Vehicle_Service_DELETE')")
    @DeleteMapping("{vehicleRegNo}")
    public VehicleServiceMaster deleteVehicle(@PathVariable(value = "vehicleRegNo") String vehicleRegNo){

        Optional<VehicleServiceMaster> vehicleList= vehicleServiceRepository.findById(vehicleRegNo);

        if(vehicleList== null)
        {
            throw new EntityNotFoundException("The Specified Vehicle deos not exists");
        }

        if (vehicleList.get().getIsDeleted()){
            throw new EntityNotFoundException("Vehicle already deleted");
        }
        else{
        	vehicleList.get().setIsDeleted(true);
            return vehicleServiceRepository.save(vehicleList.get());
        }
    }

    @GetMapping("{vehicleRegistrationNumber}")
    public Optional<VehicleServiceMaster> getvehicleByRegNumber(@PathVariable(value = "vehicleRegistrationNumber") String vehicleRegistrationNumber)
    {
    	Optional<VehicleServiceMaster> vehicleserviceObj = vehicleServiceRepository.findById(vehicleRegistrationNumber);
       	if (vehicleserviceObj == null)
    	{
       		new RuntimeException("Can not find vehicle Registration Number with vehicleRegNumber"+ vehicleRegistrationNumber);
    	}
		return vehicleserviceObj;
    }
    
    @PostMapping("/vehicleServieAttachment")
    public FileResponse UploadfileDocument(@RequestParam("file") MultipartFile file){
        String releaseDocument="vehicleService/vehicleServieAttachment";
        return uploadService.UploadSingleFile(releaseDocument, file);
    }
    
	}  