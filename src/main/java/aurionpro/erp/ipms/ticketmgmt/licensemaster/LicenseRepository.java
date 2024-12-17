package aurionpro.erp.ipms.ticketmgmt.licensemaster;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface LicenseRepository extends CrudRepository<LicenseMaster,String> {

    @Query("select l.licenseTag as selectionid, l.licenseTag as selectionvalue from LicenseMaster l")
	List<SelectionList> getAllLicenseList();

//	@Query("select l.licenseTag as selectionid, l.licenseTag as selectionvalue from LicenseMaster l where l.isDeleted=false")
//	List<SelectionList> getActiveLicenseList();
//	
 
	@Query("select a.licenseTag , a.licenseTag as selectionvalue from LicenseMaster a where a.isDeleted=false")
	List<SelectionList> getActiveLicenseList();

	

	
}
