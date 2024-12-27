package aurionpro.erp.ipms.authorization.auropayclient;

public class ClientLoginResponse {

	private String token;
	private String name;
	public ClientLoginResponse(String token2, String username) {
	this.token= token2;
	this.name= username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

