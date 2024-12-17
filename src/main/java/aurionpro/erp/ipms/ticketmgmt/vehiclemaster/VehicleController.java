package aurionpro.erp.ipms.ticketmgmt.vehiclemaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
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

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.ticketmgmt.ticketclassification.TicketClassification;
import aurionpro.erp.ipms.ticketmgmt.ticketmaster.TicketTaskView;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/vehiclemaster")

public class VehicleController {

	
	@Autowired
	VehicleService vehicleService;
	
	@Autowired
    VehicleRepository vehicleRepository;
	
	@Autowired
    FileUploadService uploadService;

	
	

//	 @PreAuthorize("hasAuthority('Vehicle_Master_VIEW')")
//	   @PostMapping("/vehicleByFilter")
//    public List<VehicleMaster> getVehicleByFilter(@RequestBody VehicleMaster vehicleMaster){
//        return vehicleService.getVehicleByFilter(vehicleMaster);
//        
//    }
	 
	 
	 @PreAuthorize("hasAuthority('Vehicle_Master_VIEW')")
	   @PostMapping("/vehicleByFilter")
	 public List<VehicleMaster> getVehicleByFilter(@RequestParam(name = "page", required = false) Integer page,
	    		@RequestParam(name = "size", required = false) Integer size,@RequestBody VehicleMaster vehicleMaster) {
	      
			return vehicleService.getVehicleByFilter(vehicleMaster, page,size);

	}

    
    @GetMapping("/getAllVehicleList")
    public List<SelectionList> getAllVehicleList(){
        return vehicleService.getAllVehicleList();
    }
     
    @GetMapping("/getActiveVehicleList")
    public List<SelectionList> getActiveVehicleList(){
        return vehicleService.getActiveVehicleList();
    }

    
    @PreAuthorize("hasAuthority('Vehicle_Master_ADD')")
	@PostMapping()
    public VehicleMaster createVehicleMaster(@Valid @RequestBody VehicleMaster vehicleMaster){
            return vehicleService.createVehicleMaster(vehicleMaster);
  		 }
    
   
    
    @PreAuthorize("hasAuthority('Vehicle_Master_EDIT')")
	@PutMapping("/{vehicleRegNumber}")
    public VehicleMaster updateVehicleMaster(@PathVariable(value="vehicleRegNumber")  String vehicleRegNumber, @RequestBody VehicleMaster vehicleMaster){
    
    		return vehicleService.updateVehicleMaster(vehicleMaster);
   	 	
    }
    
    
  
    
	/*
	 * @DeleteMapping() public VehicleMaster deleteVehicle(@RequestBody
	 * VehicleMaster vehicleMaster){
	 * 
	 * Optional<VehicleMaster> vehicleList=
	 * vehicleRepository.findById(vehicleMaster.getVehicleRegNumber());
	 * 
	 * if(vehicleList==null) { throw new
	 * EntityNotFoundException("The Specified Vehicle Register Number does not exists"
	 * ); }
	 * 
	 * if (vehicleList.get().isDeleted()){ throw new
	 * EntityNotFoundException("Vehicle already deleted"); } else{
	 * vehicleMaster.setDeleted(true); return vehicleRepository.save(vehicleMaster);
	 * } }
	 */
    
    @GetMapping("{vehicleRegNumber}")
    public Optional<VehicleMaster> getvehicleByRegNumber(@PathVariable(value = "vehicleRegNumber") String vehicleRegNumber)
    {
    	
		return vehicleService.getvehicleByRegNumber(vehicleRegNumber);
    }
    
    //@PreAuthorize("hasAuthority('Ticket_Vehicle_DELETE')")
    @DeleteMapping("{vehicleRegNo}")
    public VehicleMaster deleteVehicle(@PathVariable(value = "vehicleRegNo") String vehicleRegNo){

        Optional<VehicleMaster> vehicleList= vehicleRepository.findById(vehicleRegNo);

        if(vehicleList== null)
        {
            throw new EntityNotFoundException("The Specified Vehicle deos not exists");
        }

        if (vehicleList.get().getIsDeleted()){
            throw new EntityNotFoundException("Vehicle already deleted");
        }
        else{
        	vehicleList.get().setIsDeleted(true);
            return vehicleRepository.save(vehicleList.get());
        }
    }
    
    @PostMapping("/vehicleRegAttachment")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file){
        String vehicleReg="vehicle/VehicleRegistration";
        return uploadService.UploadSingleFile(vehicleReg, file);
    }
    
    @PostMapping("/insuranceCertificateAttachment")
    public FileResponse UploadfileInsurance(@RequestParam("file") MultipartFile file){
        String vehicleInsurance="vehicle/insuranceCertificate";
        return uploadService.UploadSingleFile(vehicleInsurance, file);
    }
    
    @PostMapping("/pollutionCertificateAttachment")
    public FileResponse UploadfilePollutionCertificate(@RequestParam("file") MultipartFile file){
        String pollutionCertificate="vehicle/pollutionCertificate";
        return uploadService.UploadSingleFile(pollutionCertificate, file);
    }
    
    @PostMapping("/otherDocumentsAttachment")
    public FileResponse UploadfileOtherDocuments(@RequestParam("file") MultipartFile file){
        String otherDocuments="vehicle/otherDocuments";
        return uploadService.UploadSingleFile(otherDocuments, file);
    }
    
    @PostMapping("/letterOfIntentAttachment")
    public FileResponse UploadfileLetterOfIntent(@RequestParam("file") MultipartFile file){
        String letterOfIntent="vehicle/letterOfIntent";
        return uploadService.UploadSingleFile(letterOfIntent, file);
    }
    
    @PostMapping("/invoiceAttachment")
    public FileResponse UploadfileInvoice(@RequestParam("file") MultipartFile file){
        String invoice="vehicle/invoice";
        return uploadService.UploadSingleFile(invoice, file);
    }
    
    @PostMapping("/releaseDocumentAttachment")
    public FileResponse UploadfileReleaseDocument(@RequestParam("file") MultipartFile file){
        String releaseDocument="vehicle/releaseDocument";
        return uploadService.UploadSingleFile(releaseDocument, file);
    }
    

}
