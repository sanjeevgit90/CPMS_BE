package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name = "ticket_task_view" ,schema = "ticketmgmt")

public class TicketTaskView{
	
    @NotBlank(message = "Ticket No cannot be Blank")
    @Column(nullable = false, updatable = false)
    private String ticketNo;
    
    @Id
    private Long entityId;

   	private Long escalatedOn;
		
	private String escalatedTo;
		
	private String approvalStatus ;
	
    private String assetName;
	
	private String assignToUser;
	
	private String assignToRole;
	
	private String ticketOwnerName;
		
	private String ticketTitle;
	
	private String priority;
		
	private String ticketType;
	    
	private String latitude;
	
	private String longitude;	
	
	private Long accountname;
	
	private String vendorname;
	
	private Long ticketId;
	
	private String assignname;

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getEscalatedOn() {
		return escalatedOn;
	}

	public void setEscalatedOn(Long escalatedOn) {
		this.escalatedOn = escalatedOn;
	}

	public String getEscalatedTo() {
		return escalatedTo;
	}

	public void setEscalatedTo(String escalatedTo) {
		this.escalatedTo = escalatedTo;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssignToUser() {
		return assignToUser;
	}

	public void setAssignToUser(String assignToUser) {
		this.assignToUser = assignToUser;
	}

	public String getAssignToRole() {
		return assignToRole;
	}

	public void setAssignToRole(String assignToRole) {
		this.assignToRole = assignToRole;
	}

	public String getTicketOwnerName() {
		return ticketOwnerName;
	}

	public void setTicketOwnerName(String ticketOwnerName) {
		this.ticketOwnerName = ticketOwnerName;
	}

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	
	public Long getAccountname() {
		return accountname;
	}

	public void setAccountname(Long accountname) {
		this.accountname = accountname;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAssignname() {
		return assignname;
	}

	public void setAssignname(String assignname) {
		this.assignname = assignname;
	}

	
}
