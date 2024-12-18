package aurionpro.erp.ipms.authorization.auropayclient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuroPayClientRepository extends JpaRepository<AuroPayClient,Long>{

	List<AuroPayClient> findByEmailId(String emailId);

}
