package aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "interdistrict_dc_taskmaster", schema = "assetmgmt")
public class InterDistrictDCTaskMaster extends TaskMaster {

	@Column(length = 50 ,nullable = false)
    private String dcno;
    
    public InterDistrictDCTaskMaster() {

    }

   
    public String getDcno() {
		return dcno;
	}


	public void setDcno(String dcno) {
		this.dcno = dcno;
	}


		@Override
    protected Object clone() throws CloneNotSupportedException {
        InterDistrictDCTaskMaster tm=(InterDistrictDCTaskMaster)super.clone();
        tm.setRemark(null);
        //tm.setProbableUse(null);

        return tm;
    }

    
}