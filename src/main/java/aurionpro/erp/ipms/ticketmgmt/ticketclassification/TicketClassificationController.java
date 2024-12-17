package aurionpro.erp.ipms.ticketmgmt.ticketclassification;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;


@RestController
@RequestMapping(value = "/ipms/ticketclassification")
public class TicketClassificationController {

    @Autowired
    TicketClassificationRepository ticketClassificationRepository;

    @Autowired
    TicketClassificationViewRepository classRepository;

    
    @PreAuthorize("hasAuthority('Ticket_Classifications_VIEW')")
    @PostMapping("/classificationByFilter")
    public Iterable<TicketClassificationView> getAllClassifiction(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketClassificationView ticketClassificationView){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<TicketClassificationView> classEx=Example.of(ticketClassificationView,em);
    	 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
     	{
     		Pageable paging = PageRequest.of(page, size);
     		 return classRepository.findAll(classEx, paging).getContent();
     	}
     	else
     	{
     		 return classRepository.findAll(classEx);
     	}
       
    }
    
    @GetMapping("/getAllClassificationList")
    public List<SelectionList> getAllClassificationList(){
        return ticketClassificationRepository.getAllClassificationList();
    }
     
    @GetMapping("/getActiveClassificationList")
    public List<SelectionList> getActiveClassificationList(){
        return ticketClassificationRepository.getActiveClassificationList();
    }
    
    @GetMapping("/getClassificationByProjectList/{id}")
    public List<SelectionList> getClassificationByProjectList(@PathVariable(value = "id") Long id){
        return ticketClassificationRepository.getClassificationByProjectList(id);
    }
    
    @PreAuthorize("hasAuthority('Ticket_Classifications_ADD')")
    @PostMapping()
    public TicketClassification createClassification(@RequestBody TicketClassification classification){
    	Optional<TicketClassification> classificationTemp= ticketClassificationRepository.findClassfication(classification.getClassificationValue(), classification.getProjectId());
    	
 		 if(classificationTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Classification already exists");
          }
 		 else
 		 {
 			 validate();
 			 return ticketClassificationRepository.save(classification);
 		 }
   }
  
    @PreAuthorize("hasAuthority('Ticket_Classifications_EDIT')")
    @PutMapping()
    public TicketClassification updateClassification(@RequestBody TicketClassification classification){
    	Optional<TicketClassification> classificationTemp= ticketClassificationRepository.findById(classification.getEntityId());
   	    	 
   		 if (classificationTemp == null)
   			 throw new EntityExistsException("The Specified Classification doesn't exists");
    		validate();
    		return ticketClassificationRepository.save(classification);
    }
    
    
    private void validate() {
    }
    
    

//    @DeleteMapping()
//    public TicketClassification deleteClassification(@RequestBody TicketClassification ticketclassification){
//
//        Optional<TicketClassification> classificationList= ticketClassificationRepository.findById(ticketclassification.getClassificationValue());
//
//        if(classificationList == null)
//        {
//            throw new EntityNotFoundException("The Specified Classification Value deos not exists");
//        }
//
//        if (classificationList.get().isDeleted()){
//            throw new EntityNotFoundException("Ticket Classifiction Value already deleted");
//        }
//        else{
//        	ticketclassification.setDeleted(true);
//            return ticketClassificationRepository.save(ticketclassification);
//        }
//    }
    
    @PreAuthorize("hasAuthority('Ticket_Classifications_DELETE')")
    @DeleteMapping("/{classification}")
    public TicketClassification deleteClassification(@PathVariable(value = "classification") Long classification){

        Optional<TicketClassification> classificationList= ticketClassificationRepository.findById(classification);

        if(classificationList== null)
        {
            throw new EntityNotFoundException("The Specified Classification deos not exists");
        }

        if (classificationList.get().getIsDeleted()){
            throw new EntityNotFoundException("Classification already deleted");
        }
        else{
        	classificationList.get().setIsDeleted(true);
            return ticketClassificationRepository.save(classificationList.get());
        }
    }
    
    @PreAuthorize("hasAuthority('Ticket_Classifications_VIEW')")
    @GetMapping("/{classificationValue}")
    public TicketClassification getTicketClassificationByValue(@PathVariable(value = "classificationValue") Long classificationValue){
        Optional<TicketClassification> classificationList= ticketClassificationRepository.findById(classificationValue);

        if (classificationList != null)
            return classificationList.get();
        else
            throw new EntityValidationException("Classification Invalid","Classification Value Not Found");
    }
    
}
       
