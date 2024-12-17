package aurionpro.erp.ipms.billingmgmt.invoice;

public class InvoiceRequest extends InvoiceTaskMaster {

	private String invoiceno;
	
	private Long invoicedate;
	
	private String invoiceexcel;
	
	private String invoicesignedexcel;

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public Long getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(Long invoicedate) {
		this.invoicedate = invoicedate;
	}

	public String getInvoiceexcel() {
		return invoiceexcel;
	}

	public void setInvoiceexcel(String invoiceexcel) {
		this.invoiceexcel = invoiceexcel;
	}

	public String getInvoicesignedexcel() {
		return invoicesignedexcel;
	}

	public void setInvoicesignedexcel(String invoicesignedexcel) {
		this.invoicesignedexcel = invoicesignedexcel;
	}
	
	
}