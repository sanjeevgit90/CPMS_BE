package aurionpro.erp.ipms.ticketmgmt.ticketclassification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
    
	
	@Entity
	@Table(name = "ticketclassification", schema = "ticketmgmt")
	public class TicketClassification extends JKDEntityAuditWithId {
    
	@Column(length =100)
	private String classificationValue;

   	@Column(name = "projectId")
	private Long projectId;
	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getClassificationValue() {
		return classificationValue;
	}

	public void setClassificationValue(String classificationValue) {
		this.classificationValue = classificationValue == null ? null : classificationValue.trim();
	}

}
