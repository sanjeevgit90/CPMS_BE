/*
Author: Jitendra V dubey
About Package:
Description: This Listner sets the Audit fields along with Organization Id and Deleted flags
        During add and only audit fields during edit
Date: 01St April 2020
*/

package aurionpro.erp.ipms.jkdframework.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import aurionpro.erp.ipms.utility.MyPrincipal;

public class JKDAuditListner {

    @PrePersist
    public void beforeAdd(JKDEntityAudit auditEntity){
        auditEntity.setCreatedBy(MyPrincipal.getMyProfile().getUsername());
        auditEntity.setCreatedDate(System.currentTimeMillis());
        auditEntity.setIsDeleted(false);
        if (auditEntity.getOrganizationId()==null){
            auditEntity.setOrganizationId(MyPrincipal.getMyProfile().getOrganizationId());
        }
    }

    @PreUpdate
    public void beforeUpdate(JKDEntityAudit auditEntity){
        auditEntity.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
        auditEntity.setUpdatedDate(System.currentTimeMillis());
    }

}