package aurionpro.erp.ipms.common.geographymaster;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class GeographyIdentity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7312503793126158180L;

	@NotEmpty(message = "Geography name is required")
	@Column(length = 100)
	//@Pattern(regexp="/^[a-z][a-z\\s]*$/",message="Geography name should have alphabets with space only")  
	private String geographyname;
	
	@Column(length = 100)
    private String parentgeography;

    public GeographyIdentity() {
    }
    
    public GeographyIdentity(String geographyname, String parentgeography) {
    	this.geographyname = geographyname;
    	this.parentgeography = parentgeography;
    }

	public String getGeographyname() {
		return geographyname;
	}

	public void setGeographyname(String geographyname) {
		this.geographyname = geographyname == null ? null : geographyname.trim();
	}

	public String getParentgeography() {
		return parentgeography;
	}

	public void setParentgeography(String parentgeography) {
		this.parentgeography = parentgeography  == null ? null : parentgeography.trim();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geographyname == null) ? 0 : geographyname.hashCode());
		result = prime * result + ((parentgeography == null) ? 0 : parentgeography.hashCode());
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
		GeographyIdentity other = (GeographyIdentity) obj;
		if (geographyname == null) {
			if (other.geographyname != null)
				return false;
		} else if (!geographyname.equals(other.geographyname))
			return false;
		if (parentgeography == null) {
			if (other.parentgeography != null)
				return false;
		} else if (!parentgeography.equals(other.parentgeography))
			return false;
		return true;
	}
    
	
}