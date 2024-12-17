package aurionpro.erp.ipms.ticketmgmt.ticketreports;

import aurionpro.erp.ipms.ticketmgmt.ticketmaster.TicketMaster;

public class TicketReportRequest extends TicketMaster{
	   	    
       		private Long fromDate;
       	    
       		private Long toDate;

			public Long getFromDate() {
				return fromDate;
			}

			public void setFromDate(Long fromDate) {
				this.fromDate = fromDate;
			}

			public Long getToDate() {
				return toDate;
			}

			public void setToDate(Long toDate) {
				this.toDate = toDate;
			}       	    
			
}
