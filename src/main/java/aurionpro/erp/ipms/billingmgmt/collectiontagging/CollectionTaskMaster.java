package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;

@Entity
@Table(name = "collectiontask", schema = "billingmgmt")
public class CollectionTaskMaster extends TaskMaster {

	private Long collectionid;
	
	@Column(length = 200)
	private String remark;
    
    public CollectionTaskMaster() {

    }
    

	public Long getCollectionid() {
		return collectionid;
	}


	public void setCollectionid(Long collectionid) {
		this.collectionid = collectionid;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Override
    protected Object clone() throws CloneNotSupportedException {
        CollectionTaskMaster tm=(CollectionTaskMaster)super.clone();
        tm.setRemark(null);
        //tm.setProbableUse(null);

        return tm;
    }

    
}