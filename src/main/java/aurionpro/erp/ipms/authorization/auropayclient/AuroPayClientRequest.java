package aurionpro.erp.ipms.authorization.auropayclient;

public class AuroPayClientRequest extends AuroPayClient {

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
