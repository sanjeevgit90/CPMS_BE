package aurionpro.erp.ipms.ticketmgmt.vehiclemaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@Service()
public class VehicleService {
	
	@Autowired
    VehicleRepository vehicleRepository;
	
//	public List<VehicleMaster> getVehicleByFilter(@RequestBody VehicleMaster vehicleMaster){
//        
//        ExampleMatcher em=ExampleMatcher.matching()
//                            .withIgnoreNullValues()
//                            .withIgnoreCase();
//
//        Example<VehicleMaster> vehicleEx=Example.of(vehicleMaster,em);
//
//        return vehicleRepository.findAll(vehicleEx);
//        
//    }
	
	
public List<VehicleMaster> getVehicleByFilter(VehicleMaster vehicleMaster, Integer page, Integer size){
        
        ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<VehicleMaster> ticketEx=Example.of(vehicleMaster,em);
       
       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
      	{
      		Pageable paging = PageRequest.of(page, size);
      		return vehicleRepository.findAll(ticketEx, paging).getContent();
      	}
      	else
      	{
      		return vehicleRepository.findAll(ticketEx);
      	}
       
       
   }
	
	public List<SelectionList> getAllVehicleList(){
        return vehicleRepository.getAllVehicleList();
    }
	
	public List<SelectionList> getActiveVehicleList(){
        return vehicleRepository.getActiveVehicleList();
    }
	
	public VehicleMaster createVehicleMaster(@Valid  VehicleMaster vehicleMaster){
    	Optional<VehicleMaster> vehicleTemp= vehicleRepository.findById(vehicleMaster.getVehicleRegNumber());
    	
  		 if(vehicleTemp.isPresent())
           {
               throw new EntityExistsException("The Specified Vehicle Registration Number already exists");
           }
  		 else
  		 {
  			 validate();
  			 return vehicleRepository.save(vehicleMaster);
  		 }
    }
   
	private void validate() {
    }
	
	public VehicleMaster updateVehicleMaster(VehicleMaster vehicleMaster){
		Optional<VehicleMaster> vehicleTemp= vehicleRepository.findById(vehicleMaster.getVehicleRegNumber());
	    	 
	   	if (vehicleTemp == null)
	   		throw new EntityExistsException("The Specified Vehicle NO doesn't exists");
	   		 
	   	if(!(vehicleTemp.get().getVehicleRegNumber().equalsIgnoreCase(vehicleMaster.getVehicleRegNumber())))
	        throw new RuntimeException("Vehicle No does not match with the Asset Name");	 
	   	
	   	VehicleMaster vehicleObj = vehicleRepository.save(vehicleMaster);
		 
		 
		 
		return vehicleObj;
  }
	
	
	
	
	
	public Optional<VehicleMaster> getvehicleByRegNumber( String vehicleRegNumber)
    {
    	Optional<VehicleMaster> vehicleObj = vehicleRepository.findById(vehicleRegNumber);
       	if (vehicleObj == null)
    	{
       		new RuntimeException("Can not find vehicle with vehicleRegNumber"+ vehicleRegNumber);
    	}
		return vehicleObj;
    }
	
	

    
	

}
