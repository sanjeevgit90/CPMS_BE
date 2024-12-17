package aurionpro.erp.ipms.ticketmgmt.servicemaster;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name = "vehicleservicemaster", schema = "ticketmgmt")
public class VehicleServiceMaster extends JKDEntityAudit { 
        
	@Id
	@NotEmpty(message="Vehicle Registration No is required")
	@Column(name = "vehicle_registration_no", unique = true, nullable = false)
	private String vehicleRegistrationNumber;
	
	@NotNull(message="Service Period From required")
	@Column(name = "service_period_from")
	private long servicePeriodFrom;
	
	@NotNull(message="Service Period To is required")
	@Column(name = "service_period_to")
	private long servicePeriodTo;
	
	@Column(name = "service_invoice_no")
	private String serviceInvoiceNumber;
	
	@Column(name = "service_cost_incurred")
	private float serviceCostIncurred;
	
	@NotEmpty(message="Service Centre Name is required")
	@Column(name = "service_centre_name")
	private String serviceCentreName;
	
	@NotNull(message="Kilometer Reading is required")
	@Column(name = "kilometer_reading")
	private float kilometerReading;
	
	@Column(name = "service_status")
	private String serviceStatus;
	
	@Column(name = "next_service_due_date")
	private long nextServiceDueDate;
	
	@Column(name = "is_a_free_service")
	private String isAFreeService;
	
	
	@Column(name = "uploaded_attachment")
	private String uploadedAttachment;
	
	@Column(name = "remark")
	private String remark;

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public long getServicePeriodFrom() {
		return servicePeriodFrom;
	}

	public void setServicePeriodFrom(long servicePeriodFrom) {
		this.servicePeriodFrom = servicePeriodFrom;
	}

	public long getServicePeriodTo() {
		return servicePeriodTo;
	}

	public void setServicePeriodTo(long servicePeriodTo) {
		this.servicePeriodTo = servicePeriodTo;
	}

	public String getServiceInvoiceNumber() {
		return serviceInvoiceNumber;
	}

	public void setServiceInvoiceNumber(String serviceInvoiceNumber) {
		this.serviceInvoiceNumber = serviceInvoiceNumber;
	}

	public float getServiceCostIncurred() {
		return serviceCostIncurred;
	}

	public void setServiceCostIncurred(float serviceCostIncurred) {
		this.serviceCostIncurred = serviceCostIncurred;
	}

	public String getServiceCentreName() {
		return serviceCentreName;
	}

	public void setServiceCentreName(String serviceCentreName) {
		this.serviceCentreName = serviceCentreName;
	}

	public float getKilometerReading() {
		return kilometerReading;
	}

	public void setKilometerReading(float kilometerReading) {
		this.kilometerReading = kilometerReading;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public long getNextServiceDueDate() {
		return nextServiceDueDate;
	}

	public void setNextServiceDueDate(long nextServiceDueDate) {
		this.nextServiceDueDate = nextServiceDueDate;
	}

	public String getIsAFreeService() {
		return isAFreeService;
	}

	public void setIsAFreeService(String isAFreeService) {
		this.isAFreeService = isAFreeService;
	}

	public String getUploadedAttachment() {
		return uploadedAttachment;
	}

	public void setUploadedAttachment(String uploadedAttachment) {
		this.uploadedAttachment = uploadedAttachment;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	         
}

