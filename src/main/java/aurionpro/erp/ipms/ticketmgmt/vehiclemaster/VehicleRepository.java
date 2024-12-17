package aurionpro.erp.ipms.ticketmgmt.vehiclemaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface VehicleRepository extends JpaRepository<VehicleMaster,String> {

	@Query("select v.vehicleRegNumber as selectionid, v.vehicleRegNumber as selectionvalue from VehicleMaster v")
	List<SelectionList> getAllVehicleList();
	
	@Query("select v.vehicleRegNumber as selectionid, v.vehicleRegNumber as selectionvalue from VehicleMaster v where v.isDeleted=false")
	List<SelectionList> getActiveVehicleList();

	Optional<VehicleMaster> findByVehicleName(String vehicleName);
	
	
}