package aurionpro.erp.ipms.common.geographymaster;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="geographicdata")
public class GeographyMaster extends JKDEntityAudit{

	@EmbeddedId
    private GeographyIdentity id;
	
	@Column(length = 10, nullable = false)
	@NotNull(message= "Geography Code may not be empty")
	@Range(min = 1)
    private Integer geographycode;
     
    public GeographyMaster() {
    }
    
    public GeographyMaster(GeographyIdentity geoId, Integer geoCode) {
    	this.id = geoId;
    	this.geographycode = geoCode;
    }
    
   
	
	public Integer getGeographycode() {
		return geographycode;
	}

	public void setGeographycode(Integer geographycode) {
		this.geographycode = geographycode;
	}

	public GeographyIdentity getId() {
		return id;
	}

	public void setId(GeographyIdentity id) {
		this.id = id;
	}
	
	

}