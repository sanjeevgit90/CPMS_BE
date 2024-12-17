package aurionpro.erp.ipms.utility.sms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "sms_table")
public class SMS extends JKDEntityAuditWithId {
	
	public String mobileno;

	 @Column(length = 400)
	 public String smsbody;

	public String smsstatus;

	public String attempt;

	public Long senddate;

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getSmsbody() {
		return smsbody;
	}

	public void setSmsbody(String smsbody) {
		this.smsbody = smsbody;
	}

	public String getSmsstatus() {
		return smsstatus;
	}

	public void setSmsstatus(String smsstatus) {
		this.smsstatus = smsstatus;
	}

	public String getAttempt() {
		return attempt;
	}

	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}

	public Long getSenddate() {
		return senddate;
	}

	public void setSenddate(Long senddate) {
		this.senddate = senddate;
	}

 
}