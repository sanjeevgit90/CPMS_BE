package aurionpro.erp.ipms.ordermgmt.purchaseordertask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name="potaskmaster", schema = "ordermgmt")
public class PoTaskMaster extends TaskMaster {

	@NotNull
	@Column(name = "po_id", nullable = false)
	private Long poId;
	
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
		PoTaskMaster tm = (PoTaskMaster)super.clone();
        tm.setPoRcFlag(tm.getPoRcFlag());
        tm.setUploadFile(null);
        return tm;
    }

	public Long getPoId() {
		return poId;
	}

	public void setPoId(Long poId) {
		this.poId = poId;
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
