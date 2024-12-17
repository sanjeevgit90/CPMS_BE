package aurionpro.erp.ipms.assetmgmt.categorymaster;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name="categorymaster", schema = "assetmgmt")
public class CategoryMaster extends JKDEntityAudit{

	@Id
	@NotEmpty(message = "Category name is required")
	@Column(length = 100)
    private String categoryname;
	
	@Column(length = 50)
    private String categorytype;
   
    @ManyToOne()
    @JoinColumn(name="parentcategory")
    @JsonIgnoreProperties("childcategory")
    private CategoryMaster parent;

   	@OneToMany(mappedBy = "parent", cascade={CascadeType.ALL})
    private Collection<CategoryMaster> childcategory;

    public CategoryMaster() {
    }
    
    public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname== null ? null:categoryname.trim();
	}

	 public String getCategorytype() {
			return categorytype;
		}

		public void setCategorytype(String categorytype) {
			this.categorytype = categorytype;
		}

	public CategoryMaster getParent() {
		return parent;
	}

	public void setParent(CategoryMaster parent) {
		this.parent = parent;
	}

	public Collection<CategoryMaster> getChildcategory() {
		return childcategory;
	}

	public void setChildcategory(Collection<CategoryMaster> childcategory) {
		this.childcategory = childcategory;
		if (childcategory!= null)
		{
			this.childcategory.forEach(c -> c.setParent(this));
		}
	}


}