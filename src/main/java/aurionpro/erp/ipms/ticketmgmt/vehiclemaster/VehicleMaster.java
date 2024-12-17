package aurionpro.erp.ipms.ticketmgmt.vehiclemaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name = "vehiclemaster", schema = "ticketmgmt")
public class VehicleMaster extends JKDEntityAudit {
	
	@Id
	@NotEmpty(message = "Vehicle registration number is required")
	@Column(name = "vehicle_registration_number",length=30)
	private String vehicleRegNumber;
	
	@NotEmpty(message = "Vehicle Type is required")
	@Column(name = "vehicle_type",length=30)
	private String vehicleType;
	
	@NotEmpty(message = "Vehicle Name is required")
	@Column(name = "vehicle_name",length=30)
	private String vehicleName;
	
	@NotEmpty(message = "Vehicle Make is required")
	@Column(name = "vehicle_make",length=50)
	private String vehicleMake;
	
	@NotEmpty(message = "Vendor Name is required")
	@Column(name = "vendor_name",length=50)
	private String vendorName;
	
	@NotEmpty(message = "Vehicle Category number is required")
	@Column(name = "vehicle_category",length=50)
	private String vehicleCateory;

	@NotNull(message = "Vehicle purchase date is required")
	@Column(name = "vehicle_purchase_date")
	private Long vehiclepurchaseDate;

	@NotNull(message = "Vehicle delivery date is required")
	@Column(name = "delivery_date")
	private Long deliveryDate;

	@NotEmpty(message = "Project Name is required")
	@Column(name = "project_name",length=50)
	private String projectName;
	
	@NotEmpty(message = "Vehicle Insurance is required")
	@Column(name = "Vehicle_insurance",length=10)
	private String vehicleInsurance;
	
	@NotEmpty(message = "Pollution Clearance Detail is required")
	@Column(name = "pollution_clearance_done")
	private String pollutionClearanceDone;
	
	@NotEmpty(message = "Vehicle state is required")
	@Column(name = "vehicle_state",length=50)
	private String state;
	
	@NotEmpty(message = "Vehicle district date is required")
	@Column(name = "district",length=15)
	private String district;
	
	@NotEmpty(message = "Unique site id is required")
	@Column(name = "unique_site_id",length=100)
	private String usid;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "asset_category",length=30)
	private String assetCategory;

	@Column(name = "any_other_vehicle_type")
	private String anyOtherVehicleType;

	@Column(name = "insured_by")
	private String insuredBy;
	
	@Column(name = "insurance_end_date")
	private Long insuranceEndDate;
	
	@Column(name = "pollution_clearance_end_date")
	private Long pollutionClearanceEndDate;
	
	@Column(name = "registration_certificate")
	private String regCertificate;
	
	@Column(name = "insurance_certificate")
	private String insuranceCertificate;
	
	@Column(name = "pollution_certificate")
	private String pollutionCertificate;
	
	@Column(name = "any_other_document")
	private String otherDocuments;
	
	@Column(name = "loi")
	private String letterOfIntent;
	
	@Column(name = "invoice")
	private String invoice;
	
	@Column(name = "release_document")
	private String releaseDocument;

	public String getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	public void setVehicleRegNumber(String vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber == null ? null : vehicleRegNumber.trim();
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVehicleCateory() {
		return vehicleCateory;
	}

	public void setVehicleCateory(String vehicleCateory) {
		this.vehicleCateory = vehicleCateory;
	}

	public Long getVehiclepurchaseDate() {
		return vehiclepurchaseDate;
	}

	public void setVehiclepurchaseDate(Long vehiclepurchaseDate) {
		this.vehiclepurchaseDate = vehiclepurchaseDate;
	}

	public Long getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getVehicleInsurance() {
		return vehicleInsurance;
	}

	public void setVehicleInsurance(String vehicleInsurance) {
		this.vehicleInsurance = vehicleInsurance;
	}

	public String getPollutionClearanceDone() {
		return pollutionClearanceDone;
	}

	public void setPollutionClearanceDone(String pollutionClearanceDone) {
		this.pollutionClearanceDone = pollutionClearanceDone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getAnyOtherVehicleType() {
		return anyOtherVehicleType;
	}

	public void setAnyOtherVehicleType(String anyOtherVehicleType) {
		this.anyOtherVehicleType = anyOtherVehicleType;
	}

	public String getInsuredBy() {
		return insuredBy;
	}

	public void setInsuredBy(String insuredBy) {
		this.insuredBy = insuredBy;
	}

	public Long getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public void setInsuranceEndDate(Long insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}

	public Long getPollutionClearanceEndDate() {
		return pollutionClearanceEndDate;
	}

	public void setPollutionClearanceEndDate(Long pollutionClearanceEndDate) {
		this.pollutionClearanceEndDate = pollutionClearanceEndDate;
	}

	public String getRegCertificate() {
		return regCertificate;
	}

	public void setRegCertificate(String regCertificate) {
		this.regCertificate = regCertificate;
	}

	public String getInsuranceCertificate() {
		return insuranceCertificate;
	}

	public void setInsuranceCertificate(String insuranceCertificate) {
		this.insuranceCertificate = insuranceCertificate;
	}

	public String getPollutionCertificate() {
		return pollutionCertificate;
	}

	public void setPollutionCertificate(String pollutionCertificate) {
		this.pollutionCertificate = pollutionCertificate;
	}

	public String getOtherDocuments() {
		return otherDocuments;
	}

	public void setOtherDocuments(String otherDocuments) {
		this.otherDocuments = otherDocuments;
	}

	public String getLetterOfIntent() {
		return letterOfIntent;
	}

	public void setLetterOfIntent(String letterOfIntent) {
		this.letterOfIntent = letterOfIntent;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getReleaseDocument() {
		return releaseDocument;
	}

	public void setReleaseDocument(String releaseDocument) {
		this.releaseDocument = releaseDocument;
	}

	
	

}