package aurionpro.erp.ipms.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import aurionpro.erp.ipms.jkdframework.authentication.MyUserDetails;
import aurionpro.erp.ipms.jkdframework.userlogin.UserLogin;

public class MyPrincipal {

   public static MyUserDetails getMyProfile(){
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals("anonymousUser")){
            UserLogin ul=new UserLogin();
            ul.setUsername("anonymousUser");
            return new MyUserDetails(ul);
        }
        else{
            MyUserDetails ud= (MyUserDetails) auth.getPrincipal();
            return ud;
        }        

    }
    
}