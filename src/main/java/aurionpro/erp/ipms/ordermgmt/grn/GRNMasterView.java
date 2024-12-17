package aurionpro.erp.ipms.ordermgmt.grn;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetails;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetailsView;

@Immutable
@Entity
@Table(name = "grn_view", schema = "ordermgmt")
public class GRNMasterView {

	@Id
	@Column(name="entityid")
	private Long entityId;
	
	@Column
    private String createdBy;
	
	@Column
    private Long createdDate;
	
	@Column
    private String updatedBy;
    
    @Column
    private Long updatedDate;
    
    @Column
    private Boolean isDeleted;
    
    @Column
    private Long organizationId;
    
    @Column(name = "grn_number")
	private String grnNumber;
	
	@Column(name = "grn_date")
	private Long grnDate;
	
	@Column(name = "delivery_date")
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
	
	/*@Column(name = "product_id")
	private String productId;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "accepted_quantity")
	private String acceptedQuantity;
	
	@Column(name = "rejected_quantity")
	private String rejectedQuantity;*/
	
	@Column(name = "purchase_order_no")
	private String purchaseOrderNo;
	
	@Column(name = "supplier_name")
	private Long supplierName;
	
	@Column(name = "supp_name")
	private String suppName;
	
	@Column(name = "project")
	private Long project;
	
	@Transient
	private List<GRNProductDetails> prodDetails;
	
	@Transient
	private List<GRNProductDetailsView> prodList;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getGrnNumber() {
		return grnNumber;
	}

	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
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

	/*public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAcceptedQuantity() {
		return acceptedQuantity;
	}

	public void setAcceptedQuantity(String acceptedQuantity) {
		this.acceptedQuantity = acceptedQuantity;
	}

	public String getRejectedQuantity() {
		return rejectedQuantity;
	}

	public void setRejectedQuantity(String rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}*/

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Long getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(Long supplierName) {
		this.supplierName = supplierName;
	}

	public String getSuppName() {
		return suppName;
	}

	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}

	public Long getProject() {
		return project;
	}

	public void setProject(Long project) {
		this.project = project;
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
