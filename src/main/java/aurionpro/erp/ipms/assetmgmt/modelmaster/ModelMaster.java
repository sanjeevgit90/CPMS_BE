package aurionpro.erp.ipms.assetmgmt.modelmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.assetmgmt.manufacturemaster.ManufacturerMaster;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="modelmaster", schema = "assetmgmt")
public class ModelMaster extends JKDEntityAudit{

	@Id
	@NotEmpty(message = "Model name is required")
	@Column(length = 100)
    private String modelname;
   
	@Column(length = 300)
    private String description;
    
    @ManyToOne()
    @NotNull(message = "manufacturer name is required")
    @JoinColumn(name = "manufacturename")
    private ManufacturerMaster manufacture;
      
     public ModelMaster() {
    }

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname==null?null:modelname.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ManufacturerMaster getManufacture() {
		return manufacture;
	}

	public void setManufacture(ManufacturerMaster manufacture) {
		this.manufacture = manufacture;
	}

	
	
}