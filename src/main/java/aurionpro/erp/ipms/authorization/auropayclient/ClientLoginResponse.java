package aurionpro.erp.ipms.authorization.auropayclient;

public class ClientLoginResponse {

	 private String token;

	    public ClientLoginResponse(String token) {
	        this.token = token;
	    }

	   
	    public String getToken() {
	        return token;
	    }
}

