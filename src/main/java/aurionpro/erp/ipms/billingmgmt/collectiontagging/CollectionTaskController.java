package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ipms/collectiontask")
public class CollectionTaskController {

    @Autowired
    private CollectionTaggingService collectionService;
    
    @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
    @GetMapping("/submitCollection/{id}")
    public CollectionTagging submitCollection(@PathVariable(value = "id") Long id){
		 return collectionService.submitCollection(id);
    }
    
	
    @PreAuthorize("hasAuthority('Collection_Task_VIEW')")
    @PostMapping("/taskByFilters")
    public Iterable<CollectionTaskView> taskByFilters(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody CollectionTaskView proj){
	      return collectionService.taskByFilters(proj, page, size);
    }
    
    @PreAuthorize("hasAuthority('Collection_Task_VIEW')")
    @PostMapping("/historytaskByFilters")
    public Iterable<CollectionTaskView> historytaskByFilters(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody CollectionTaskView proj){
	      return collectionService.historytaskByFilters(proj, page, size);
    }

    
    @PreAuthorize("hasAuthority('Collection_Task_VIEW')")
    @GetMapping("/{id}")
    public Optional<CollectionTaskMaster> gettaskById(@PathVariable(value = "id") Long id){
    	return collectionService.taskByid(id);
    }

    @PreAuthorize("hasAuthority('Collection_Task_EDIT')")
    @PutMapping()
    public CollectionTagging processInvoice(@RequestBody CollectionTaskMaster task){       
       return collectionService.processInvoice(task);
    }

    @PreAuthorize("hasAuthority('Collection_Task_VIEW')")
    @PostMapping("/getAllHistoryTasks")
    public List<CollectionTaskView> getAllHistoryTasks(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody CollectionTaskView request){
        return collectionService.getAllHistoryTasks(request, page, size);
    }
    
    @PreAuthorize("hasAuthority('Collection_Task_VIEW')")
    @GetMapping("/getHistoryById/{id}")
    public List<CollectionTaskView> getHistoryById(@PathVariable(value = "id") long id){
        return collectionService.getHistoryById(id);
    }
}