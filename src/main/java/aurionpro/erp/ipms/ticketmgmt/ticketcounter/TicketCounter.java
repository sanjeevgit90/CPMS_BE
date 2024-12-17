package aurionpro.erp.ipms.ticketmgmt.ticketcounter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticketcounter", schema = "ticketmgmt")
public class TicketCounter {
    
	@Id
	@Column(length=20)
	private String projectPin;
	
	private long counter;

	public String getProjectPin() {
		return projectPin;
	}

	public void setProjectPin(String projectPin) {
		this.projectPin = projectPin;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}
	
	

}
