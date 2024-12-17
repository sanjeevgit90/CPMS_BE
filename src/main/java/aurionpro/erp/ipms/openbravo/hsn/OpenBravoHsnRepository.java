package aurionpro.erp.ipms.openbravo.hsn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpenBravoHsnRepository extends JpaRepository<OpenBravoHsnMaster, String> {

	//Optional<OpenBravoHsnMaster> findByHsncode(String hsncode);

	/*@Query("select h.hsncode as selectionid, h.hsncode as selectionvalue from OpenBravoHsnMaster h")
	List<SelectionList> getHsnCodeList();

	@Query("select h.hsncode as selectionid, h.hsncode as selectionvalue from OpenBravoHsnMaster h where h.isDeleted=false")
	List<SelectionList> getActiveHsnCodeList();*/
	
	@Query(value = "select c.hsn_id from openbravo.ob_hsn_master c where c.open_bravo_id = ?1", nativeQuery = true)
	String getHsnFromOpenBravoId(String obId);
}
