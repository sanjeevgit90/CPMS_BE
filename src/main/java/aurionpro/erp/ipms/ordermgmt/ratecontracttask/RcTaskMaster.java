package aurionpro.erp.ipms.ordermgmt.ratecontracttask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name="rctaskmaster", schema = "ordermgmt")
public class RcTaskMaster extends TaskMaster {

	@NotNull
	@Column(name = "rc_id", nullable = false)
	private Long rcId;
	
	@Column(name = "upload_file")
	private String uploadFile;

	@Column(name = "po_rc_flag")
	private String poRcFlag;
	
	@Transient
	private String workflowType;
	
	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	@Override
    protected Object clone() throws CloneNotSupportedException {
		RcTaskMaster tm = (RcTaskMaster)super.clone();
        tm.setPoRcFlag("RC");
        tm.setUploadFile(null);
        return tm;
    }

	public Long getRcId() {
		return rcId;
	}

	public void setRcId(Long rcId) {
		this.rcId = rcId;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getPoRcFlag() {
		return poRcFlag;
	}

	public void setPoRcFlag(String poRcFlag) {
		this.poRcFlag = poRcFlag;
	}
}
