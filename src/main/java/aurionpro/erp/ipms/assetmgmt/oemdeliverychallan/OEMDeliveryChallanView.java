package aurionpro.erp.ipms.assetmgmt.oemdeliverychallan;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import aurionpro.erp.ipms.assetmgmt.common.DCAssetView;

@Entity
@Table(name="oem_dc_view", schema = "assetmgmt")
@Immutable
public class OEMDeliveryChallanView{

	@Id
    private Long entityId;

    private Long organizationId;
    
    private Boolean isdeleted;
  
	private String dcno;

	private Long dcdate;
	
	private String consigneename;
	
	private String consigneecontact;
	
	private String projectname;

    private Long projectid;
    
	private String challanattachment;
		
	private Boolean printflag;
	
	private String shippedto;
	
	private Long taskid;
	
	private String dcstatus;
	
	private Boolean uploadflag;
	
private Long noofboxes;
	
	private Long courierdate;
	
	private String courierno;
	
	private String courierDetails;
	
private String partyname;
	
	private String address;

	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	private Long consignor;
	
	private Long fulladdress;
		
	
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

	public Long getConsignor() {
		return consignor;
	}

	public void setConsignor(Long consignor) {
		this.consignor = consignor;
	}

	public Long getFulladdress() {
		return fulladdress;
	}

	public void setFulladdress(Long fulladdress) {
		this.fulladdress = fulladdress;
	}

@Transient
    private List<DCAssetView> asset;

	public String getDcno() {
		return dcno;
	}

	public void setDcno(String dcno) {
		this.dcno = dcno;
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

	
	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public List<DCAssetView> getAsset() {
		return asset;
	}

	public void setAsset(List<DCAssetView> dcAssetList) {
		this.asset = dcAssetList;
	}

	public String getDcstatus() {
		return dcstatus;
	}

	public void setDcstatus(String dcstatus) {
		this.dcstatus = dcstatus;
	}

	public Boolean getUploadflag() {
		return uploadflag;
	}

	public void setUploadflag(Boolean uploadflag) {
		this.uploadflag = uploadflag;
	}
	
	
}