package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;


	@Entity
	@Table(name = "tickettask" ,schema = "ticketmgmt")
	public class TicketTaskHistory extends TaskMaster{
		
		    @NotBlank(message = "Ticket No cannot be Blank")
		    @Column(nullable = false, updatable = false)
		    private String ticketNo;
		    
		    @Column(nullable = false, updatable = false)
		    private Long ticketId;
		    
		    private Long assignTo;
           
		    @Column(length=50)
		    private String latitude;
        	
        	@Column(length=50)
        	private String longitude;
        	
        	private Long escalatedOn;
        	        		
        	@Column(length=200)
        	private String escalatedTo;
                	
           private String vendorname;
           
           @Column(length=200)
       	   private String captureImg;
			
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



			public Long getAssignTo() {
			return assignTo;
		}



		public void setAssignTo(Long assignTo) {
			this.assignTo = assignTo;
		}



			@Override
		    protected Object clone() throws CloneNotSupportedException {
		        TicketTaskHistory tm=(TicketTaskHistory)super.clone();
		        tm.setRemark(null);
		        tm.setLatitude(null);
		        tm.setEscalatedOn(null);
		        tm.setEscalatedTo(null);
		        tm.setLongitude(null);
		        //tm.setProbableUse(null);
		        tm.setVendorname(null);
		        tm.setCaptureImg(null);
		        tm.setAssignTo(null);
		        return tm;
		    }

		
}
