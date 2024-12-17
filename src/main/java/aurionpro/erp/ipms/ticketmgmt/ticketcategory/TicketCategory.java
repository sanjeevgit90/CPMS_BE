package aurionpro.erp.ipms.ticketmgmt.ticketcategory;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.audit.JKDEntityAudit;

@Entity
@Table(name = "ticketcategory", schema = "ticketmgmt")
public class TicketCategory extends JKDEntityAudit { 
        
	    @Id
	    @Column(length = 100)
		private String categoryName;
 
		private String parentCategoryId;
		
		@Column(length =100)
		private String categoryType;
		
		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName == null? null:categoryName;
		}

		public String getParentCategoryId() {
			return parentCategoryId;
		}

		public void setParentCategoryId(String parentCategoryId) {
			this.parentCategoryId = parentCategoryId;
		}

		public String getCategoryType() {
			return categoryType;
		}

		public void setCategoryType(String categoryType) {
			this.categoryType = categoryType;
		}

         
}

