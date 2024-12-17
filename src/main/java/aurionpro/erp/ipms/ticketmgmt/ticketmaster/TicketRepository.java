package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface TicketRepository extends CrudRepository<TicketMaster,Long> {

	Optional<TicketMaster> findByTicketNo(String ticketNo);

	List<TicketMasterView> findAll(Example<TicketMasterView> ticketEx);
	
	

    
	//List<TicketMaster> findAll(Example<TicketMaster> ticketEx);


}
