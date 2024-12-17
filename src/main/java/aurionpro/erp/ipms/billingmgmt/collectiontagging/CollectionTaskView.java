package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="collection_task_view", schema = "billingmgmt")
@Immutable
public class CollectionTaskView{

	@Id
    private Long taskid;
	
	private Long collectionid;

	private Long collectiondate;
		
	private Long projectid;
		
	private Long invoiceid;

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double netamountcredieted;
		
	private String utrno;
		
	private String collectionstatus;
	 
	private String projectname;
	
	private String invoiceno;
	
	private String approvalstatus;
	 
	 private String workflowname;
	 
	 private String updatedby;
	 
	 private String assigntorole;
		 
	 private String assigntouser;
	 
	 private String uploadpaymentadvice;

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public Long getCollectiondate() {
		return collectiondate;
	}

	public void setCollectiondate(Long collectiondate) {
		this.collectiondate = collectiondate;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public Long getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(Long invoiceid) {
		this.invoiceid = invoiceid;
	}

	public Double getNetamountcredieted() {
		return netamountcredieted;
	}

	public void setNetamountcredieted(Double netamountcredieted) {
		this.netamountcredieted = netamountcredieted;
	}

	public String getUtrno() {
		return utrno;
	}

	public void setUtrno(String utrno) {
		this.utrno = utrno;
	}

	public String getCollectionstatus() {
		return collectionstatus;
	}

	public void setCollectionstatus(String collectionstatus) {
		this.collectionstatus = collectionstatus;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}

	public String getWorkflowname() {
		return workflowname;
	}

	public void setWorkflowname(String workflowname) {
		this.workflowname = workflowname;
	}

	public Long getCollectionid() {
		return collectionid;
	}

	public void setCollectionid(Long collectionid) {
		this.collectionid = collectionid;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getAssigntorole() {
		return assigntorole;
	}

	public void setAssigntorole(String assigntorole) {
		this.assigntorole = assigntorole;
	}

	public String getAssigntouser() {
		return assigntouser;
	}

	public void setAssigntouser(String assigntouser) {
		this.assigntouser = assigntouser;
	}

	public String getUploadpaymentadvice() {
		return uploadpaymentadvice;
	}

	public void setUploadpaymentadvice(String uploadpaymentadvice) {
		this.uploadpaymentadvice = uploadpaymentadvice;
	} 
	
}