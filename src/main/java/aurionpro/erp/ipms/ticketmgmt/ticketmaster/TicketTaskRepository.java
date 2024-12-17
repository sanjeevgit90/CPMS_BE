package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TicketTaskRepository extends CrudRepository<TicketTaskHistory,Long> {
	
	@Query("select t from TicketTaskHistory t where t.ticketNo=?1")
	List<TicketTaskHistory> getHistoryTicketList(String ticketNo);
	
	
	

	

	
}