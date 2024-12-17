package aurionpro.erp.ipms.billingmgmt.billingschedule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ScheduleIdentity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7312503793126158180L;

	@NotNull(message = "Project is required")
	private Long projectid;
	
	@Column(length = 10)
    private String milestoneno;

    public ScheduleIdentity() {
    }
    
    public ScheduleIdentity(Long projectid, String milestoneno) {
    	this.projectid = projectid;
    	this.milestoneno = milestoneno;
    }

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public String getMilestoneno() {
		return milestoneno;
	}

	public void setMilestoneno(String milestoneno) {
		this.milestoneno = milestoneno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((milestoneno == null) ? 0 : milestoneno.hashCode());
		result = prime * result + ((projectid == null) ? 0 : projectid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleIdentity other = (ScheduleIdentity) obj;
		if (milestoneno == null) {
			if (other.milestoneno != null)
				return false;
		} else if (!milestoneno.equals(other.milestoneno))
			return false;
		if (projectid == null) {
			if (other.projectid != null)
				return false;
		} else if (!projectid.equals(other.projectid))
			return false;
		return true;
	}

	
    
	
}