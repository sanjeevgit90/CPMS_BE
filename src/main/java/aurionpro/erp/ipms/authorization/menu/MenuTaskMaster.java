package aurionpro.erp.ipms.authorization.menu;

import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "menutaskmaster", schema = "authentication")
public class MenuTaskMaster extends TaskMaster {

    private String menuname;
    private String probableUse;
    private String forwardComments;

    public MenuTaskMaster() {

    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getProbableUse() {
        return probableUse;
    }

    public void setProbableUse(String probableUse) {
        this.probableUse = probableUse;
    }

    public String getForwardComments() {
        return forwardComments;
    }

    public void setForwardComments(String forwardComments) {
        this.forwardComments = forwardComments;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MenuTaskMaster tm=(MenuTaskMaster)super.clone();
        tm.setForwardComments(null);
        tm.setProbableUse(null);

        return tm;
    }

    
}