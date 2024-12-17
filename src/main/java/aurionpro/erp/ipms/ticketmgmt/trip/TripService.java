package aurionpro.erp.ipms.ticketmgmt.trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

@Service()
@Transactional
public class TripService {
	
	@Autowired
	TripRepository tripRepository;
	
	
    public TripMaster createTripMaster(@Valid  TripMaster tripMaster){
    	Optional<TripMaster> tripTemp= tripRepository.findById(tripMaster.getEntityId());
    	
  		 if(tripTemp.isPresent())
           {
               throw new EntityExistsException("The Specified Trip already exists");
           }
  		 else
  		 {
  			 validate();
  			 return tripRepository.save(tripMaster);
  		 }
    }
    
    private void validate() {
    }
    
   public TripMaster updateTrip(TripMaster tripMaster) {
		List<Long> idList = tripMaster.getIdList();
		if(idList==null){
			return tripRepository.save(tripMaster);
		} else {
			int i= tripRepository.updateTrip(tripMaster.getTicketId(), idList);
		}
		return tripMaster;
	}
    
    
//    public TripMaster updateTrip(@Valid  TripMaster tripMaster){
//    	Optional<TripMaster> tripTemp= tripRepository.findById(tripMaster.getEntityId());
//    	
//  		 if(tripTemp.isPresent())
//           {
//               throw new EntityExistsException("The Specified Trip already exists");
//           }
//  		 else
//  		 {
//  			 validate();
//  			 return tripRepository.save(tripMaster);
//  		 }
//    }
//    
    
    public List<TripMaster> getTripByFilter( TripMaster tripMaster){
        
		
        return tripRepository.getTripByFilter(tripMaster.getVehicleNo(), tripMaster.getStartTime(),tripMaster.getEndTime());
        //return  tripRepository.findAll(tripEx);
    }
    
    public Optional<TripMaster> getTripByTicketId(Long ticketId)
    {
    	Optional<TripMaster> tripObj = tripRepository.findByTicketId(ticketId);
       	if (tripObj == null)
    	{
       		new RuntimeException("Can not find Trip with Ticket Id" +ticketId);
    	}
		return tripObj;
    }
    
    public TripMaster saveTripDataFromThirdParty(TripMaster tripMaster) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//		Date date = new Date();
//        String dateString = dateFormat.format(date);
////		
		TripMaster tripObj = null;
		List<TripRecordDto> records = new ArrayList<TripRecordDto>();
		records = tripMaster.getRecords();
		
		for(TripRecordDto obj : records){
			tripObj = new TripMaster();
			tripObj.setRecordId(obj.getObdSerialId());
			if(obj.getTripId()>0)
				tripObj.setTripId(Integer.toString(obj.getTripId()));
			else
				tripObj.setTripId(null);
			tripObj.setParentId(Integer.toString(obj.getParentId()));
			tripObj.setGroupId(Integer.toString(obj.getGroupId()));
			tripObj.setLatitude(obj.getLatitude());
			tripObj.setLongitude(obj.getLongitude());
			tripObj.setMaxSpeed(obj.getMaxSpeed());
			tripObj.setPolyline(obj.getPolyline());
			tripObj.setUrlString(obj.getUrlString());
			tripObj.setSourceLat(obj.getSourceLat());
			tripObj.setSourceLong(obj.getSourceLong());
			tripObj.setDestinationLat(obj.getDestinationLat());
			tripObj.setDestinationLong(obj.getDestinationLong());
			tripObj.setDistance(obj.getDistanceValue());
			tripObj.setLocationType(obj.getLocationType());
			tripObj.setIgnitionEvent(obj.getIgnitionEvent());
			tripObj.setTicketId(tripMaster.getTicketId());
			tripObj.setVehicleNo(tripMaster.getVehicleNo());
			tripObj.setDestAdd(obj.getDestAdd());
			tripObj.setSourceAdd(obj.getSourceAdd());
				
				tripObj.setStartTime((obj.getStartTime()));
				tripObj.setEndTime((obj.getEndTime()));
			
			tripRepository.save(tripObj);
		}

		return null;
	}

    
    public TripMaster deleteTripFromTicket(Long id) {
		 Optional<TripMaster> tripList= tripRepository.findById(id);

	        if(tripList== null)
	        {
	            throw new EntityNotFoundException("The Specified Trip does not exists");
	        }

	        if (tripList.get().getIsDeleted()){
	            throw new EntityNotFoundException("Record already deleted");
	        }
	        else{
	        	tripList.get().setIsDeleted(true);
	        	TripMaster tripObj = tripRepository.save(tripList.get());
	    		 
	    		
	    		return tripObj;
	        }
    	
		//return tripRepository.deleteTripFromTicket(id);
	}
    
	
	
	

}
