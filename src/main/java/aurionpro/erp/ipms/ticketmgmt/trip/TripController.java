package aurionpro.erp.ipms.ticketmgmt.trip;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ipms/tripmaster")

public class TripController {
	
	
	@Autowired
	TripService tripService;

	    
    @PostMapping()
	public TripMaster createTripMaster(@Valid @RequestBody TripMaster tripMaster) {
		validate();
		return tripService.createTripMaster(tripMaster);
	}
    
    @PutMapping("/updateTrip")
    public TripMaster updateTrip(@Valid @RequestBody TripMaster tripMaster){
    	 validate();
		 return tripService.updateTrip(tripMaster);
    }
    
	@PostMapping("/tripByFilter")
	public List<TripMaster> getTripByFilter(@RequestBody TripMaster tripMaster) {
         return tripService.getTripByFilter(tripMaster);
    }
	
    @GetMapping("/{ticketId}")
    public Optional<TripMaster> getTripByTicketId(@PathVariable(value = "ticketId") Long ticketId) {
	      return tripService.getTripByTicketId(ticketId);
    }
  
    @PostMapping("/saveTripFromThirdParty")
	public TripMaster saveTripFromThirdParty(@Valid @RequestBody TripMaster tripMaster) {
			return tripService.saveTripDataFromThirdParty(tripMaster);
	}
    
    @DeleteMapping("/{id}")
    public TripMaster deleteTripFromTicket(@PathVariable(value = "id") Long id){
    	return  tripService.deleteTripFromTicket(id);
	}
    
    private void validate() {
    }
    
    
}
