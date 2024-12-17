package aurionpro.erp.ipms.ticketmgmt.ticketproblemreport;


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
@RequestMapping(value = "/ipms/ticketproblemreport")
public class TicketProblemReportController {

    @Autowired
    TicketProblemReportRepository ticketProblemReportRepository;

    @Autowired
    TicketProblemReportViewRepository ticketViewRepository;

    
    @GetMapping()
    public Iterable<TicketProblemReport> getAllProblemReport(){
        return ticketProblemReportRepository.findAll();
    }
    
    @GetMapping("/getAllProblemReportList")
    public List<SelectionList> getAllProblemReportList(){
        return ticketProblemReportRepository.getAllProblemReportList();
    }
    
    @GetMapping("/getActiveProblemReportList")
    public List<SelectionList> getActiveProblemReportList(){
        return ticketProblemReportRepository.getActiveProblemReportList();
    }
    
    @GetMapping("/getProblemByProjectList/{id}")
    public List<SelectionList> getProblemByProjectList(@PathVariable(value = "id") Long id){
        return ticketProblemReportRepository.getProblemByProjectList(id);
    }
    
    @PreAuthorize("hasAuthority('Ticket_Problem_Reported_VIEW')")
    @PostMapping("/ticketProblemByFilter")
    public Iterable<TicketProblemView> getAllProblem(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketProblemView ticketProblemView){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<TicketProblemView> problemEx=Example.of(ticketProblemView,em);
       
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
     	{
     		Pageable paging = PageRequest.of(page, size);
     		 return ticketViewRepository.findAll(problemEx, paging).getContent();
     	}
     	else
     	{
     		 return ticketViewRepository.findAll(problemEx);
     	}
       
    }
    
    @PreAuthorize("hasAuthority('Ticket_Problem_Reported_VIEW')")
    @PostMapping()
    public TicketProblemReport createProblemReport(@RequestBody TicketProblemReport ticketProblemReport){
    	Optional<TicketProblemReport> problemReportTemp= ticketProblemReportRepository.findProblemReported(ticketProblemReport.getProblemReportValue(), ticketProblemReport.getProjectId());
    	
 		 if(problemReportTemp.isPresent())
          {
              throw new EntityExistsException("The Specified Problem Report already exists");
          }
 		 else
 		 {
 			 validate();
 			 return ticketProblemReportRepository.save(ticketProblemReport);
 		 }
   }
    @PreAuthorize("hasAuthority('Ticket_Problem_Reported_EDIT')")
    @PutMapping()
    public TicketProblemReport updateProblemReportt(@RequestBody TicketProblemReport ticketProblemReport){
    	
   		 Optional<TicketProblemReport> problemReportTemp= ticketProblemReportRepository.findById(ticketProblemReport.getEntityId());
   		 if (problemReportTemp == null)
   			 throw new EntityExistsException("The Specified Problem Report doesn't exists");
   		 
    		validate();
    		return ticketProblemReportRepository.save(ticketProblemReport);
   	 	
    }
    
    
    private void validate() {
    }
    
//    @DeleteMapping()
//    public TicketProblemReport deleteProblemRepo(@RequestBody TicketProblemReport ticketProblemRepo){
//
//        Optional<TicketProblemReport> problemRepoList= ticketProblemReportRepository.findById(ticketProblemRepo.getProblemReportValue());
//
//        if(problemRepoList== null)
//            throw new EntityNotFoundException("The Specified Problem Report Value deos not exists");
//       
//        if (problemRepoList.get().isDeleted()){
//            throw new EntityNotFoundException("Ticket Problem Report Value already deleted");
//        }
//        else{
//        	ticketProblemRepo.setDeleted(true);
//            return ticketProblemReportRepository.save(ticketProblemRepo);
//        }
//    }
    
    @PreAuthorize("hasAuthority('Ticket_Problem_Reported_VIEW')")
    @GetMapping("/{problemReportValue}")
    public TicketProblemReport getProblemReportByName(@PathVariable(value = "problemReportValue") Long problemReportValue){
        Optional<TicketProblemReport> problemReportList= ticketProblemReportRepository.findById(problemReportValue);

        if (problemReportList != null)
            return problemReportList.get();
        else
            throw new EntityValidationException("Problem Report Value Invalid","Problem Report Not Found");
    }
    
    @PreAuthorize("hasAuthority('Ticket_Problem_Reported_DELETE')")
    @DeleteMapping("/{problemReport}")
    public TicketProblemReport deleteClassification(@PathVariable(value = "problemReport") Long problemReport){

        Optional<TicketProblemReport> problemList= ticketProblemReportRepository.findById(problemReport);

        if(problemList== null)
        {
            throw new EntityNotFoundException("The Specified Problem deos not exists");
        }

        if (problemList.get().getIsDeleted()){
            throw new EntityNotFoundException("Problem Value already deleted");
        }
        else{
        	problemList.get().setIsDeleted(true);
            return ticketProblemReportRepository.save(problemList.get());
        }
    }

    
}