package aurionpro.erp.ipms.assetmgmt.oemdeliverychallan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "oem_dc_taskmaster", schema = "assetmgmt")
public class OEMDCTaskMaster extends TaskMaster {

	@Column(length = 50 ,nullable = false)
    private String dcno;
    
    public OEMDCTaskMaster() {

    }

   
    public String getDcno() {
		return dcno;
	}


	public void setDcno(String dcno) {
		this.dcno = dcno;
	}


	@Override
    protected Object clone() throws CloneNotSupportedException {
        OEMDCTaskMaster tm=(OEMDCTaskMaster)super.clone();
        tm.setRemark(null);
        //tm.setProbableUse(null);

        return tm;
    }

    
}