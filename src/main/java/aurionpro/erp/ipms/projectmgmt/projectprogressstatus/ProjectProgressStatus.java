package aurionpro.erp.ipms.projectmgmt.projectprogressstatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="progress_status", schema = "projectmgmt")
public class ProjectProgressStatus {

	@Id
	@Column(length = 20)
	private String progressname;
	
	@Column(length = 20)
	private String progresscolor;
	

    public ProjectProgressStatus() {
    }


	public String getProgressname() {
		return progressname;
	}


	public void setProgressname(String progressname) {
		this.progressname = progressname == null? null: progressname;
	}


	public String getProgresscolor() {
		return progresscolor;
	}


	public void setProgresscolor(String progresscolor) {
		this.progresscolor = progresscolor;
	}
}