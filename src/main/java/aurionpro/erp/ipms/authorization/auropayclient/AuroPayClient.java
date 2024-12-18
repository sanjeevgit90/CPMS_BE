package aurionpro.erp.ipms.authorization.auropayclient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="integration_client", schema = "authentication")
@JsonIgnoreProperties("authorities")
public class AuroPayClient extends JKDEntityAuditWithId {
	
	@Column(length = 50,nullable = false)
	private String clientName;
	
	@Column(length = 50,nullable = false)
	private String emailId;
	
	@Column(length = 500)
	private String address;
	
	private String clientCode;
	
	@Column(length = 25)
	private String country;
	
	@Column(length = 25)
	private String state;
	
	private Integer  status;
	
	private Long activationDate;
	
	private String publicKey;
	
	private String token;


	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Long activationDate) {
		this.activationDate = activationDate;
	}
	
	 public AuroPayClient() {
	    }

	public AuroPayClient(AuroPayClient auroClient) {
		this.setEntityId(auroClient.getEntityId());
		this.clientName = auroClient.clientName;
		this.clientCode = auroClient.clientCode;
		this.address = auroClient.address;
		this.emailId = auroClient.emailId;
		this.activationDate = auroClient.activationDate;
		this.state = auroClient.state;
		this.status = auroClient.status;
		this.country = auroClient.country;
		this.publicKey = auroClient.publicKey;
		this.token = auroClient.token;

		
		
	}
	


	

	
	
	
	
	

}
