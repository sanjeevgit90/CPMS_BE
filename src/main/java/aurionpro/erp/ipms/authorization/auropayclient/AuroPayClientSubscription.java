package aurionpro.erp.ipms.authorization.auropayclient;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="client_subscription", schema = "authentication")
public class AuroPayClientSubscription extends JKDEntityAuditWithId {
	
	private String apiName;
	
	private String secretKey;
	
	private String status;
	
	private Long registrationDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private AuroPayClient clientId;


	public String getApiName() {
		return apiName;
	}


	public void setApiName(String apiName) {
		this.apiName = apiName;
	}


	public String getSecretKey() {
		return secretKey;
	}


	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Long getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Long registrationDate) {
		this.registrationDate = registrationDate;
	}


	public AuroPayClient getClientId() {
		return clientId;
	}


	public void setClientId(AuroPayClient clientId) {
		this.clientId = clientId;
	}


	public AuroPayClientSubscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
