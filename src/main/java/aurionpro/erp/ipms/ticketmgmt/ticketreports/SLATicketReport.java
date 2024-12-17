package aurionpro.erp.ipms.ticketmgmt.ticketreports;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_sla_report" ,schema = "ticketmgmt")
public class SLATicketReport {
	@Id
	private Long entityId;
	
	private Boolean isdeleted;
	
	private Long ticketClosedTime;
	
	private String category;
	
	private String classifications;
	
	private Long dueDate;
	
	private String email;
	
	private String channel;
	
	private String phone;
	
	private String priority;
	
	private String resolution;
	
	private String ticketStatus;
	
	private String subCategory;
	
	private String ticketTitle;
	
	private String toAddress;
	
	private Long accountName;
	
	private String description;
	
	private String assetName;

	private String contactName;
	
	private Long ticketOwner;
	
	private String ticketType;
	
	private String createdBy;
	
	private Long createdDate;
	
	private String updatedBy;
	
	private Long updatedDate;
	
	private Long resolutionDate;
	
	private Long assignTo;
	
	private Long assetId;
	
	private String ticketOwnerName;
	
	private String ticketOwnerEmail;
	
	private String ticketNo;
	
	private String assignName;
	
	private String assignmail;
	
	private String projectName;
	
	private Boolean flag;
	
	private String district;
	
	private String location;
	
	private String attachment;
	
	private String vehiclesUsed;
	
	private String assetTag;
	
	private String otherproblemdescription;
	
	private String department;
	
	private String ticketCategory;
	
	private String ticketSubcategory;
	
	private String productName;
	
	private String productModel;
	
	private String serialNo;
	
	private String vendorName ;
	
	private String vendor ;
	
	private String vendoremail;
	
	private String ccEmailDl;
	
	private String dailyEmailDl;
	
	private String emailFormat;
	
	private Long escalatedOn;
	
	 private String escalatedTo;
	 
	 private String state;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getTicketClosedTime() {
		return ticketClosedTime;
	}

	public void setTicketClosedTime(Long ticketClosedTime) {
		this.ticketClosedTime = ticketClosedTime;
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

	public Long getDueDate() {
		return dueDate;
	}

	public void setDueDate(Long dueDate) {
		this.dueDate = dueDate;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
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

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(Long resolutionDate) {
		this.resolutionDate = resolutionDate;
	}


	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getTicketOwnerName() {
		return ticketOwnerName;
	}

	public void setTicketOwnerName(String ticketOwnerName) {
		this.ticketOwnerName = ticketOwnerName;
	}

	public String getTicketOwnerEmail() {
		return ticketOwnerEmail;
	}

	public void setTicketOwnerEmail(String ticketOwnerEmail) {
		this.ticketOwnerEmail = ticketOwnerEmail;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getAssignName() {
		return assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	public String getAssignmail() {
		return assignmail;
	}

	public void setAssignmail(String assignmail) {
		this.assignmail = assignmail;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	

	public Long getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(Long assignTo) {
		this.assignTo = assignTo;
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getVehiclesUsed() {
		return vehiclesUsed;
	}

	public void setVehiclesUsed(String vehiclesUsed) {
		this.vehiclesUsed = vehiclesUsed;
	}

	public String getAssetTag() {
		return assetTag;
	}

	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}

	public String getOtherproblemdescription() {
		return otherproblemdescription;
	}

	public void setOtherproblemdescription(String otherproblemdescription) {
		this.otherproblemdescription = otherproblemdescription;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendoremail() {
		return vendoremail;
	}

	public void setVendoremail(String vendoremail) {
		this.vendoremail = vendoremail;
	}

	public String getCcEmailDl() {
		return ccEmailDl;
	}

	public void setCcEmailDl(String ccEmailDl) {
		this.ccEmailDl = ccEmailDl;
	}

	public String getDailyEmailDl() {
		return dailyEmailDl;
	}

	public void setDailyEmailDl(String dailyEmailDl) {
		this.dailyEmailDl = dailyEmailDl;
	}

	public String getEmailFormat() {
		return emailFormat;
	}

	public void setEmailFormat(String emailFormat) {
		this.emailFormat = emailFormat;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	 
	 

}




