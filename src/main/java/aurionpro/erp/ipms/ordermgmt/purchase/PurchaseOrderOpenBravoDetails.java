package aurionpro.erp.ipms.ordermgmt.purchase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "purchase_order_open_bravo_details", schema = "ordermgmt")
public class PurchaseOrderOpenBravoDetails extends JKDEntityAuditWithId {

	/*@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", length = 36, unique = true)
	private String id;*/

	/*@Column(name = "purchase_order_id", length = 36, unique = true)
	private String purchaseOrderId;*/
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="purchase_order_id", unique = true)
	private PurchaseOrderMaster purchaseOrderMaster;
	
	@Column(name = "po_pushed_status", length = 15)
	private String poPushedStatus;

	@Column(name = "po_pushed_date")
	private Long poPushedDate;

	public PurchaseOrderMaster getPurchaseOrderMaster() {
		return purchaseOrderMaster;
	}

	public void setPurchaseOrderMaster(PurchaseOrderMaster purchaseOrderMaster) {
		this.purchaseOrderMaster = purchaseOrderMaster;
	}

	public String getPoPushedStatus() {
		return poPushedStatus;
	}

	public void setPoPushedStatus(String poPushedStatus) {
		this.poPushedStatus = poPushedStatus;
	}

	public Long getPoPushedDate() {
		return poPushedDate;
	}

	public void setPoPushedDate(Long poPushedDate) {
		this.poPushedDate = poPushedDate;
	}
	
	
}
