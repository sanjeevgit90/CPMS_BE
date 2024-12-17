package aurionpro.erp.ipms.authorization.userprofile;

public class UserProfileSearchRequest extends UserProfileView {

	 private String roles;
	   
	 private Long organizations;
	   
		public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Long getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Long organizations) {
		this.organizations = organizations;
	}

    
}