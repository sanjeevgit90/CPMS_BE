package aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetMaster;
import aurionpro.erp.ipms.assetmgmt.common.DCAssetView;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="interdistrictdc", schema = "assetmgmt")
@JsonIgnoreProperties
public class InterDistrictDeliveryChallan extends JKDEntityAuditWithId{

	@Column(length = 50 ,nullable = false)
	private String dcno;
	
	@NotNull(message = "Delivery Challan Date is required.")
	private long dcdate;
	
	@Column(length = 15)
	private String mobileno;
	
	@Column(length = 100)
	private String contactperson;
	
	@Column(length = 100)
	private String consigneename;
	
	@Column(length = 15)
	private String consigneecontact;
	
	@NotNull(message = "Project Name is required.")
	@Column(nullable = false)
	private long projectname;
	
	@Column(length = 200)
	private String challanattachment;
		
	private Boolean printflag;
	
	private Boolean uploadflag;
	
	@Column(length = 200)
	private String shippedto;
	
	@Column(length = 20)
	private String dcStatus;
	
	@Transient
	private String remark;
	
	private Long noofboxes;
	
	private Long courierdate;
	
	@Column(length = 200)
	private String courierDetails;
	
	@Column(length = 100)
	private String courierno;  

	public String getCourierno() {
		return courierno;
	}

	public void setCourierno(String courierno) {
		this.courierno = courierno;
	}

	@ManyToMany()
    @JoinTable(
        name = "inter_dc_asset", schema = "assetmgmt",
        joinColumns = { @JoinColumn(name = "dcid") }, 
        inverseJoinColumns = { @JoinColumn(name = "assetid") })
    private List<AssetMaster> asset;
    
    @Transient
    private List<DCAssetView> assetRecord;

	public String getDcno() {
		return dcno;
	}

	public void setDcno(String dcno) {
		this.dcno = dcno;
	}

	public long getDcdate() {
		return dcdate;
	}

	public void setDcdate(long dcdate) {
		this.dcdate = dcdate;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getConsigneename() {
		return consigneename;
	}

	public void setConsigneename(String consigneename) {
		this.consigneename = consigneename;
	}

	public String getConsigneecontact() {
		return consigneecontact;
	}

	public void setConsigneecontact(String consigneecontact) {
		this.consigneecontact = consigneecontact;
	}

	public long getProjectname() {
		return projectname;
	}

	public void setProjectname(long projectname) {
		this.projectname = projectname;
	}

	public String getChallanattachment() {
		return challanattachment;
	}

	public void setChallanattachment(String challanattachment) {
		this.challanattachment = challanattachment;
	}

	
	public String getShippedto() {
		return shippedto;
	}

	public void setShippedto(String shippedto) {
		this.shippedto = shippedto;
	}


	public List<AssetMaster> getAsset() {
		return asset;
	}

	public void setAsset(List<AssetMaster> asset) {
		this.asset = asset;
	}

	public String getDcStatus() {
		return dcStatus;
	}

	public void setDcStatus(String dcStatus) {
		this.dcStatus = dcStatus;
	}

	public void setPrintflag(boolean printflag) {
		this.printflag = printflag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DCAssetView> getAssetRecord() {
		return assetRecord;
	}

	public void setAssetRecord(List<DCAssetView> assetRecord) {
		this.assetRecord = assetRecord;
	}

	public Boolean getPrintflag() {
		return printflag;
	}

	public void setPrintflag(Boolean printflag) {
		this.printflag = printflag;
	}

	public Boolean getUploadflag() {
		return uploadflag;
	}

	public void setUploadflag(Boolean uploadflag) {
		this.uploadflag = uploadflag;
	}

	public Long getNoofboxes() {
		return noofboxes;
	}

	public void setNoofboxes(Long noofboxes) {
		this.noofboxes = noofboxes;
	}

	public Long getCourierdate() {
		return courierdate;
	}

	public void setCourierdate(Long courierdate) {
		this.courierdate = courierdate;
	}

	public String getCourierDetails() {
		return courierDetails;
	}

	public void setCourierDetails(String courierDetails) {
		this.courierDetails = courierDetails;
	}

	

}