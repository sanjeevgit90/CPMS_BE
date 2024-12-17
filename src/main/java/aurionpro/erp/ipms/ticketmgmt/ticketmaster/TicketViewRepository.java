package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketViewRepository extends JpaRepository<TicketMasterView,Long> {

	Optional<TicketMaster> findByTicketNo(String ticketNo);

    @Query(value = "select p.entityid from ticketmgmt.ticketmaster_view p where p.accountname in "
    		+ " (select id from projectmgmt.getproject(?1)) and p.createddate between ?2 and ?3", nativeQuery = true )
	List<Long> getIncidentTickets(Long profileId, Long fromDate, Long toDate);

    @Query(value = "select p.entityid from ticketmgmt.ticketmaster_view p where p.accountname in "
    		+ " (select id from projectmgmt.getproject(?1)) and p.createddate >= ?2", nativeQuery = true )
	List<Long> getTicketFromDate(Long profileId, Long fromDate);

    @Query(value = "select p.entityid from ticketmgmt.ticketmaster_view p where p.accountname in "
    		+ " (select id from projectmgmt.getproject(?1)) and p.createddate <= ?2", nativeQuery = true )
	List<Long> getTicketToDate(Long profileId, Long fromDate);

    @Query(value = "select p.entityid from ticketmgmt.ticketmaster_view p where p.accountname in "
    		+ " (select id from projectmgmt.getproject(?1))", nativeQuery = true )
	List<Long> getIncidentTickets(Long profileId);

    @Query(value = "select * from ticketmgmt.ticketfilter(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15)", nativeQuery = true )
    List<TicketMasterView> getTicketByFilter(String ticketNo, Long accountName, String priority, String state,
			String district, String location, String ticketStatus, String ticketTitle, Long assetid, Long fromDate,
			Long toDate, Long ticketOwner, Long userId, String sort, String column);

	

}
