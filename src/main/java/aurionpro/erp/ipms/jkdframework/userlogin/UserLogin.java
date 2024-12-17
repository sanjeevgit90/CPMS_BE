package aurionpro.erp.ipms.jkdframework.userlogin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userlogin", schema = "authentication")
public class UserLogin{
    
    @Id
    @Column(length = 50)
    private String username;
    @Column(length = 150, nullable = false)
    private String password;
    
    @Column(nullable = true)
    private boolean isEnabled;

    @Column(nullable = true)
    private boolean isFirstAttempt;
    @Column(nullable = true)
    private int failedAttemptCount;
    @Column(nullable = true)
    private Long lastPasswordResetDate;
    @Column(nullable = true)
    private Long accountExpiryDate;
    @Column(length = 50, nullable = true)
    private String forgotPasswordToken;

    public UserLogin() {
        this.isEnabled=true;
        this.isFirstAttempt=true;
        this.accountExpiryDate=Long.valueOf("4102338600000"); // set to 31/12/2099
        this.failedAttemptCount=0;
        this.lastPasswordResetDate=System.currentTimeMillis();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isFirstAttempt() {
        return isFirstAttempt;
    }

    public void setFirstAttempt(boolean isFirstAttempt) {
        this.isFirstAttempt = isFirstAttempt;
    }

    public int getFailedAttemptCount() {
        return failedAttemptCount;
    }

    public void setFailedAttemptCount(int failedAttemptCount) {
        this.failedAttemptCount = failedAttemptCount;
    }

    public Long getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Long lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Long getAccountExpiryDate() {
        return accountExpiryDate;
    }

    public void setAccountExpiryDate(Long accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }

    public String getForgotPasswordToken() {
        return forgotPasswordToken;
    }

    public void setForgotPasswordToken(String forgotPasswordToken) {
        this.forgotPasswordToken = forgotPasswordToken;
    }

    
}