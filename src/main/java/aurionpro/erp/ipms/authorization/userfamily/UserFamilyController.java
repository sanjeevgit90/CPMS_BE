package aurionpro.erp.ipms.authorization.userfamily;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.utility.MyPrincipal;


@RestController
@RequestMapping(value = "/ipms/userfamily")
public class UserFamilyController {

    @Autowired
    private UserFamilyRepository ufRepo;

    @GetMapping("/myfamily")
    public Iterable<UserFamily> getAllMembers(){
        Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
        return ufRepo.findByUserprofileEntityId(profileId);
    }

    
    @PostMapping()
    public UserFamily CreateMember(@RequestBody UserFamily uFamily){

        Optional<UserFamily> ufTemp;

        if(uFamily.getEntityId()!=null)
        {
            ufTemp= ufRepo.findById(uFamily.getEntityId());

            if(ufTemp.isPresent())
            {
                throw new EntityExistsException("The Specified Member already exists");
            }
        }
        
        validate();
        UserProfile uProfile=new UserProfile();
        uProfile.setEntityId(MyPrincipal.getMyProfile().getUserProfileId());
        uFamily.setUserprofile(uProfile);
        return ufRepo.save(uFamily);
        
    }

    @PutMapping()
    public UserFamily UpdateMember(@RequestBody UserFamily uFamily){

        Optional<UserFamily> ufTemp= ufRepo.findById(uFamily.getEntityId());
        ufTemp.get().setMemberName(uFamily.getMemberName());
        ufTemp.get().setMemberContact(uFamily.getMemberContact());
        ufTemp.get().setRelationship(uFamily.getRelationship());
        ufTemp.get().setDob(uFamily.getDob());

        if(!ufTemp.isPresent())
        {
            throw new EntityNotFoundException("The Specified Member does not exists");
        }
        else{
            validate();
            return ufRepo.save(uFamily);
        }
    }

    @DeleteMapping("/{id}")
    public UserFamily DeleteMember(@PathVariable(name = "id") Long familyId){

        Optional<UserFamily> ufTemp= ufRepo.findById(familyId);

        if(!ufTemp.isPresent())
        {
            throw new EntityNotFoundException("The Specified Member does not exists");
        }

        ufRepo.delete(ufTemp.get());

        return ufTemp.get();

    }

    private void validate() {
    }
}