package aurionpro.erp.ipms.openbravo.organisation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpenBravoOrganisationRepository extends JpaRepository<OpenBravoOrganisation,String> {

	@Query(value = "select c.org_id from openbravo.ob_organisation c where c.open_bravo_id = ?1", nativeQuery = true)
	Long getOrgIdFromOpenBravoId(String obId);
}
