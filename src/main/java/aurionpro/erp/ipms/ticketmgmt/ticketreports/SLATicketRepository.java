package aurionpro.erp.ipms.ticketmgmt.ticketreports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SLATicketRepository extends JpaRepository<SLATicketReport,Long> {

    @Query(value = "select p.entityid from ticketmgmt.ticket_sla_report p where p.accountname in "
	    		+ " (select id from projectmgmt.getproject(?1)) and p.ticketclosedtime between ?2 and ?3", nativeQuery = true )
	List<Long> getSLATicket(Long profileId, Long fromDate, Long toDate);

    @Query(value = "select p.entityid from ticketmgmt.ticket_sla_report p where p.accountname in "
    		+ " (select id from projectmgmt.getproject(?1)) and p.ticketclosedtime >= ?2", nativeQuery = true )
	List<Long> getSLATicketFromDate(Long profileId, Long fromDate);

    @Query(value = "select p.entityid from ticketmgmt.ticket_sla_report p where p.accountname in "
    		+ " (select id from projectmgmt.getproject(?1)) and p.ticketclosedtime <= ?2", nativeQuery = true )
	List<Long> getSLATicketToDate(Long profileId, Long fromDate);

    @Query(value = "select p.entityid from ticketmgmt.ticket_sla_report p where p.accountname in "
    		+ " (select id from projectmgmt.getproject(?1))", nativeQuery = true )
	List<Long> getSLATicketTickets(Long profileId);

}
