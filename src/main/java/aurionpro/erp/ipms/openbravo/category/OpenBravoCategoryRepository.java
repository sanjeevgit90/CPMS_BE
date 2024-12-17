package aurionpro.erp.ipms.openbravo.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpenBravoCategoryRepository extends JpaRepository<OpenBravoCategoryMaster, String> {

	@Query(value = "select c.category_name from openbravo.ob_category_master c where c.open_bravo_id = ?1", nativeQuery = true)
	String getCatgeoryFromOpenBravoId(String obId);
}
