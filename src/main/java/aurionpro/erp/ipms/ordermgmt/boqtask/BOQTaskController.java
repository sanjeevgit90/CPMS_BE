package aurionpro.erp.ipms.ordermgmt.boqtask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;

@RestController
@RequestMapping(value = "/ipms/boqtask")
public class BOQTaskController {

	@Autowired
	BOQTaskService boqTaskService;

	 @PreAuthorize("hasAuthority('BOQ_Task_VIEW')")
		@GetMapping("/getboqtasks")
    public List<BOQTask> getAllBoqTasks(){
        return boqTaskService.getAllBoqTasks();
    }
    
    @GetMapping("/getboqtaskbyid/{taskId}")
    public BOQTask getBoqTaskById(@PathVariable(value = "taskId") long taskId){
        return boqTaskService.getBoqTaskById(taskId);
    }

    @PostMapping("/sendBoqForApproval/{boqId}")
    public TaskResponse sendPrsForApproval(@PathVariable(value = "boqId") long boqId){
        return boqTaskService.sendPrsForApproval(boqId);
    }
    
    @PreAuthorize("hasAuthority('BOQ_Task_EDIT')")
	@PostMapping("/processworkflow")
    public TaskResponse processWorkflow(@RequestBody BOQTask request){
    	return boqTaskService.processWorkflow(request);
    }
    
    @PreAuthorize("hasAuthority('BOQ_Task_VIEW')")
	@GetMapping("/getallboqhistorytasks")
    public List<BOQTask> getAllBoqHistoryTasks(){
        return boqTaskService.getAllBoqHistoryTasks();
    }
    
    @GetMapping("/getboqtaskhistorybyid/{boqId}")
    public List<BOQTask> getBoqTaskHistoryById(@PathVariable(value = "boqId") long boqId){
        return boqTaskService.getBoqTaskHistoryById(boqId);
    }
}