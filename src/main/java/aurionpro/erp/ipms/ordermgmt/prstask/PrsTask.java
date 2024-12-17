package aurionpro.erp.ipms.ordermgmt.prstask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name="prstask", schema = "ordermgmt")
public class PrsTask extends TaskMaster {

	@NotNull
	@Column(name = "prs_id", nullable = false)
	private Long prsId;
	
	@Column(name = "upload_file")
	private String uploadFile;
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
		PrsTask tm = (PrsTask)super.clone();
        tm.setUploadFile(null);
        return tm;
    }

	public Long getPrsId() {
		return prsId;
	}

	public void setPrsId(Long prsId) {
		this.prsId = prsId;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
}
