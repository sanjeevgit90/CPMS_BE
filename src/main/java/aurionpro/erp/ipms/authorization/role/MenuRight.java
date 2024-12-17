package aurionpro.erp.ipms.authorization.role;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MenuRight {

    @Column(length = 20)
    private String menuName;
    private boolean enableAdd;
    private boolean enableEdit;
    private boolean enableDelete;
    private boolean enableView;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public boolean isEnableAdd() {
        return enableAdd;
    }

    public void setEnableAdd(boolean enableAdd) {
        this.enableAdd = enableAdd;
    }

    public boolean isEnableEdit() {
        return enableEdit;
    }

    public void setEnableEdit(boolean enableEdit) {
        this.enableEdit = enableEdit;
    }

    public boolean isEnableDelete() {
        return enableDelete;
    }

    public void setEnableDelete(boolean enableDelete) {
        this.enableDelete = enableDelete;
    }

    public boolean isEnableView() {
        return enableView;
    }

    public void setEnableView(boolean enableView) {
        this.enableView = enableView;
    }

    public MenuRight() {
    }
    
}