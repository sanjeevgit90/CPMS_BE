package aurionpro.erp.ipms.ordermgmt.prstask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ipms/prstask")
public class PrsTaskController {

	// Code for Approval Workflow
    @Autowired
    PrsTaskService prsTaskService;

    @PreAuthorize("hasAnyAuthority('PRS_Approval_Task_VIEW','MOB_PRS_Approval_VIEW')")
    @PostMapping("/getpotasks")
    public PageImpl<PrsTaskView> getAllPrsTasks(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody PrsTaskView request){
        return prsTaskService.getAllPrsTasks(request, page, size);
    }
    
    @PreAuthorize("hasAnyAuthority('PRS_Approval_Task_VIEW','MOB_PRS_Approval_VIEW')")
    @GetMapping("/getpotaskbyid/{taskId}")
    public PrsTask getPrsTaskById(@PathVariable(value = "taskId") long taskId){
        return prsTaskService.getPrsTaskById(taskId);
    }

    @PreAuthorize("hasAnyAuthority('PRS_Master_VIEW','MOB_PRS_Master_VIEW')")
    @GetMapping("/sendPrsForApproval/{prsId}")
    public PrsTask sendPrsForApproval(@PathVariable(value = "prsId") long prsId){
        return prsTaskService.sendPrsForApproval(prsId);
    	
    }
    
    @PreAuthorize("hasAnyAuthority('PRS_Approval_Task_EDIT','MOB_PRS_Approval_EDIT')")
    @PostMapping("/processworkflow")
    public PrsTask processWorkflow(@RequestBody PrsTask request){
    	return prsTaskService.processWorkflow(request);
    	
    }
    
    @PreAuthorize("hasAnyAuthority('PRS_Approval_Task_VIEW','MOB_PRS_Approval_VIEW')")
    @PostMapping("/getallprshistorytasks")
    public PageImpl<PrsTaskView> getAllPrsHistoryTasks(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody PrsTaskView request){
        return prsTaskService.getAllPrsHistoryTasks(request, page, size);
    }
    
    @PreAuthorize("hasAnyAuthority('PRS_Approval_Task_VIEW', 'PRS_Master_VIEW','MOB_PRS_Master_VIEW' ,'MOB_PRS_Approval_VIEW')")
    @GetMapping("/getprstaskhistorybyid/{prsId}")
    public List<PrsTaskView> getPrsTaskHistoryById(@PathVariable(value = "prsId") long prsId){
        return prsTaskService.getPrsTaskHistoryById(prsId);
    }
    // Code for Approval Workflow Ends
}
