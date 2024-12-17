package aurionpro.erp.ipms.ticketmgmt.vendor;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
    
	
	@Entity
	@Table(name = "vendor", schema = "ticketmgmt")
	public class Vendor extends JKDEntityAuditWithId {
    
		private Long project;
		
		private String vendorName ;
		
		private String emailId;
		
		private String ccEmailDl;
		
		private String dailyEmailDl;
		
	    private String contact;
		
		private String emailFormat;

		private String emailSubject;
		
		private Long accountName;
		 
		public Long getAccountName() {
			return accountName;
		}

		public void setAccountName(Long accountName) {
			this.accountName = accountName;
		}

		public Long getProject() {
			return project;
		}

		public void setProject(Long project) {
			this.project = project;
		}

		public String getVendorName() {
			return vendorName;
		}

		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
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

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String getEmailFormat() {
			return emailFormat;
		}

		public void setEmailFormat(String emailFormat) {
			this.emailFormat = emailFormat;
		}

		public String getEmailSubject() {
			return emailSubject;
		}

		public void setEmailSubject(String emailSubject) {
			this.emailSubject = emailSubject;
		}

				
		
		


}

