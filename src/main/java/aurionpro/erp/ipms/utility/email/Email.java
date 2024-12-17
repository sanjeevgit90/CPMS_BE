package aurionpro.erp.ipms.utility.email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "email_table")
public class Email extends JKDEntityAuditWithId {
	
	public String emailid;

	public String emailsubject;

	 @Column(length = 1000)
	 public String emailbody;

	public String emailstatus;

	public String attempt;

	public Long senddate;

	@Column(columnDefinition = "text" )
	public String ccemailid;

	@Column(columnDefinition = "text" )
	public String bccemailid;

	@Column(columnDefinition = "text" )
	public String attachment;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getEmailsubject() {
		return emailsubject;
	}

	public void setEmailsubject(String emailsubject) {
		this.emailsubject = emailsubject;
	}

	public String getEmailbody() {
		return emailbody;
	}

	public void setEmailbody(String emailbody) {
		this.emailbody = emailbody;
	}

	public String getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(String emailstatus) {
		this.emailstatus = emailstatus;
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

	public String getCcemailid() {
		return ccemailid;
	}

	public void setCcemailid(String ccemailid) {
		this.ccemailid = ccemailid;
	}

	public String getBccemailid() {
		return bccemailid;
	}

	public void setBccemailid(String bccemailid) {
		this.bccemailid = bccemailid;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
    
}