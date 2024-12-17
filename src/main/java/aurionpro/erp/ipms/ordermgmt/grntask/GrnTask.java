package aurionpro.erp.ipms.ordermgmt.grntask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "grn_task", schema = "ordermgmt")
public class GrnTask extends TaskMaster {

	@NotNull
	@Column(name = "grn_id", nullable = false)
	private Long grnId;
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
		GrnTask tm = (GrnTask)super.clone();
        return tm;
    }

	public Long getGrnId() {
		return grnId;
	}

	public void setGrnId(Long grnId) {
		this.grnId = grnId;
	}
}
