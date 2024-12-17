package aurionpro.erp.ipms.ordermgmt.grntask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ipms/grntask")
public class GrnTaskController {

	@Autowired
	private GrnTaskService grnTaskService;
	
	//@PreAuthorize("hasAnyAuthority('GRN_Master_VIEW','MOB_GRN_Master_VIEW')")
    @GetMapping("/sendGrnForApproval/{grnId}")
    public GrnTask sendGrnForApproval(@PathVariable(value = "grnId") long grnId){
        return grnTaskService.sendGrnForApproval(grnId);
    }
    
    //@PreAuthorize("hasAnyAuthority('GRN_Approval_Task_EDIT','MOB_GRN_Approval_EDIT')")
    @PostMapping("/processworkflow")
    public GrnTask processWorkflow(@RequestBody GrnTask request){
    	return grnTaskService.processWorkflow(request);
    }
    
    //@PreAuthorize("hasAnyAuthority('GRN_Approval_Task_VIEW','MOB_GRN_Approval_VIEW')")
    @PostMapping("/getallgrntasks")
    public List<GrnTaskView> getAllGrnTasks(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody GrnTaskView request){
        return grnTaskService.getAllGrnTasks(request, page, size);
    }
    
    //@PreAuthorize("hasAnyAuthority('GRN_Approval_Task_VIEW','MOB_GRN_Approval_VIEW')")
    @PostMapping("/getallgrnhistorytasks")
    public List<GrnTaskView> getAllGrnHistoryTasks(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody GrnTaskView request){
        return grnTaskService.getAllGrnHistoryTasks(request, page, size);
    }
    
    //@PreAuthorize("hasAnyAuthority('GRN_Approval_Task_VIEW','MOB_GRN_Approval_VIEW')")
    @GetMapping("/getgnrtaskbyid/{taskId}")
    public GrnTask getGrnTaskById(@PathVariable(name = "taskId") long taskId){
        return grnTaskService.getGrnTaskById(taskId);
    }
    
    //@PreAuthorize("hasAnyAuthority('GRN_Approval_Task_VIEW','MOB_GRN_Approval_VIEW')")
    @GetMapping("/getgrntaskviewbyid/{taskId}")
    public GrnTaskView getGrnTaskViewById(@PathVariable(name = "taskId") long taskId){
        return grnTaskService.getGrnTaskViewById(taskId);
    }
    
    //getting workflow history
    @GetMapping("/getworkflowhistorybyid/{grnId}")
    public List<GrnTaskView> getPoHistoryById(@PathVariable(name = "grnId") long grnId){
        return grnTaskService.getGrnWorkflowHistoryById(grnId);
    }
}
