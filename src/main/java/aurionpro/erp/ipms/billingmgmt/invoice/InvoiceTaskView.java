package aurionpro.erp.ipms.billingmgmt.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="invoice_task_view", schema = "billingmgmt")
@Immutable
public class InvoiceTaskView{

	@Id
    private Long taskid;

	 private Long customer;
	 
	 private Long projectid;
	 
	 private String customername; 
	
	 private String milestoneno;
	 
	 private String projectname;
	 
	 private String approvalstatus;
	 
	 private String workflowname;

	 @Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	 private Double totalamount;
	 
	 private String invoicestatus;
	 
	 private String assigntorole;
	 
	 private String assigntouser;
 
	 private String invoiceexcel;
	 
	 private Long invoiceid;
	 
	 private String updatedby;

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getMilestoneno() {
		return milestoneno;
	}

	public void setMilestoneno(String milestoneno) {
		this.milestoneno = milestoneno;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
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



	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus = invoicestatus;
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

	public String getInvoiceexcel() {
		return invoiceexcel;
	}

	public void setInvoiceexcel(String invoiceexcel) {
		this.invoiceexcel = invoiceexcel;
	}

	public Long getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(Long invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
		
}