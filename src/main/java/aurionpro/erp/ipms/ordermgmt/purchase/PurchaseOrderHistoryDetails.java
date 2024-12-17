package aurionpro.erp.ipms.ordermgmt.purchase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "purchase_order_history_details", schema = "ordermgmt")
public class PurchaseOrderHistoryDetails extends JKDEntityAuditWithId {

	/*@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36, unique = true)
	private String id;*/

	/*@Column(name = "purchase_order_id", length = 36, unique = true)
	private String purchaseOrderId;*/
	
	@OneToOne()
    @JoinColumn(name="purchase_order_id", unique = true)
	private PurchaseOrderMaster purchaseOrderMaster;
	
	@Column(name = "invoicetoname")
	private String invoicetoname;

	@Column(name = "invoicetoaddressforhistory")
	private String invoicetoaddressforhistory;

	@Column(name = "invoicetocontact")
	private String invoicetocontact;

	@Column(name = "invoicetophone")
	private String invoicetophone;

	@Column(name = "invoicetoemail")
	private String invoicetoemail;

	@Column(name = "suppliername")
	private String suppliernameforhistory;

	@Column(name = "supplieraddress", columnDefinition = "text")
	private String supplieraddress;

	@Column(name = "suppliercontact")
	private String suppliercontact;

	@Column(name = "supplierphone")
	private String supplierphone;

	@Column(name = "supplieremail")
	private String supplieremail;

	@Column(name = "billtoname")
	private String billtoname;

	@Column(name = "billtoaddressforhistory", columnDefinition = "text")
	private String billtoaddressforhistory;

	@Column(name = "billtocontact")
	private String billtocontact;

	@Column(name = "billtophone")
	private String billtophone;

	@Column(name = "billtoemail")
	private String billtoemail;

	@Column(name = "billtogstin")
	private String billtogstin;

	@Column(name = "shiptoname")
	private String shiptoname;

	@Column(name = "shiptoaddressforhistory", columnDefinition = "text")
	private String shiptoaddressforhistory;

	@Column(name = "shiptocontact")
	private String shiptocontact;

	@Column(name = "shiptophone")
	private String shiptophone;

	@Column(name = "shiptoemail")
	private String shiptoemail;

	public PurchaseOrderMaster getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(PurchaseOrderMaster purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

	public String getInvoicetoname() {
		return invoicetoname;
	}

	public void setInvoicetoname(String invoicetoname) {
		this.invoicetoname = invoicetoname;
	}

	public String getInvoicetoaddressforhistory() {
		return invoicetoaddressforhistory;
	}

	public void setInvoicetoaddressforhistory(String invoicetoaddressforhistory) {
		this.invoicetoaddressforhistory = invoicetoaddressforhistory;
	}

	public String getInvoicetocontact() {
		return invoicetocontact;
	}

	public void setInvoicetocontact(String invoicetocontact) {
		this.invoicetocontact = invoicetocontact;
	}

	public String getInvoicetophone() {
		return invoicetophone;
	}

	public void setInvoicetophone(String invoicetophone) {
		this.invoicetophone = invoicetophone;
	}

	public String getInvoicetoemail() {
		return invoicetoemail;
	}

	public void setInvoicetoemail(String invoicetoemail) {
		this.invoicetoemail = invoicetoemail;
	}

	public String getSuppliernameforhistory() {
		return suppliernameforhistory;
	}

	public void setSuppliernameforhistory(String suppliernameforhistory) {
		this.suppliernameforhistory = suppliernameforhistory;
	}

	public String getSupplieraddress() {
		return supplieraddress;
	}

	public void setSupplieraddress(String supplieraddress) {
		this.supplieraddress = supplieraddress;
	}

	public String getSuppliercontact() {
		return suppliercontact;
	}

	public void setSuppliercontact(String suppliercontact) {
		this.suppliercontact = suppliercontact;
	}

	public String getSupplierphone() {
		return supplierphone;
	}

	public void setSupplierphone(String supplierphone) {
		this.supplierphone = supplierphone;
	}

	public String getSupplieremail() {
		return supplieremail;
	}

	public void setSupplieremail(String supplieremail) {
		this.supplieremail = supplieremail;
	}

	public String getBilltoname() {
		return billtoname;
	}

	public void setBilltoname(String billtoname) {
		this.billtoname = billtoname;
	}

	public String getBilltoaddressforhistory() {
		return billtoaddressforhistory;
	}

	public void setBilltoaddressforhistory(String billtoaddressforhistory) {
		this.billtoaddressforhistory = billtoaddressforhistory;
	}

	public String getBilltocontact() {
		return billtocontact;
	}

	public void setBilltocontact(String billtocontact) {
		this.billtocontact = billtocontact;
	}

	public String getBilltophone() {
		return billtophone;
	}

	public void setBilltophone(String billtophone) {
		this.billtophone = billtophone;
	}

	public String getBilltoemail() {
		return billtoemail;
	}

	public void setBilltoemail(String billtoemail) {
		this.billtoemail = billtoemail;
	}

	public String getBilltogstin() {
		return billtogstin;
	}

	public void setBilltogstin(String billtogstin) {
		this.billtogstin = billtogstin;
	}

	public String getShiptoname() {
		return shiptoname;
	}

	public void setShiptoname(String shiptoname) {
		this.shiptoname = shiptoname;
	}

	public String getShiptoaddressforhistory() {
		return shiptoaddressforhistory;
	}

	public void setShiptoaddressforhistory(String shiptoaddressforhistory) {
		this.shiptoaddressforhistory = shiptoaddressforhistory;
	}

	public String getShiptocontact() {
		return shiptocontact;
	}

	public void setShiptocontact(String shiptocontact) {
		this.shiptocontact = shiptocontact;
	}

	public String getShiptophone() {
		return shiptophone;
	}

	public void setShiptophone(String shiptophone) {
		this.shiptophone = shiptophone;
	}

	public String getShiptoemail() {
		return shiptoemail;
	}

	public void setShiptoemail(String shiptoemail) {
		this.shiptoemail = shiptoemail;
	}
}
