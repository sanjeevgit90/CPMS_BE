package aurionpro.erp.ipms.ticketmgmt.servicemaster;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface VehicleServiceRepository extends CrudRepository<VehicleServiceMaster,String> {

    @Query("select l.vehicleRegistrationNumber as selectionid, l.vehicleRegistrationNumber as selectionvalue from VehicleServiceMaster l")
	List<SelectionList> getAllVehicleServiceList();

	@Query("select l.vehicleRegistrationNumber as selectionid, l.vehicleRegistrationNumber as selectionvalue from VehicleServiceMaster l where l.isDeleted=false")
	List<SelectionList> getActiveVehicleList();

	

	
}
