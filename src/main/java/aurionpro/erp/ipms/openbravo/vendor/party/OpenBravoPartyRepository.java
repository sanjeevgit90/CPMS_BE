package aurionpro.erp.ipms.openbravo.vendor.party;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpenBravoPartyRepository extends JpaRepository<OpenBravoPartyMaster, String> {

	//public List<OpenBravoPartyMaster> findByOpenBravoId(String id);
	
	//public List<OpenBravoPartyMaster> findByPartyName(String partyName);
	
	@Query(value = "select c.party_id from openbravo.ob_party_master c where c.open_bravo_id = ?1", nativeQuery = true)
	Long getPartyIdFromOpenBravoId(String obId);
}
