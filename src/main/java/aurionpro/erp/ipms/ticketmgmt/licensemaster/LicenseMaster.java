package aurionpro.erp.ipms.ticketmgmt.licensemaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "licensemaster", schema = "ticketmgmt")
public class LicenseMaster extends JKDEntityAudit { 
        
	    @Id
	    @NotEmpty(message="License Tag is required")
		@Column(name = "license_tag")
		private String licenseTag;
		
	    @NotEmpty(message="Software Name Tag is required")
		@Column(name = "software_name")
		private String softwareName;
		
	    @NotEmpty(message="Serial No Tag is required")
		@Column(name = "serial")
		private String serialNo;
		
	    @NotEmpty(message="License To Name is required")
		@Column(name = "license_to_name")
		private String licenseToName;
		
	    @NotEmpty(message="License To Mail is required")
		@Column(name = "license_to_email")
		private String licenseToEmail;
		
	    @NotEmpty(message="Seat is required")
		@Column(name = "seats")
		private String seats;
		
	    @NotEmpty(message="Order No is required")
		@Column(name = "order_no")
		private String orderNo;
		
	    @Column(name = "category")
		private String category;
		
	    @Column(name = "sub_category")
		private String subCategory;
	    
	    @NotNull(message="Purchase Date is required")
        @Column(name = "purchase_date")
		private long purchaseDate;
		
	    @NotNull(message="Purchase Cost is required")
		@Column(name = "purchase_cost")
		private float purchaseCost;
		
	    @NotEmpty(message="Depreciation is required")
		@Column(name = "depreciation")
		private String depreciation;
		
	    @NotNull(message="Warranty is required")
		@Column(name = "warranty")
		private long warranty;
		
	    @Column(name = "eol")
		private String eol;
		
	    @NotEmpty(message="Notes is required")
		@Column(name = "notes")
		private String notes;
	
		@Column(name = "uploaded_attachment")
		private String uploadedAttachment;



		public String getLicenseTag() {
			return licenseTag;
		}

		public void setLicenseTag(String licenseTag) {
			this.licenseTag = licenseTag;
		}

		public String getSoftwareName() {
			return softwareName;
		}

		public void setSoftwareName(String softwareName) {
			this.softwareName = softwareName;
		}

		public String getSerialNo() {
			return serialNo;
		}

		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
		}

		public String getLicenseToName() {
			return licenseToName;
		}

		public void setLicenseToName(String licenseToName) {
			this.licenseToName = licenseToName;
		}

		public String getLicenseToEmail() {
			return licenseToEmail;
		}

		public void setLicenseToEmail(String licenseToEmail) {
			this.licenseToEmail = licenseToEmail;
		}

		public String getSeats() {
			return seats;
		}

		public void setSeats(String seats) {
			this.seats = seats;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getSubCategory() {
			return subCategory;
		}

		public void setSubCategory(String subCategory) {
			this.subCategory = subCategory;
		}

		public long getPurchaseDate() {
			return purchaseDate;
		}

		public void setPurchaseDate(long purchaseDate) {
			this.purchaseDate = purchaseDate;
		}

		public float getPurchaseCost() {
			return purchaseCost;
		}

		public void setPurchaseCost(float purchaseCost) {
			this.purchaseCost = purchaseCost;
		}

		public String getDepreciation() {
			return depreciation;
		}

		public void setDepreciation(String depreciation) {
			this.depreciation = depreciation;
		}

		public long getWarranty() {
			return warranty;
		}

		public void setWarranty(long warranty) {
			this.warranty = warranty;
		}

		public String getEol() {
			return eol;
		}

		public void setEol(String eol) {
			this.eol = eol;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public String getUploadedAttachment() {
			return uploadedAttachment;
		}

		public void setUploadedAttachment(String uploadedAttachment) {
			this.uploadedAttachment = uploadedAttachment;
		}
		
		
		
         
}

