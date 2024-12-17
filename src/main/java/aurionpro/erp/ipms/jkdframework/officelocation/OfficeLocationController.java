package aurionpro.erp.ipms.jkdframework.officelocation;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/officelocation")
public class OfficeLocationController {

    @Autowired
    private OfficeLocationRepository officeRepo;

    @PreAuthorize("hasAuthority('Office_Location_VIEW')")
     @GetMapping()
    public Iterable<OfficeLocation> getAllOfficeLocation(){
        return officeRepo.findAll();
    }

    @GetMapping("/selectionlist")
    public List<SelectionList> getOfficeList(){
        return officeRepo.getOfficeList();
    }

    @GetMapping("/{officeId}")
    public OfficeLocation getOfficeById(@PathVariable(value = "officeId") Long officeId){
        Optional<OfficeLocation> officeList= officeRepo.findById(officeId);

        if (officeList.isPresent())
            return officeList.get();
        else
            throw new EntityValidationException("Invalid Data","Office Location Not Found");
    }

    @PreAuthorize("hasAuthority('Office_Location_ADD')")
    @PostMapping()
    public OfficeLocation createOffice(@RequestBody OfficeLocation office){

        List<OfficeLocation> orgList= officeRepo.findByOfficename(office.getOfficename());

        if(orgList.size()>0)
        {
            throw new EntityExistsException("The Specified Office already exists");
        }
        else{
            validate();
            return officeRepo.save(office);
        }
    }

    private void validate() {
    }
    
    @PreAuthorize("hasAuthority('Office_Location_EDIT')")
    @PutMapping()
    public OfficeLocation updateOffice(@Valid @RequestBody OfficeLocation office){
    	Optional<OfficeLocation> officeTemp= officeRepo.findById(office.getEntityId());
	    	 
	   	if (officeTemp == null)
	   		throw new EntityExistsException("The Specified Office Location doesn't exists");
	   		 
	   	if(!(officeTemp.get().getOfficename().equalsIgnoreCase(office.getOfficename())))
	        throw new RuntimeException("Office name in Request does not match with the Office Name");	 
	  
		return officeRepo.save(office);
    }

}