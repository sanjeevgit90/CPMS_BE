package aurionpro.erp.ipms.ticketmgmt.ticketcounter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCounterRepository  extends JpaRepository<TicketCounter,String> {
    
}
