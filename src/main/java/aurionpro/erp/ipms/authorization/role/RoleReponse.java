package aurionpro.erp.ipms.authorization.role;

import java.util.List;

public class RoleReponse {

    String rolename;
    List<MenuRightsWithChild> menuRights;

    public RoleReponse(String rolename) {
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<MenuRightsWithChild> getMenuRights() {
        return menuRights;
    }

    public void setMenuRights(List<MenuRightsWithChild> menuRights) {
        this.menuRights = menuRights;
    }
    
}
