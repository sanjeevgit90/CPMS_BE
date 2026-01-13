package aurionpro.erp.ipms.ordermgmt.prs.dto;

public class PrsPaymentUpdateDto {
	 private Long entityId;
	    private String bookEntryNo;
	    private Long paymentDoneDate;
	    private String paymentBookEntryNo;
		public Long getEntityId() {
			return entityId;
		}
		public void setEntityId(Long entityId) {
			this.entityId = entityId;
		}
		public String getBookEntryNo() {
			return bookEntryNo;
		}
		public void setBookEntryNo(String bookEntryNo) {
			this.bookEntryNo = bookEntryNo;
		}
		public Long getPaymentDoneDate() {
			return paymentDoneDate;
		}
		public void setPaymentDoneDate(Long paymentDoneDate) {
			this.paymentDoneDate = paymentDoneDate;
		}
		public String getPaymentBookEntryNo() {
			return paymentBookEntryNo;
		}
		public void setPaymentBookEntryNo(String paymentBookEntryNo) {
			this.paymentBookEntryNo = paymentBookEntryNo;
		}
	    
}
