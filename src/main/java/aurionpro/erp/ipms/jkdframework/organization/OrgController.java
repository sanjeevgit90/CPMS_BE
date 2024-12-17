package aurionpro.erp.ipms.jkdframework.organization;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/ipms/organization")
public class OrgController {

    @Autowired
    private OrgRepository orgRepo;

    @PreAuthorize("hasAuthority('Organization_Master_VIEW')")
    @GetMapping()
    public Iterable<Organization> getAllOrganizations(){
        return orgRepo.findAll();
    }

    @GetMapping("/selectionlist")
    public List<SelectionList> getOrgList(){
        return orgRepo.SelectionOrgList();
    }

    @GetMapping("/{orgId}")
    public Organization getOrgById(@PathVariable(value = "orgId") Long orgId){
        Optional<Organization> orgList= orgRepo.findById(orgId);

        if (orgList.isPresent())
            return orgList.get();
        else
            throw new EntityValidationException("Organization Invalid","Organization Not Found");
    }

    @PreAuthorize("hasAuthority('Organization_Master_ADD')")
     @PostMapping()
    public Organization createOrganization(@RequestBody Organization org){

        List<Organization> orgList= orgRepo.findByOrgName(org.getOrgName());

        if(orgList.size()>0)
        {
            throw new EntityExistsException("The Specified Organization already exists");
        }
        else{
            validate();
            return orgRepo.save(org);
        }
    }
    
    @PreAuthorize("hasAuthority('Organization_Master_EDIT')")
    @PutMapping()
   public Organization updateOrganization(@RequestBody Organization org){

       Optional<Organization> orgList= orgRepo.findById(org.getEntityId());

       if(orgList== null)
       {
           throw new EntityExistsException("The Specified Organization doesn't exists");
       }
       else{
           validate();
           return orgRepo.save(org);
       }
   }
    
    @PreAuthorize("hasAuthority('Organization_Master_DELETE')")
    @DeleteMapping("/{orgId}")
	public Organization deleteOrgById(@PathVariable(value = "orgId") Long id) {

        Optional<Organization> orgTemp=orgRepo.findById(id);

        if(!orgTemp.isPresent())
        {
            throw new EntityNotFoundException("Organization does not exist");
        }

        if(orgTemp.get().getIsDeleted())
        {
            throw new EntityNotFoundException("Organization already deleted");
        }
        else{
            orgTemp.get().setIsDeleted(true);
            return orgRepo.save(orgTemp.get());
        }
	}

    private void validate() {
    }

}