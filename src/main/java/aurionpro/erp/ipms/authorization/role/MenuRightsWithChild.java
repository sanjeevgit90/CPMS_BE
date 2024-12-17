package aurionpro.erp.ipms.authorization.role;

import java.util.List;

public class MenuRightsWithChild extends MenuRight {
    
    public String displayname;
    public List<MenuRightsWithChild> childMenu;

    public List<MenuRightsWithChild> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<MenuRightsWithChild> childMenu) {
        this.childMenu = childMenu;
    }

    public MenuRightsWithChild() {
    }

    public MenuRightsWithChild(String menuname,String displayname, boolean enableAdd, 
            boolean enableEdit, boolean enableView, boolean enableDelete) {

        this.setEnableAdd(enableAdd);
        this.setEnableDelete(enableDelete);
        this.setEnableEdit(enableEdit);
        this.setEnableView(enableView);
        this.setMenuName(menuname);
        this.setDisplayname(displayname);
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
}