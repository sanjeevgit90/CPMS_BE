package aurionpro.erp.ipms.authorization.role;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="role", schema = "authentication")
public class Role extends JKDEntityAudit{

    @Id
    @Column(length = 20)
    private String rolename;
    private boolean isDeleted;

   /*  @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    Collection<UserProfile> userProfile; */

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_rights", schema = "authentication",joinColumns = @JoinColumn(name = "rolename"))
    private Collection<MenuRight> menuRights;
    
    public Role() {
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(final String rolename) {
        this.rolename = rolename == null? null : rolename.trim();
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(final boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Collection<MenuRight> getMenuRights() {
        return menuRights;
    }

    public void setMenuRights(Collection<MenuRight> menuRights) {
        this.menuRights = menuRights;
    }
    
}