package aurionpro.erp.ipms.authorization.role;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface RoleRepository extends CrudRepository<Role, String> {

    @Query("select r.rolename as selectionid, r.rolename as selectionvalue from Role r")
    public List<SelectionList> SelectionRoleList();

    //public List<Role> findByUserProfileEntityId(Long userProfileId);
    
}