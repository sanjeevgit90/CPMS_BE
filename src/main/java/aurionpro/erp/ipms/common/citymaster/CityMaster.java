package aurionpro.erp.ipms.common.citymaster;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="citymaster")
public class CityMaster extends JKDEntityAudit{

	@EmbeddedId
    private CityIdentity id;
	
	@Column(length = 10, nullable = false)
	@NotNull(message= "City Code may not be empty")
	@Range(min = 1)
    private Integer citycode;
          
     public CityMaster() {
    }
     
     public CityMaster(CityIdentity cityId, Integer cityCode) {
     	this.id = cityId;
     	this.citycode = cityCode;
     }

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public CityIdentity getId() {
		return id;
	}

	public void setId(CityIdentity id) {
		this.id = id;
	}
	
	
}