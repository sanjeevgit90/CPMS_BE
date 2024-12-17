package aurionpro.erp.ipms.jkdframework.organization;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="organization",schema = "authentication",uniqueConstraints = @UniqueConstraint(columnNames ={"orgName"}))
public class Organization extends JKDEntityAuditWithId {

    @Column(length = 25)
    private String orgName;
    @Column(length = 15)
    private String orgPan;
    @Column(length = 15)
    private String orgTan;
    @Column(length = 500)
    private String orgRegAddress;
    @Column(length = 20)
    private String orgCountry;
    @Column(length = 500)
    private String orgDirectors;
    @Column(length = 250)
	private String poHeadName;
	@Column(length = 250)
	private String poHeadDesignation;
	@Column(length = 10)
	private String pushPo;
	@Column
	private Integer productFlag;

    public Organization() {
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null: orgName.trim();
    }

    public String getOrgPan() {
        return orgPan;
    }

    public void setOrgPan(String orgPan) {
        this.orgPan = orgPan;
    }

    public String getOrgTan() {
        return orgTan;
    }

    public void setOrgTan(String orgTan) {
        this.orgTan = orgTan;
    }

    public String getOrgRegAddress() {
        return orgRegAddress;
    }

    public void setOrgRegAddress(String orgRegAddress) {
        this.orgRegAddress = orgRegAddress;
    }

    public String getOrgCountry() {
        return orgCountry;
    }

    public void setOrgCountry(String orgCountry) {
        this.orgCountry = orgCountry;
    }

    public String getOrgDirectors() {
        return orgDirectors;
    }

    public void setOrgDirectors(String orgDirectors) {
        this.orgDirectors = orgDirectors;
    }

	public String getPoHeadName() {
		return poHeadName;
	}

	public void setPoHeadName(String poHeadName) {
		this.poHeadName = poHeadName;
	}

	public String getPoHeadDesignation() {
		return poHeadDesignation;
	}

	public void setPoHeadDesignation(String poHeadDesignation) {
		this.poHeadDesignation = poHeadDesignation;
	}

	public String getPushPo() {
		return pushPo;
	}

	public void setPushPo(String pushPo) {
		this.pushPo = pushPo;
	}

	public Integer getProductFlag() {
		return productFlag;
	}

	public void setProductFlag(Integer productFlag) {
		this.productFlag = productFlag;
	}

}