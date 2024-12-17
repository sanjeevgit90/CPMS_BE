package aurionpro.erp.ipms.jkdframework.authentication;

import java.util.List;

import aurionpro.erp.ipms.common.geographymaster.GeographyMaster;

public class AuthenticationResponse {

    private final String jwt;
    private final String firstName;
    private final String lastName;
    private final String userrights;
    private List<GeographyMaster> userdistrict;
    private final Long userid;

    public String getJwt() {
        return jwt;
    }

    public AuthenticationResponse(String jwt, String firstName, String lastName, String rights, Long userid) {
        this.jwt = jwt;
        this.firstName=firstName;
        this.lastName=lastName;
        this.userrights=rights;
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserrights() {
        return userrights;
    }

    public List<GeographyMaster> getUserdistrict() {
        return userdistrict;
    }

    public void setUserdistrict(List<GeographyMaster> userdistrict) {
        this.userdistrict = userdistrict;
    }

	public Long getUserid() {
		return userid;
	}

}