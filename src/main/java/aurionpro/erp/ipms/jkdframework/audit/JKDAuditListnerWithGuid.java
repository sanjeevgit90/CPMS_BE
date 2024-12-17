/*
Author: Jitendra V dubey
About Package:
Description: This Listner sets the Generates GUID for primary key, Audit fields along 
        with Organization Id and Deleted flags during add and only audit fields during edit
Date: 01St April 2020
*/
package aurionpro.erp.ipms.jkdframework.audit;

import java.util.UUID;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import aurionpro.erp.ipms.utility.MyPrincipal;

public class JKDAuditListnerWithGuid {

    @PrePersist
    public void beforeAdd(JKDEntityAuditWithGuid auditEntity){
        auditEntity.setCreatedBy(MyPrincipal.getMyProfile().getUsername());
        auditEntity.setCreatedDate(System.currentTimeMillis());
        auditEntity.setEntityId(UUID.randomUUID().toString());
        auditEntity.setDeleted(false);

        if (auditEntity.getOrganizationId()==null){
            auditEntity.setOrganizationId(MyPrincipal.getMyProfile().getOrganizationId());
        }
    }

    @PreUpdate
    public void beforeUpdate(JKDEntityAuditWithGuid auditEntity){
        auditEntity.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
        auditEntity.setUpdatedDate(System.currentTimeMillis());
    }

}