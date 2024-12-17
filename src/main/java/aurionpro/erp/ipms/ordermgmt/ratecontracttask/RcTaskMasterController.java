package aurionpro.erp.ipms.ordermgmt.ratecontracttask;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.common.TaskCountList;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/rctask")
public class RcTaskMasterController {

	// Code for Approval Workflow
    @Autowired
    RcTaskMasterService rcTaskMasterService;
    
    @Autowired
    FileUploadService uploadService;

    @PostMapping("/getrctasks")
    public List<RcTaskMaster> getAllRcTasks(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody RcTaskMaster taskRequest){
        return rcTaskMasterService.getAllRcTasks(taskRequest, page, size);
    }
    
    @GetMapping("/getrctaskbyid/{taskId}")
    public RcTaskMaster getRcTaskById(@PathVariable(name = "taskId") long taskId){
        return rcTaskMasterService.getRcTaskById(taskId);
    }
    
    @PostMapping("/getpendingrctasksfromview")
    public List<RcTaskView> getPendingRcTasksFromView(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody RcTaskView taskRequest){
        return rcTaskMasterService.getPendingRcTasksFromView(taskRequest,page,size);
    }
    
    @GetMapping("/getrctaskbyidview/{taskId}")
    public Optional<RcTaskView> getRcTaskByIdView(@PathVariable(name = "taskId") long taskId){
        return rcTaskMasterService.getRcTaskByIdView(taskId);
    }
    
    @PostMapping("/gethistoryrctasksfromview")
    public PageImpl<RcTaskView> getHistoryRcTasksFromView(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody RcTaskView taskRequest){
        return rcTaskMasterService.getHistoryRcTasksFromView(taskRequest, page, size);
    }
    
    //getting workflow history
    @GetMapping("/getrchistorybyid/{rcId}")
    public List<RcTaskView> getRcHistoryById(@PathVariable(name = "rcId") long rcId){
        return rcTaskMasterService.getRcHistoryById(rcId);
    }
    
    //save approval task for rate contract
    @PostMapping("/sendRcForApproval")
    public RcTaskMaster sendRcForApproval(@RequestBody RcTaskMaster request){
        return rcTaskMasterService.sendRcForApproval(request);
    }

    @PostMapping("/processworkflow")
    public RcTaskMaster processWorkflow(@RequestBody RcTaskMaster request){
    	return rcTaskMasterService.processWorkflow(request);
    }
    
    @GetMapping("/getRCTaskCount")
    public List<TaskCountList> getRCTaskCount(){
        return rcTaskMasterService.getRCTaskCount();
    }
    // Code for Approval Workflow Ends
    
    @PostMapping("/uploadtaskattachment/{folderName}")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
    	String subFolder = "Rate Contract/Task Attachments/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
}
