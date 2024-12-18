package aurionpro.erp.ipms.authorization.auropayclient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ClientLoginRepository extends CrudRepository<ClientLogin,String> {

}
