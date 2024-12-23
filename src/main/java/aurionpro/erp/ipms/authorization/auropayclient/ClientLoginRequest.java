package aurionpro.erp.ipms.authorization.auropayclient;

public class ClientLoginRequest {
	
	private String clientname;
    private String password;

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
