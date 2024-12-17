package aurionpro.erp.ipms.ticketmgmt.trip;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TripRepository  extends JpaRepository<TripMaster,Long> {
     
	Optional<TripMaster> findByTripId(String tripId);

	Optional<TripMaster> findByTicketId(Long tripId);
	
	
	@Modifying
    @Query(value = "update ticketmgmt.tripmaster  set ticketid = ?1 where entityid in (?2)", nativeQuery = true)
    int updateTrip(String ticketId, List<Long> idList);
 
	@Query("select p from TripMaster p where p.ticketId is null and p.vehicleNo=?1 and p.startTime>= ?2 and p.endTime<=?3"  )
	List<TripMaster> getTripByFilter(String vehicleNo, Long startTime, Long endTime);

    //public TripMaster deleteTripFromTicket(Long id);

 
}
