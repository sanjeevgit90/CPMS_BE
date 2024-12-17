package aurionpro.erp.ipms.authorization.menu;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="menu", schema = "authentication")
public class Menu extends JKDEntityAudit{

    @Id
    @Column(length = 20)
    private String menuname;
    
    @Column(length = 20)
    private Integer menuorder;

    @Column(nullable = false, length = 20)
    private String displayname;

    @Column(nullable = true,length = 150)
    private String description;

    @Column(length = 50, nullable = false)
    private String urlpath;

    @ManyToOne()
    @JoinColumn(name="parentmenu")
    @JsonIgnoreProperties("childmenu")
    private Menu parent;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Menu> childmenu;

    public Menu() {
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null :menuname.trim();
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildmenu() {
        return childmenu;
    }

    public void setChildmenu(List<Menu> childmenu) {
        this.childmenu = childmenu;
        if (this.childmenu!=null)
            this.childmenu.forEach(c->c.setParent(this));
    }

	public Integer getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(Integer menuorder) {
		this.menuorder = menuorder;
	}
        

}