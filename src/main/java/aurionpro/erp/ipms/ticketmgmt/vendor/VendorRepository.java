package aurionpro.erp.ipms.ticketmgmt.vendor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface VendorRepository extends CrudRepository<VendorView,Long> {

	
	@Query("select t from VendorView t where project=?1")
	List<VendorView> getVendortList(Long project);

	@Query("select t.vendorName as selectionid, t.vendorName as selectionvalue from Vendor t where isDeleted=false")
	List<SelectionList> getAllVendortList();
}
