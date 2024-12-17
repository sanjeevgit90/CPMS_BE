package aurionpro.erp.ipms.jkdframework.userlogin;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.jkdframework.authentication.ResetPassword;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.StringUtil;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginRepository loginRepo;

    @Autowired
    PasswordEncoder passEncoder;

    public void IncrementFailedLoginAttempt(String username){
        loginRepo.IncreamentFailedAttemptCounter(username);
    }

    public void ResetFailedLoginAttempt(String username){
        loginRepo.ResetFailedAttemptCounter(username);
    }

    public String GenerateForgotPasswordToken(String username){

        String token=StringUtil.randomPasswordString(6);
        loginRepo.SetForgotPasswordToken(username, token);

        return token;
    }

    public Optional<UserLogin> GetUserByName(String username){
        return loginRepo.findById(username);
    }

    public boolean ResetUserPassword(ResetPassword resetRequest){
        Optional<UserLogin> uLogin=GetUserByName(resetRequest.getUsername());

        if(uLogin.isPresent()){

            if(uLogin.get().getForgotPasswordToken().equalsIgnoreCase(resetRequest.getToken())){
                uLogin.get().setPassword(passEncoder.encode(resetRequest.getNewPassword()));
                uLogin.get().setForgotPasswordToken("");
                loginRepo.save(uLogin.get());
            }
            else
            {
                throw new EntityNotFoundException("Invalid Token");
            }
            return true;
        }
        else{
            throw new EntityNotFoundException("Invalid Username");
        }
    }

    public boolean ChangeUserPassword(ChangePassword changeRequest){
        Optional<UserLogin> uLogin=GetUserByName(MyPrincipal.getMyProfile().getUsername());

        if(uLogin.isPresent()){
            
            if(passEncoder.matches(changeRequest.getOldPassword(),uLogin.get().getPassword())){
                uLogin.get().setPassword(passEncoder.encode(changeRequest.getNewPassword()));
                loginRepo.save(uLogin.get());
            }
            else
            {
                throw new EntityNotFoundException("Invalid old Password");
            }
            return true;
        }
        else{
            throw new EntityNotFoundException("Invalid Username");
        }
    }
    
    public boolean unlockUserAccount(String userName, String token) {
    	Optional<UserLogin> uLogin=GetUserByName(userName);

        if(uLogin.isPresent()){

            if(uLogin.get().getForgotPasswordToken().equalsIgnoreCase(token)){
               loginRepo.ResetFailedAttemptCounter(userName);
            }
            else
            {
                throw new EntityNotFoundException("Invalid Token");
            }
            return true;
        }
        else{
            throw new EntityNotFoundException("Invalid Username");
        }
    }

	
}