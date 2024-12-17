package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="collectionview", schema = "billingmgmt")
@Immutable
public class CollectionView {
	
	@Id
	private Long entityid;
	
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
	
	private String utrno;
	
	 private String uploadpaymentadvice;
	
	 private String collectionstatus;
	 
	 private String projectname;
	 
	 private String projectpin;
		
	private String invoiceno;
     
    public CollectionView() {
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

	public Long getEntityid() {
		return entityid;
	}

	public void setEntityid(Long entityid) {
		this.entityid = entityid;
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

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectpin() {
		return projectpin;
	}

	public void setProjectpin(String projectpin) {
		this.projectpin = projectpin;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}
    
}