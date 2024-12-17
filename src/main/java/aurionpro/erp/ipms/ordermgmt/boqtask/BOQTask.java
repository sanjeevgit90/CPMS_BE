package aurionpro.erp.ipms.ordermgmt.boqtask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "boq_task", schema = "ordermgmt")
public class BOQTask extends TaskMaster {

	@NotNull
	@Column(name = "boq_id", nullable = false, length = 50)
	private Long boqId;
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
		BOQTask tm = (BOQTask)super.clone();
        return tm;
    }

	public Long getBoqId() {
		return boqId;
	}

	public void setBoqId(Long boqId) {
		this.boqId = boqId;
	}
}
