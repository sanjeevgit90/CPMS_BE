package aurionpro.erp.ipms.authorization.userfamily;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFamilyRepository extends JpaRepository<UserFamily,Long>{

    public List<UserFamily> findByMemberName(String memberName);

    public List<UserFamily> findByUserprofileEntityId(Long userProfileId);

}