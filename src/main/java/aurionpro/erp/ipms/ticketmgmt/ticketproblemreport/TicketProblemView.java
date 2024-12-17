package aurionpro.erp.ipms.ticketmgmt.ticketproblemreport;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "ticket_problem_view" ,schema = "ticketmgmt")
@Immutable

public class TicketProblemView {
	
	@Id
	private Long entityId;
	
	private String problemReportValue;

    private Long projectId;
    
    private String projectname;

    private Boolean isDeleted;

	public String getProblemReportValue() {
		return problemReportValue;
	}

	public void setProblemReportValue(String problemReportValue) {
		this.problemReportValue = problemReportValue;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
    
    
}





