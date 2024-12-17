package aurionpro.erp.ipms.assetmgmt.manufacturemaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="manufacturemaster", schema = "assetmgmt")
public class ManufacturerMaster extends JKDEntityAudit{

	@Id
	@NotEmpty(message = "Manufacturer name is required")
	@Column(length = 100)
	//@Pattern(regexp="/^[a-z][a-z\\s]*$/",message="Geography name should have alphabets with space only")  
	private String manufacturername;
	
     public ManufacturerMaster() {
    }

	public String getManufacturername() {
		return manufacturername;
	}

	public void setManufacturername(String manufacturername) {
		this.manufacturername = manufacturername==null?null:manufacturername.trim();
	}

}