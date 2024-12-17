package aurionpro.erp.ipms.authorization.userprofile;

import java.util.List;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import aurionpro.erp.ipms.authorization.role.Role;
import aurionpro.erp.ipms.common.geographymaster.GeographyMaster;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;
import aurionpro.erp.ipms.jkdframework.organization.Organization;

@Entity
@Table(name="userprofile", schema = "authentication")
@JsonIgnoreProperties("authorities")
public class UserProfile extends JKDEntityAuditWithId {

    @Column(length = 50,nullable = false)
    private String userName;
    @Column(length = 50,nullable = false)
    private String firstName;
    @Column(length = 50,nullable = false)
    private String lastName;
    @Column(length = 100, nullable = false)
    private String emailId;
    @Column(length = 12)    
    private String mobileNumber;
    private Long dob;
    @Column(length = 15, nullable = false)
    private String gender;
    @Column(length = 20)
    private String employeeCode;
    @Column(length = 50)
    private String designation;
    @Column(length = 500)
    private String address;
    @Column(length = 50)
    private String district;
    @Column(length = 50)
    private String state;
    @Column(length = 25)
    private String country;
    @Column(length = 500)
    private String mobileAppKey;

    @Column(length = 3)
    private String BloodGroup;
    @Column(length = 50)    
    private String emergencyContactPerson;
    @Column(length = 12)    
    private String emergencyContactMobile;
    @Column(length = 50)    
    private String department;
    private Long doj;
    @Column(nullable = true) 
    private boolean isConfirmed;
    @Column(length = 200)  
    private String profileImage;
    
    @Column(length = 100)    
    private String officelocation;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", schema = "authentication")
    @JsonIgnoreProperties("userProfile")
    private List<Role> roles;

    @ManyToMany()
    @JoinTable(
        name = "user_organization", schema = "authentication",
        joinColumns = { @JoinColumn(name = "userid") }, 
        inverseJoinColumns = { @JoinColumn(name = "organizationid") })
    private List<Organization> organizations;

    @ManyToOne()
    @JoinColumn(name="managerId")
    @JsonIgnoreProperties({"manager","roles","authorities","organizations"})
    private UserProfile manager;
    
    @ManyToMany()
    @JoinTable(
        name = "user_district", schema = "authentication",
        joinColumns = { @JoinColumn(name = "userid") }, 
        inverseJoinColumns = { @JoinColumn(name = "district"),
          @JoinColumn(name = "parent")})
    private List<GeographyMaster> mappeddistrict;

    public UserProfile() {
    }

    @JsonIgnore()
    public String getAuthorities() {
        StringJoiner authorities=new StringJoiner(",");

        roles.forEach(r-> {
            authorities.add(r.getRolename());
            r.getMenuRights().forEach(mr->{
                if (mr.isEnableAdd()){authorities.add(mr.getMenuName() + "_ADD");}
                if (mr.isEnableEdit()){authorities.add(mr.getMenuName() + "_EDIT");}
                if (mr.isEnableView()){authorities.add(mr.getMenuName() + "_VIEW");}
                if (mr.isEnableDelete()){authorities.add(mr.getMenuName() + "_DELETE");}
            });
        });

        return authorities.toString();
    }

    @JsonIgnore()
    public String getMyMenus() {
        StringJoiner myMenus=new StringJoiner(",");

        roles.forEach(r-> {
            r.getMenuRights().forEach(mr -> myMenus.add(mr.getMenuName()));  
        });

        return myMenus.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileAppKey() {
        return mobileAppKey;
    }

    public void setMobileAppKey(String mobileAppKey) {
        this.mobileAppKey = mobileAppKey;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserProfile(UserProfile uProfile) {
        this.setEntityId(uProfile.getEntityId());
        this.userName = uProfile.userName;
        this.firstName = uProfile.firstName;
        this.lastName = uProfile.lastName;
        this.emailId = uProfile.emailId;
        this.mobileNumber = uProfile.mobileNumber;
        this.dob = uProfile.dob;
        this.gender = uProfile.gender;
        this.employeeCode = uProfile.employeeCode;
        this.designation = uProfile.designation;
        this.address = uProfile.address;
        this.district = uProfile.district;
        this.state = uProfile.state;
        this.country = uProfile.country;
        this.mobileAppKey = uProfile.mobileAppKey;
        this.roles = uProfile.roles;
        this.doj=uProfile.doj;
        this.department=uProfile.department;
        this.emergencyContactMobile=uProfile.emergencyContactMobile;
        this.emergencyContactPerson=uProfile.emergencyContactPerson;
        this.BloodGroup=uProfile.BloodGroup;
        this.organizations=uProfile.organizations;
        this.isConfirmed=uProfile.isConfirmed;
        this.manager=uProfile.manager;
        this.officelocation = uProfile.officelocation;
        this.mappeddistrict = uProfile.mappeddistrict;
        		
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getEmergencyContactPerson() {
        return emergencyContactPerson;
    }

    public void setEmergencyContactPerson(String emergencyContactPerson) {
        this.emergencyContactPerson = emergencyContactPerson;
    }

    public String getEmergencyContactMobile() {
        return emergencyContactMobile;
    }

    public void setEmergencyContactMobile(String emergencyContactMobile) {
        this.emergencyContactMobile = emergencyContactMobile;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getDoj() {
        return doj;
    }

    public void setDoj(Long doj) {
        this.doj = doj;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public UserProfile getManager() {
        return manager;
    }

    public void setManager(UserProfile manager) {
        this.manager = manager;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

	public String getOfficelocation() {
		return officelocation;
	}

	public void setOfficelocation(String officelocation) {
		this.officelocation = officelocation;
	}

	public List<GeographyMaster> getMappeddistrict() {
		return mappeddistrict;
	}

	public void setMappeddistrict(List<GeographyMaster> mappeddistrict) {
		this.mappeddistrict = mappeddistrict;
	}
	
	@Transient
	 private Boolean blocked;

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	

}