/*
Author: Jitendra V dubey
About Package:
Description: This Super class is created to be inhertited by other entities that need 
    Autogenerated Entity Id, Audit Fields along with Organization Id and Deleted flags
Date: 01St April 2020
*/
package aurionpro.erp.ipms.jkdframework.audit;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
@EntityListeners(JKDAuditListnerWithId.class)
//@JsonIgnoreProperties({"createdBy","createdDate","updatedBy","updatedDate","organizationId"})
public class JKDEntityAuditWithId {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="ipms_seq")
    @SequenceGenerator(name = "ipms_seq", sequenceName ="entity_sequence", allocationSize = 1, initialValue = 1)
    private Long entityId;

    @Column(updatable = false)
    private Long createdDate;
    @Column(updatable = false,length = 50)
    private String createdBy;
    private Long updatedDate;
    @Column(length = 50)
    private String updatedBy;
    @Column(updatable = false)
    private Long organizationId;

    @Column(nullable = true)
    private Boolean isDeleted;
    
    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

     public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
    

}