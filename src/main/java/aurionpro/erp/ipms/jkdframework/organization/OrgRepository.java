package aurionpro.erp.ipms.jkdframework.organization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface OrgRepository extends JpaRepository<Organization,Long> {

    @Query("select o.entityId as selectionid, o.orgName as selectionvalue from Organization o order by o.orgName")
    public List<SelectionList> SelectionOrgList();

    public List<Organization> findByOrgName(String orgName);

}