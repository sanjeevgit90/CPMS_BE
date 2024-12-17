package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class TicketTaskRequest{
	   	    
       		private String ticketCategory;
       	    
       	    private String classifications;
       	 
       	    private Long resolutionDate;

    	    private String resolution;
    	
    	    private String ticketSubcategory;
    	    
    		private Long ticketClosedTime;
    		
    		private String trip;
    		
    		private String ticketNo;
 		    
    		private Long ticketId;
            
         	private String latitude;
         	
         	private String longitude;
         	
         	private String vendorname;
            
         	private String captureImg;
         	
         	 private Long entityId;
         	 
         	 private String approvalStatus;
         	 
         	 
         	 private String remark;
            
			public String getTicketCategory() {
				return ticketCategory;
			}

			public void setTicketCategory(String ticketCategory) {
				this.ticketCategory = ticketCategory;
			}

			public String getClassifications() {
				return classifications;
			}

			public void setClassifications(String classifications) {
				this.classifications = classifications;
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

			public String getTicketSubcategory() {
				return ticketSubcategory;
			}

			public void setTicketSubcategory(String ticketSubcategory) {
				this.ticketSubcategory = ticketSubcategory;
			}

			public Long getTicketClosedTime() {
				return ticketClosedTime;
			}

			public void setTicketClosedTime(Long ticketClosedTime) {
				this.ticketClosedTime = ticketClosedTime;
			}

			public String getTrip() {
				return trip;
			}

			public void setTrip(String trip) {
				this.trip = trip;
			}

			public String getTicketNo() {
				return ticketNo;
			}

			public void setTicketNo(String ticketNo) {
				this.ticketNo = ticketNo;
			}

			public Long getTicketId() {
				return ticketId;
			}

			public void setTicketId(Long ticketId) {
				this.ticketId = ticketId;
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

			public String getVendorname() {
				return vendorname;
			}

			public void setVendorname(String vendorname) {
				this.vendorname = vendorname;
			}

			public String getCaptureImg() {
				return captureImg;
			}

			public void setCaptureImg(String captureImg) {
				this.captureImg = captureImg;
			}

			public Long getEntityId() {
				return entityId;
			}

			public void setEntityId(Long entityId) {
				this.entityId = entityId;
			}

			public String getApprovalStatus() {
				return approvalStatus;
			}

			public void setApprovalStatus(String approvalStatus) {
				this.approvalStatus = approvalStatus;
			}

			public String getRemark() {
				return remark;
			}

			public void setRemark(String remark) {
				this.remark = remark;
			}
			
			
}
