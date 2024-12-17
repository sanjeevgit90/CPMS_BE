package aurionpro.erp.ipms.authorization.department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAuditWithId;

@Entity
@Table(name="department",schema = "authentication",uniqueConstraints = @UniqueConstraint(columnNames ={"departmentName"}))
public class Department extends JKDEntityAuditWithId {

    @NotNull(message = "Department Name cannot be Blank")
    @Column(length = 50,nullable = false)
    private String departmentName;
    @NotNull(message = "Department Code cannot be Blank")
    @Column(length = 10,nullable = false)
    private String departmentCode;

    /* @ManyToMany()
    @JoinTable(
        name = "user_department", schema = "authentication",
        joinColumns = { @JoinColumn(name = "userid") }, 
        inverseJoinColumns = { @JoinColumn(name = "departmentid") })
    private List<UserProfile> Users; */

    public Department() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName== null ? null : departmentName.trim();
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

}