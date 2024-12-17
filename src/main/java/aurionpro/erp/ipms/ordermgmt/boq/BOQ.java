package aurionpro.erp.ipms.ordermgmt.boq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "boq", schema = "ordermgmt")
public class BOQ extends JKDEntityAuditWithId {

	@NotNull(message = "BOQ no. is required.")
	@Column(name = "boq_no", unique = true, nullable = false, length = 50)
	private String boqNo;

	@Min(value = 1, message = "Account is required.")
	@Column(name = "account_id", unique = true, nullable = false)
	private Long accountId;

	@Min(value = 1, message = "BOQ date is required.")
	@Column(name = "boq_date", nullable = false)
	private Long boqDate;

	@Column(name = "boq_approval_status", length = 50)
	private String boqApprovalStatus;

	public String getBoqNo() {
		return boqNo;
	}

	public void setBoqNo(String boqNo) {
		this.boqNo = boqNo == null? null : boqNo.trim();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getBoqDate() {
		return boqDate;
	}

	public void setBoqDate(Long boqDate) {
		this.boqDate = boqDate;
	}

	public String getBoqApprovalStatus() {
		return boqApprovalStatus;
	}

	public void setBoqApprovalStatus(String boqApprovalStatus) {
		this.boqApprovalStatus = boqApprovalStatus;
	}
}