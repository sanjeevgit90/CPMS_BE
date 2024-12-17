package aurionpro.erp.ipms.authorization.userfamily;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="userfamily",schema = "authentication")
public class UserFamily extends JKDEntityAuditWithId {

    @NotNull(message = "Family Member Name cannot be Blank")
    @Column(length = 50,nullable = false)
    private String memberName;
    @NotNull(message = "RelationShip cannot be Blank")
    @Column(length = 15,nullable = false)
    private String relationship;
    private Long dob;
    @Column(length = 12,nullable = true)
    private String memberContact;

    @ManyToOne()
    @JoinColumn(name="userprofileId")
    @JsonIgnoreProperties({"manager","roles","authorities","organizations"})
    private UserProfile userprofile;

    public UserFamily() {
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    public UserProfile getUserprofile() {
        return userprofile;
    }

    public void setUserprofile(UserProfile userprofile) {
        this.userprofile = userprofile;
    }
    
}