package aurionpro.erp.ipms.jkdframework.userlogin;

import aurionpro.erp.ipms.authorization.userprofile.UserProfile;

public class UserProfileRequest extends UserProfile {

    private String password;
    private long accountExpiryDate;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAccountExpiryDate() {
        return accountExpiryDate;
    }

    public void setAccountExpiryDate(long accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }
    
}