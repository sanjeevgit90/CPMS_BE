package aurionpro.erp.ipms.jkdframework.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.jkdframework.userlogin.UserLogin;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = -3316205945666886424L;
    
    private UserLogin myUD;
    private List<GrantedAuthority> authorities;
    private Long userProfileId;
    private String myMenus;
    private Long organizationId;
    private String firstName;
    private String lastName;

    public MyUserDetails(UserLogin ud) {
        this.myUD=ud;
        this.authorities=null;

    }

    @Autowired
    UserProfileService profileService;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        
        return myUD.getPassword();
    }

    @Override
    public String getUsername() {
        
        return myUD.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {

        if(myUD.getAccountExpiryDate()<System.currentTimeMillis()){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean isAccountNonLocked() {
        
        if(myUD.getFailedAttemptCount()>=5)
        {return false;}
        else
        {return true;}
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        if(myUD.getLastPasswordResetDate()==null){
            return true;
        }

        /* if(myUD.getLastPasswordResetDate()<System.currentTimeMillis()){
            return false;
        }
        else{
            return true;
        } */

        return true;
    }

    @Override
    public boolean isEnabled() {
       
        return myUD.isEnabled();
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getMyMenus() {
        return myMenus;
    }

    public void setMyMenus(String myMenus) {
        this.myMenus = myMenus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

}