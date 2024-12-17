package aurionpro.erp.ipms.billingmgmt.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "invoicetask", schema = "billingmgmt")
public class InvoiceTaskMaster extends TaskMaster {

	private Long invoiceid;
	
	@Column(length = 200)
	private String remark;
    
    public InvoiceTaskMaster() {

    }

	public InvoiceTaskMaster(InvoiceRequest invoice) {
		this.setApprovalStatus(invoice.getApprovalStatus());
		this.setAssignToRole(invoice.getAssignToRole());
		this.setAssignToUser(invoice.getAssignToUser());
		this.setEntityId(invoice.getEntityId());
		this.setInvoiceid(invoice.getInvoiceid());
		this.setRemark(invoice.getRemark());
		this.setStageName(invoice.getStageName());
		this.setWorkflowName(invoice.getWorkflowName());
	}

		public Long getInvoiceid() {
		return invoiceid;
	}
		
		public void setInvoiceid(Long invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
    protected Object clone() throws CloneNotSupportedException {
        InvoiceTaskMaster tm=(InvoiceTaskMaster)super.clone();
        tm.setRemark(null);
        //tm.setProbableUse(null);

        return tm;
    }

    
}