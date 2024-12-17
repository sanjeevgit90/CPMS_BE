package aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import aurionpro.erp.ipms.assetmgmt.common.DCAssetView;

@Entity
@Table(name="interdistrict_dc_view", schema = "assetmgmt")
@Immutable
public class InterDistrictDCView{

	@Id
    private Long entityId;

    private Long organizationId;
    
    private Boolean isdeleted;
  
	private String dcno;

	private Long dcdate;
	
	private String mobileno;
	
	private String contactperson;
	
	private String consigneename;
	
	private String consigneecontact;
	
	private String projectname;

    private Long projectid;
    
	private String challanattachment;
		
	private Boolean printflag;
	
	private Boolean uploadflag;
	
	private String shippedto;
	
	private String dcstatus;
	
	private Long taskid;
	
private Long noofboxes;
	
	private Long courierdate;
	
	private String courierno;
	
	private String courierDetails;
	
	@Transient
    private List<DCAssetView> asset;

	public List<DCAssetView> getAsset() {
	return asset;
}

	public void setAsset(List<DCAssetView> asset) {
	this.asset = asset;
	}

	public String getDcno() {
		return dcno;
	}

	public void setDcno(String dcno) {
		this.dcno = dcno;
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

	public Boolean getPrintflag() {
		return printflag;
	}

	public void setPrintflag(Boolean printflag) {
		this.printflag = printflag;
	}

	
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public String getDcstatus() {
		return dcstatus;
	}

	public void setDcstatus(String dcstatus) {
		this.dcstatus = dcstatus;
	}

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
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

	public String getCourierno() {
		return courierno;
	}

	public void setCourierno(String courierno) {
		this.courierno = courierno;
	}

	public String getCourierDetails() {
		return courierDetails;
	}

	public void setCourierDetails(String courierDetails) {
		this.courierDetails = courierDetails;
	}
	
}