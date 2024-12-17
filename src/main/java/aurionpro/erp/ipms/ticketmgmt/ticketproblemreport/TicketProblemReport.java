package aurionpro.erp.ipms.ticketmgmt.ticketproblemreport;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;


@Entity
@Table(name = "ticketproblemreport", schema = "ticketmgmt")
public class TicketProblemReport extends JKDEntityAuditWithId {
    
	@Column(length =100)
	private String problemReportValue;
	
	private Long projectId;

	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProblemReportValue() {
		return problemReportValue;
	}

	public void setProblemReportValue(String problemReportValue) {
		this.problemReportValue = problemReportValue == null ? null : problemReportValue.trim();
	}
	
}



