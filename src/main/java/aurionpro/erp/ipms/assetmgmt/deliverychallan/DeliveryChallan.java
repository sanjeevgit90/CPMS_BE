package aurionpro.erp.ipms.assetmgmt.deliverychallan;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetMaster;
import aurionpro.erp.ipms.assetmgmt.common.DCAssetView;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="deliverchallan", schema = "assetmgmt")
public class DeliveryChallan extends JKDEntityAuditWithId{

	@NotNull(message = "Consignor is required.")
	@Column(length = 100, nullable = false)
	private Long consignor;
	
	@Column(length = 50 ,nullable = false)
	private String dcno;
	
	@NotNull(message = "Delivery Challan Date is required.")
	private Long dcdate;
	
	@Column(length = 50)
	private String gstno;
	
	@NotNull(message = "Address is required.")
	@Column(length = 200 ,nullable = false)
	private Long fulladdress;
	
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
	private Long projectname;
	
	@Column(length = 200)
	private String challanattachment;
		
	private Boolean printflag;
	
	private Boolean uploadflag;
	
	@Column(length = 200)
	private String shippedto;
	
	private Long noofboxes;
	
	private Long courierdate;
	
	@Column(length = 200)
	private String courierDetails;
	
	@Column(length = 100)
	private String courierno;
	
	
    @OneToMany()
    @JoinTable(
        name = "delivery_challan_asset", schema = "assetmgmt",
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

	public String getGstno() {
		return gstno;
	}

	public void setGstno(String gstno) {
		this.gstno = gstno;
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

	public Long getDcdate() {
		return dcdate;
	}

	public void setDcdate(Long dcdate) {
		this.dcdate = dcdate;
	}

	public Long getProjectname() {
		return projectname;
	}

	public void setProjectname(Long projectname) {
		this.projectname = projectname;
	}

	public Boolean getPrintflag() {
		return printflag;
	}

	public void setPrintflag(Boolean printflag) {
		this.printflag = printflag;
	}

	public Long getFulladdress() {
		return fulladdress;
	}

	public void setFulladdress(Long fulladdress) {
		this.fulladdress = fulladdress;
	}

	public Long getConsignor() {
		return consignor;
	}

	public void setConsignor(Long consignor) {
		this.consignor = consignor;
	}

	public List<AssetMaster> getAsset() {
		return asset;
	}

	public void setAsset(List<AssetMaster> asset) {
		this.asset = asset;
	}

	public List<DCAssetView> getAssetRecord() {
		return assetRecord;
	}

	public void setAssetRecord(List<DCAssetView> assetRecord) {
		this.assetRecord = assetRecord;
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

	public String getCourierno() {
		return courierno;
	}

	public void setCourierno(String courierno) {
		this.courierno = courierno;
	}
	

}