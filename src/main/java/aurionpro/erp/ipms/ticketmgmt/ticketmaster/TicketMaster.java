package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "ticketmaster", schema = "ticketmgmt",uniqueConstraints = {@UniqueConstraint(columnNames = {"ticketNo"})}
)
public class TicketMaster extends JKDEntityAuditWithId {
	
	@Column(length=100)
	private String ticketNo;
	
	//@NotEmpty(message="State is required")
	@Column(length=100)
	private String state;

	@NotEmpty(message="District is required")
	@Column(length=100)
	private String district;
	
	@NotEmpty(message="location is required")
	@Column(length=100)
	private String location;
	
	@Column(length=100)
	private String category;
	
	@Column(length=200)
	private String classifications;
	
	@Email(message ="Email Should be Valid")
	private String email;
	
	@Column(length=50 )
	private String channel;
	
	private String phone;
		
    @NotEmpty(message="Ticket Title is required")
	//@Column(length=50)
	private String ticketTitle;
	
	@NotNull(message="Project Name is required")
	private Long accountName;
	
	@Column(length=250)
	private String description;
	
	@Column(length=200)
	private String otherProblemDescription;
	
	@NotNull(message="Asset name is required")
	private Long assetid;

	//@NotNull(message="Ticket Owner Name is required")
	private Long ticketOwner;
	
	@NotEmpty(message="Ticket Type is required")
	@Column(length=50)
	private String ticketType;
	
	@NotEmpty(message="Contact name is required")
	@Column(length=50)
	private String contactName;
	
	@Column(name = "resolutionDate")
	private Long resolutionDate;
	
	@Column(length=500)
	private String resolution;
	
	@Column(length=50)
	private String subCategory;
	
	@Column(length=50)
	private String vehiclesUsed;
		
	@Column(length=50)
	private String department;
	
	@Column(length=50)
	private String ticketCategory;
	
	@Column(length=50)
	private String ticketSubcategory;
	
	@Column(name = "flag")
	private Boolean flag;
		 
	private Long dueDate;
	
	private Long ticketClosedTime;
	
	private String attachment;

	private String ticketStatus;
	
	@Column(length=250)
	private String toAddress;
	
	@Column(length=50)
	private String priority;
	
	private Long assignTo;

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClassifications() {
		return classifications;
	}

	public void setClassifications(String classifications) {
		this.classifications = classifications;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public Long getAccountName() {
		return accountName;
	}

	public void setAccountName(Long accountName) {
		this.accountName = accountName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOtherProblemDescription() {
		return otherProblemDescription;
	}

	public void setOtherProblemDescription(String otherProblemDescription) {
		this.otherProblemDescription = otherProblemDescription;
	}

	public Long getAssetid() {
		return assetid;
	}

	public void setAssetid(Long assetid) {
		this.assetid = assetid;
	}

	public Long getTicketOwner() {
		return ticketOwner;
	}

	public void setTicketOwner(Long ticketOwner) {
		this.ticketOwner = ticketOwner;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Long getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(Long resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getVehiclesUsed() {
		return vehiclesUsed;
	}

	public void setVehiclesUsed(String vehiclesUsed) {
		this.vehiclesUsed = vehiclesUsed;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTicketCategory() {
		return ticketCategory;
	}

	public void setTicketCategory(String ticketCategory) {
		this.ticketCategory = ticketCategory;
	}

	public String getTicketSubcategory() {
		return ticketSubcategory;
	}

	public void setTicketSubcategory(String ticketSubcategory) {
		this.ticketSubcategory = ticketSubcategory;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Long getDueDate() {
		return dueDate;
	}

	public void setDueDate(Long dueDate) {
		this.dueDate = dueDate;
	}

	public Long getTicketClosedTime() {
		return ticketClosedTime;
	}

	public void setTicketClosedTime(Long ticketClosedTime) {
		this.ticketClosedTime = ticketClosedTime;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Long getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(Long assignTo) {
		this.assignTo = assignTo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	 @Transient
	    private String assetName;

		public String getAssetName() {
			return assetName;
		}

		public void setAssetName(String assetName) {
			this.assetName = assetName;
		}    
	    
}

