package aurionpro.erp.ipms.ordermgmt.grn;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetails;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetailsView;

@Entity
@Table(name = "grn_master", schema = "ordermgmt")
public class GRNMaster extends JKDEntityAuditWithId {

	@NotNull(message = "GRN no. is required.")
	@Column(name = "grn_number", nullable = false, unique = true)
	private String grnNumber;
	
	@NotNull(message = "GRN date is required.")
	@Column(name = "grn_date", nullable = false)
	private Long grnDate;
	
	@Column(name = "delivery_date", nullable = false)
	private Long deliveryDate;
	
	@Column(name = "delivery_challan_no")
	private String deliveryChallanNo;
	
	@Column(name = "delivery_challan_date")
	private Long deliveryChallanDate;
	
	@Column(name = "invoice_no")
	private String invoiceNo;
	
	@Column(name = "invoice_date")
	private Long invoiceDate;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "vehicle_no")
	private String vehicleNo;
	
	@Column(name = "transporter_name")
	private String transporterName;

	@Column(name = "dc_copy_upload")
	private String dcCopyUpload;
	
	@Column(name = "lr_copy_upload")
	private String lrCopyUpload;
	
	@Column(name = "po_no")
	private Long poNo;
	
	@Column(name = "approval_status")
	private String approvalStatus;
	
	@Transient
	private List<GRNProductDetails> prodDetails;
	
	@Transient
	private List<GRNProductDetailsView> prodList;

	public String getGrnNumber() {
		return grnNumber;
	}

	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber == null? null: grnNumber.trim();
	}

	public Long getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(Long grnDate) {
		this.grnDate = grnDate;
	}

	public Long getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryChallanNo() {
		return deliveryChallanNo;
	}

	public void setDeliveryChallanNo(String deliveryChallanNo) {
		this.deliveryChallanNo = deliveryChallanNo;
	}

	public Long getDeliveryChallanDate() {
		return deliveryChallanDate;
	}

	public void setDeliveryChallanDate(Long deliveryChallanDate) {
		this.deliveryChallanDate = deliveryChallanDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Long getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Long invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getTransporterName() {
		return transporterName;
	}

	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}

	public String getDcCopyUpload() {
		return dcCopyUpload;
	}

	public void setDcCopyUpload(String dcCopyUpload) {
		this.dcCopyUpload = dcCopyUpload;
	}

	public String getLrCopyUpload() {
		return lrCopyUpload;
	}

	public void setLrCopyUpload(String lrCopyUpload) {
		this.lrCopyUpload = lrCopyUpload;
	}

	public Long getPoNo() {
		return poNo;
	}

	public void setPoNo(Long poNo) {
		this.poNo = poNo;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public List<GRNProductDetails> getProdDetails() {
		return prodDetails;
	}

	public void setProdDetails(List<GRNProductDetails> prodDetails) {
		this.prodDetails = prodDetails;
	}

	public List<GRNProductDetailsView> getProdList() {
		return prodList;
	}

	public void setProdList(List<GRNProductDetailsView> prodList) {
		this.prodList = prodList;
	}
}