package aurionpro.erp.ipms.jkdframework.userlogin;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.jkdframework.common.DashboardCountList;
import aurionpro.erp.ipms.utility.StringUtil;
import aurionpro.erp.ipms.utility.email.SendingMailService;

@RestController()
@RequestMapping(value = "/ipms/user")
@Transactional()
public class UserLoginController{

    @Autowired
    private UserLoginRepository loginRepo;

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserLoginService loginService;

    @GetMapping()
    public Iterable<UserLogin> getAllUsers(){
        return loginRepo.findAll();
    }

    @Autowired
    PasswordEncoder passEncoder;

    @Autowired
    SendingMailService emailService;

    @PostMapping()
    public UserLogin creatUserLogin(@RequestBody UserProfileRequest ud){

        Optional<UserLogin> ulTemp=loginRepo.findById(ud.getUserName());

        if (ulTemp.isPresent()){
            throw new EntityExistsException("User already exists");
        }

        String randomPassword=StringUtil.randomPasswordString(8); //generate random Password

        UserLogin ulCreate=new UserLogin();
        ulCreate.setUsername(ud.getUserName());
        String pass=passEncoder.encode("admin#123");
        ulCreate.setPassword(pass);
        ulCreate.setFirstAttempt(true);
        if(ud.getAccountExpiryDate()==0){
            ulCreate.setAccountExpiryDate(Long.valueOf("4102338600000")); // set to 31/12/2099
        }
        loginRepo.save(ulCreate);

        UserProfile uProfile=new UserProfile(ud);
        profileService.createUserProfile(uProfile);

        String msgBody="Dear User, <br><br> Thankyou for registring with us. <br>Your Password: " + randomPassword + "<br><br> Regards,<br>IPMS Team";
        emailService.sendEmail(uProfile.getUserName(),"Registration Successful", msgBody, "");

        return ulCreate;
    }
    
    @DeleteMapping()
    public UserLogin deleteUserLogin(@RequestBody UserLogin ud) throws Exception{

        Optional<UserLogin> uTemp=loginRepo.findById(ud.getUsername());

        if (!uTemp.isPresent()){
            throw new EntityNotFoundException("User does not exists");
        }

        uTemp.get().setEnabled(false);
       
        this.loginRepo.save(uTemp.get()); 

       return uTemp.get();
    }

    @PostMapping("/changepassword")
    public ResponseEntity<?> ChangeUserPassword(@RequestBody ChangePassword changeRequest){

        loginService.ChangeUserPassword(changeRequest);
       // return ResponseEntity.ok("Password has been Changed");
        return null;
    }
    
    @GetMapping("/getDashboardCount")
    public List<DashboardCountList> getDashboardCount(){
    	return loginRepo.getDashboardCount();
    }
}