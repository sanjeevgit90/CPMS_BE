package aurionpro.erp.ipms.common.policestationmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="policestationmaster")
public class PoliceStationMaster extends JKDEntityAudit{

	@Id
	@NotEmpty(message = "Police Station name is required")
	@Column(length = 100)
	private String policestationname;
	
	@Column(length = 10, nullable = false)
	@NotNull(message= "Police Station Code may not be empty")
	@Range(min = 1)
    private Integer policestationcode;
    
	@NotEmpty(message = "City is required")
	@Column(length = 100)
    private String city;
      
     public PoliceStationMaster() {
    }

	public Integer getPolicestationcode() {
		return policestationcode;
	}

	public void setPolicestationcode(Integer policestationcode) {
		this.policestationcode = policestationcode;
	}

	public String getPolicestationname() {
		return policestationname;
	}

	public void setPolicestationname(String policestationname) {
		this.policestationname = policestationname == null? null: policestationname.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}