package aurionpro.erp.ipms.authorization.userprofile;

import java.util.List;
import java.util.Optional;

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
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.jkdframework.userlogin.UserLogin;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/userprofile")
public class UserProfileController {

    @Autowired
    private UserProfileService profileService;

    @GetMapping("/selectionlist")
    public List<SelectionList> getProfileList(){
        return profileService.getUserProfileList();
    }
    
    @GetMapping("/selectionUserlist")
    public List<SelectionList> getUserNameList(){
        return profileService.getUserNameList();
    }

    @GetMapping("/{profileId}")
    public UserProfile getProfileById(@PathVariable(value = "profileId") Long profileId){
        Optional<UserProfile> upTemp= profileService.getUserProfileById(profileId);

        if (upTemp.isPresent())
            return upTemp.get();
        else
            throw new EntityValidationException("Invalid Profile Id","No User found with the specified Profile Id");
    }

    @GetMapping("/myprofile")
    public UserProfile getProfileById(){
        UserProfile upTemp= profileService.getUserProfileByName(MyPrincipal.getMyProfile().getUsername());
        
        if (upTemp==null)
            throw new EntityValidationException("Invalid Profile Id","No User found with the specified Profile Id");
        else
            return upTemp;
    }

    @GetMapping("/myteamprofile")
    public List<UserProfile> getMyTeamProfile(){
        return profileService.getMyTeamMembers();
    }

    @PostMapping("/profileByFilter")
    public List<UserProfileView> getMenuByFilter(@RequestBody UserProfileSearchRequest uProfile){
        
       return profileService.getUserProfileByFilter(uProfile);
        
    }

    @PostMapping()
    public UserProfile CreateProfile(@RequestBody UserProfile uProfile){

            return profileService.createUserProfile(uProfile);
    }

    @PutMapping()
    public UserProfile UpdateProfile(@RequestBody UserProfile uProfile){
        
        return profileService.UpdateUserProfile(uProfile);
        
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @PutMapping("/admin")
    public UserProfile UpdateProfileAdmin(@RequestBody UserProfile uProfile){
        
        return profileService.UpdateUserProfileAdmin(uProfile);
        
    }
    
	
	  @GetMapping("/getEmailMobileNoByUserProfile/{id}") 
	  public SelectionList getMobileNoByUserProfile(@PathVariable(value = "id") Long id){
		  return profileService.getEmailMobileNoByUserProfile(id); 
	 }
	 
	  
	 @DeleteMapping("/{profileId}")
	  public UserProfile deleteProfileById(@PathVariable(value = "profileId") Long profileId){
	        return profileService.deleteProfileById(profileId);
     }
     
    @Autowired
    FileUploadService uploadService;

    //@PreAuthorize("hasAuthority('MENU_ADD')")
    @PostMapping("/uploadProfileImage")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file){
        String subFolder="profileImages";
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PostMapping("/saveMobileAppKey")
    public UserProfile saveMobileAppKey(@RequestBody UserProfile uProfile){
       return profileService.saveMobileAppKey(uProfile);
    }
    
    @GetMapping("lockUnlockProfile/{status}/{profileId}")
	  public UserLogin lockUnlockProfile(@PathVariable(value = "status") String status, @PathVariable(value = "profileId") Long profileId){
	        return profileService.lockUnlockProfile(profileId, status);
   }
}