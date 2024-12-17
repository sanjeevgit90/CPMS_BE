package aurionpro.erp.ipms.vendor.partymaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "partymaster", schema = "ordermgmt")
public class PartyMaster extends JKDEntityAuditWithId {
	
	@Column(name = "party_type")
	private String partyType;
	
	@Column(name = "party_no", unique = true)
	private String partyNo;
	
	@Column(name = "party_name")
	private String partyName;
	
	@Column(name = "contact_person_name")
	private String contactPersonName;
	
	@Column(name = "mobile_no", length = 10)
	private String mobileNo;
	
	@Column(name = "landline_no", length = 20)
	private String landlineNo;
	
	@Email(message = "Email should be valid")
	@Column(name = "email_id", length = 100)
	private String emailId;
	
	@Column(name = "date_of_incorporation")
	private Long dateOfIncorporation;
	
	@Column(name = "date_of_incorporation_attachment")
	private String dateOfIncorporationAttachment;
	
	@Column(name = "nature_of_service_providing")
	private String natureOfServiceProviding;
	
	@Column(name = "sme_reg_no")
	private String smeRegNo;
	
	@Column(name = "pan_no", length = 20)
	private String panNo;
	
	@Column(name = "pan_no_attachment")
	private String panNoAttachment;
	
	@Column(name = "tan_no", length = 20)
	private String tanNo;
	
	@Column(name = "tan_no_attachment")
	private String tanNoAttachment;
	
	@Column(name = "arn_no", length = 20)
	private String arnNo;
	
	@Column(name = "arn_no_attachment")
	private String arnNoAttachment;
	
	@Column(name = "hsn_sac_code", length = 20)
	private String hsnSacCode;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_name_attachment")
	private String bankNameAttachment;
	
	@Column(name = "product_service_description")
	private String productServiceDescription;
	
	@Column(name = "branch_name_and_address")
	private String branchNameandAddress;
	
	@Column(name = "account_type", length = 20)
	private String accountType;
	
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "micr_code")
	private String micrCode;
	
	@Column(name = "ifsc_neft_code")
	private String ifscNeftCode;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "status", length = 20)
	private String status;
	
	@Column(name = "price_list")
	private String priceList;
	
	@Column(name = "contact_id")
	private String contactId;
	
	@Column(name = "organisation_id")
	private Long organisationId;
	
	@Transient
	private String obOrganisationId;
	
	public Long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	public String getPartyNo() {
		return partyNo;
	}

	public void setPartyNo(String partyNo) {
		this.partyNo = partyNo == null? null : partyNo.trim();
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName == null ? null : partyName.trim();
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public void setDateOfIncorporation(Long dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public String getDateOfIncorporationAttachment() {
		return dateOfIncorporationAttachment;
	}

	public void setDateOfIncorporationAttachment(String dateOfIncorporationAttachment) {
		this.dateOfIncorporationAttachment = dateOfIncorporationAttachment;
	}

	public String getNatureOfServiceProviding() {
		return natureOfServiceProviding;
	}

	public void setNatureOfServiceProviding(String natureOfServiceProviding) {
		this.natureOfServiceProviding = natureOfServiceProviding;
	}

	public String getSmeRegNo() {
		return smeRegNo;
	}

	public void setSmeRegNo(String smeRegNo) {
		this.smeRegNo = smeRegNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPanNoAttachment() {
		return panNoAttachment;
	}

	public void setPanNoAttachment(String panNoAttachment) {
		this.panNoAttachment = panNoAttachment;
	}

	public String getTanNo() {
		return tanNo;
	}

	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}

	public String getTanNoAttachment() {
		return tanNoAttachment;
	}

	public void setTanNoAttachment(String tanNoAttachment) {
		this.tanNoAttachment = tanNoAttachment;
	}

	public String getArnNo() {
		return arnNo;
	}

	public void setArnNo(String arnNo) {
		this.arnNo = arnNo;
	}

	public String getArnNoAttachment() {
		return arnNoAttachment;
	}

	public void setArnNoAttachment(String arnNoAttachment) {
		this.arnNoAttachment = arnNoAttachment;
	}

	public String getHsnSacCode() {
		return hsnSacCode;
	}

	public void setHsnSacCode(String hsnSacCode) {
		this.hsnSacCode = hsnSacCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNameAttachment() {
		return bankNameAttachment;
	}

	public void setBankNameAttachment(String bankNameAttachment) {
		this.bankNameAttachment = bankNameAttachment;
	}

	public String getProductServiceDescription() {
		return productServiceDescription;
	}

	public void setProductServiceDescription(String productServiceDescription) {
		this.productServiceDescription = productServiceDescription;
	}

	public String getBranchNameandAddress() {
		return branchNameandAddress;
	}

	public void setBranchNameandAddress(String branchNameandAddress) {
		this.branchNameandAddress = branchNameandAddress;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getIfscNeftCode() {
		return ifscNeftCode;
	}

	public void setIfscNeftCode(String ifscNeftCode) {
		this.ifscNeftCode = ifscNeftCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriceList() {
		return priceList;
	}

	public void setPriceList(String priceList) {
		this.priceList = priceList;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getObOrganisationId() {
		return obOrganisationId;
	}

	public void setObOrganisationId(String obOrganisationId) {
		this.obOrganisationId = obOrganisationId;
	}
	
	/*@OneToMany(mappedBy = "partyMasterParent", cascade = CascadeType.ALL)
    private Collection<AddressMaster> addressChildList;
	
	@OneToMany(mappedBy = "partyMasterParent", cascade = CascadeType.ALL)
    private Collection<GstMaster> gstChildList;*/
	
}
