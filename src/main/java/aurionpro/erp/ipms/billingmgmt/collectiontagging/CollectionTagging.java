package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="collection_tagging", schema = "billingmgmt")
public class CollectionTagging extends JKDEntityAuditWithId{
	
	private Long collectiondate;
	
	private Long projectid;
	
	private Long invoiceid;
	

	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double tdsdeducted;
	
	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double gsttdsdeducted;
	
	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double otherdeducted;
	
	private String deductiondescription;
	
	@Column(precision = 15, scale=2, columnDefinition = "numeric(15,2)")
	private Double netamountcredieted;
	
	@Column(length = 20) 
	private String utrno;
	
	@Column(length = 200)  
	private String uploadpaymentadvice;
	
	@Column(length = 50)  
	private String collectionstatus;
     
    public CollectionTagging() {
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

	public Double getTdsdeducted() {
		return tdsdeducted;
	}

	public void setTdsdeducted(Double tdsdeducted) {
		this.tdsdeducted = tdsdeducted;
	}

	public Double getGsttdsdeducted() {
		return gsttdsdeducted;
	}

	public void setGsttdsdeducted(Double gsttdsdeducted) {
		this.gsttdsdeducted = gsttdsdeducted;
	}

	public Double getOtherdeducted() {
		return otherdeducted;
	}

	public void setOtherdeducted(Double otherdeducted) {
		this.otherdeducted = otherdeducted;
	}

	public String getDeductiondescription() {
		return deductiondescription;
	}

	public void setDeductiondescription(String deductiondescription) {
		this.deductiondescription = deductiondescription;
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

	public String getUploadpaymentadvice() {
		return uploadpaymentadvice;
	}

	public void setUploadpaymentadvice(String uploadpaymentadvice) {
		this.uploadpaymentadvice = uploadpaymentadvice;
	}

	public String getCollectionstatus() {
		return collectionstatus;
	}

	public void setCollectionstatus(String collectionstatus) {
		this.collectionstatus = collectionstatus;
	}
    
   
}