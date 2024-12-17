package aurionpro.erp.ipms.ticketmgmt.ticketclassification;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "ticket_classifications_view" ,schema = "ticketmgmt")
@Immutable

public class TicketClassificationView {
	
	@Id
	private Long entityId;
	
	private String classificationValue;

    private Long projectId;
    
    private String projectname;
    
    private Boolean isDeleted;

	public String getClassificationValue() {
		return classificationValue;
	}

	public void setClassificationValue(String classificationValue) {
		this.classificationValue = classificationValue;
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





