package aurionpro.erp.ipms.jkdframework.authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.jkdframework.userlogin.UserLoginService;
import aurionpro.erp.ipms.utility.AppProperties;
import aurionpro.erp.ipms.utility.email.SendingMailService;


@RestController
public class AuthenticationController{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserLoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    AppProperties appProperties;

    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException ex){
            loginService.IncrementFailedLoginAttempt(authenticationRequest.getUsername());
            throw new BadCredentialsException("Incorrect username or password");
        }
        catch(LockedException ex){
        	sendUnlockAccountMail(authenticationRequest.getUsername());
            throw new BadCredentialsException("User Account is Locked, this can be due to multiple wrong login attempts. Link is send to your email address use it to unlock your account.");
        }
        catch(Exception ex){
            throw new BadCredentialsException(ex.getLocalizedMessage());
        }
        
        //Reset Login Failed attempts
        loginService.ResetFailedLoginAttempt(authenticationRequest.getUsername());

        //Set JWT token and generate response
        final MyUserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final UserProfile uProfile=profileService.getUserProfileByName(userDetails.getUsername());
        final String jwt=jwtUtil.generateToken(userDetails,uProfile,Long.valueOf(1));

        AuthenticationResponse authResp=new AuthenticationResponse(jwt,uProfile.getFirstName(),uProfile.getLastName(),uProfile.getAuthorities(), uProfile.getEntityId());
        authResp.setUserdistrict(uProfile.getMappeddistrict());
        return ResponseEntity.ok(authResp);

    }

    private void sendUnlockAccountMail(String username) {
    	 String token=loginService.GenerateForgotPasswordToken(username);
         String msgBody=	appProperties.getUnlockMsg;
 		
      //  String url = appProperties.getUnlockUrl+"?userName="+ username+"&token="+token;
        String url = appProperties.getUrl+"/"+ username+"/"+token;
 		
 		 msgBody = msgBody.replace("UNLOCKURL", url);
  		
         emailService.sendEmail(username, "Unlock IPMS Account", msgBody, "");
	}

	@Autowired
    SendingMailService emailService;

    @RequestMapping(value="/forgotpassword/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> forgotPasswordLinkGeneration(@PathVariable(value = "username") String username){
        
        String token=loginService.GenerateForgotPasswordToken(username);
        String msgBody="Dear User, <br><br> Your token to reset Password: " + token + "<br><br> Regards,<br>IPMS Team";

        emailService.sendEmail(username, "Reset Password Token", msgBody, "");
       // return ResponseEntity.ok("Passsword Reset Token has been send on your Email Id");
    return null; 
    }

    @RequestMapping(value="/resetpassword", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassword resetRequest){
        
        loginService.ResetUserPassword(resetRequest);

        String msgBody="Dear User, <br><br> Your Password has been changed Successfully <br><br>Note: If it is not you please report immediately <br><br> Regards,<br>IPMS Team";
        emailService.sendEmail(resetRequest.getUsername(), "Password changed", msgBody, "");
        
      //  return ResponseEntity.ok("Password has been reset successfully");
        return null; 
    
    }
    
    @RequestMapping(value="/unlockUser/{userName}/{token}", method = RequestMethod.GET)
    public ResponseEntity<?> resetPassword(@PathVariable(name = "userName") String userName, @PathVariable(name = "token") String token ){
        
        loginService.unlockUserAccount(userName, token);

        String msgBody="Dear User, <br><br> Your account has been unlocked Successfully. You can try login in your account <br><br>Note: If it is not you please report immediately <br><br> Regards,<br>IPMS Team";
        emailService.sendEmail(userName, "Account is unlocked", msgBody, "");
        
      //  return ResponseEntity.ok("Password has been reset successfully");
        return null; 
    
    }
}