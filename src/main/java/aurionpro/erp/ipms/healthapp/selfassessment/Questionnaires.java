package aurionpro.erp.ipms.healthapp.selfassessment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="questionnaires", schema = "healthmgmt")
@TypeDef(
	    name = "jsonb",
	    typeClass = JsonBinaryType.class
	)
public class Questionnaires extends JKDEntityAuditWithId {

    @Column(length = 25,nullable = false)
    private String questionbankname ;
    
   @Type(type = "jsonb")
   @Column(columnDefinition = "jsonb")
    private String baseQuestions;
    
    public Questionnaires() {
    }

	public String getQuestionbankname() {
		return questionbankname;
	}

	public void setQuestionbankname(String questionbankname) {
		this.questionbankname = questionbankname;
	}

	public String getBaseQuestions() {
		return baseQuestions;
	}

	public void setBaseQuestions(String baseQuestions) {
		this.baseQuestions = baseQuestions;
	}
    
    
}