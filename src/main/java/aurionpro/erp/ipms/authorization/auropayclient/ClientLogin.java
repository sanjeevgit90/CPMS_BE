package aurionpro.erp.ipms.authorization.auropayclient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientlogin", schema = "authentication")
public class ClientLogin {
	
	  @Id
	    @Column(length = 50)
	    private String clientname;
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

	    public ClientLogin() {
	        this.isEnabled=true;
	        this.isFirstAttempt=true;
	        this.accountExpiryDate=Long.valueOf("4102338600000"); // set to 31/12/2099
	        this.failedAttemptCount=0;
	        this.lastPasswordResetDate=System.currentTimeMillis();
	    }

	    public String getClientname() {
	        return clientname;
	    }

	    public void setClientname(String clientname) {
	        this.clientname = clientname == null? null : clientname.trim();
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
