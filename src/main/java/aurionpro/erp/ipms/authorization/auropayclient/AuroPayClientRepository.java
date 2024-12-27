package aurionpro.erp.ipms.authorization.auropayclient;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuroPayClientRepository extends JpaRepository<AuroPayClient,Long>{


	List<AuroPayClient> findByEmailId(String emailId);


	Optional<AuroPayClient> findByClientName(String clientname);
	

	AuroPayClient findByEmailIdAndPasswordAndStatusAndIsDeleted(String clientname, String password, int i, boolean b);
	AuroPayClient findByEmailIdAndStatusAndIsDeleted(String clientname, int i, boolean b);

}
