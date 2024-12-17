package aurionpro.erp.ipms.healthapp.selfassessment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="self_assessment", schema = "healthmgmt")
@TypeDef(
	    name = "jsonb",
	    typeClass = JsonBinaryType.class
	)
	
public class SelfAssessment extends JKDEntityAuditWithId {

	@NotNull(message="User Id is requried")
   private long userid;
	
	@NotNull(message="Assessment Date is requried")
   private long assessmentdate;
    
   @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String answersubmitted;
    
    @Column(length = 25,nullable = false)
    private String healthstatus;

    public SelfAssessment() {
    }

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getAssessmentdate() {
		return assessmentdate;
	}

	public void setAssessmentdate(long assessmentdate) {
		this.assessmentdate = assessmentdate;
	}

	public String getAnswersubmitted() {
		return answersubmitted;
	}

	public void setAnswersubmitted(String answersubmitted) {
		this.answersubmitted = answersubmitted;
	}

	public String getHealthstatus() {
		return healthstatus;
	}

	public void setHealthstatus(String healthstatus) {
		this.healthstatus = healthstatus;
	}
    
}