package aurionpro.erp.ipms.assetmgmt.hsncodemaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name = "hsnmaster", schema = "assetmgmt",uniqueConstraints = @UniqueConstraint(columnNames ={"hsncode"}))
public class HsnCodeMaster extends JKDEntityAuditWithId {

    @NotEmpty(message = "HSN Code is required")
	@Column(length = 50)
    private String hsncode;

    private Float cgst;
    
    private Float igst;
    
    private Float sgst;
    
    @NotEmpty(message = "Country is required")
	@Column(length = 100)
    private String country;

    public HsnCodeMaster() {
    }

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode==null? null:hsncode.trim();
	}

	public Float getCgst() {
		return cgst;
	}

	public void setCgst(Float cgst) {
		this.cgst = cgst;
	}

	public Float getIgst() {
		return igst;
	}

	public void setIgst(Float igst) {
		this.igst = igst;
	}

	public Float getSgst() {
		return sgst;
	}

	public void setSgst(Float sgst) {
		this.sgst = sgst;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}