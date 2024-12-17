package aurionpro.erp.ipms.assetmgmt.assetconstantmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="assetconstantmaster", schema = "assetmgmt")
public class AssetConstantMaster extends JKDEntityAuditWithId{

	@NotEmpty(message = "Constant name is required")
	@Column(length = 100)
	private String constantname;
	
	private Integer term;
	
	@Column(length = 50)  
	private String constantnamefor;
	
     
     public AssetConstantMaster() {
    }


	public String getConstantname() {
		return constantname;
	}


	public void setConstantname(String constantname) {
		this.constantname = constantname==null?null:constantname.trim();
	}


	public Integer getTerm() {
		return term;
	}


	public void setTerm(Integer term) {
		this.term = term;
	}


	public String getConstantnamefor() {
		return constantnamefor;
	}


	public void setConstantnamefor(String constantnamefor) {
		this.constantnamefor = constantnamefor;
	}

	
}