package aurionpro.erp.ipms.ordermgmt.purchaseordertask;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.common.TaskCountList;
import aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping.ProjectWorkflowLevelMappingService;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/potask")
public class PoTaskMasterController {
	
	

	// Code for Approval Workflow
    @Autowired
    PoTaskMasterService poTaskMasterService;
    
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAnyAuthority('PO_Approval_Tasks_VIEW','MOB_POTask_VIEW')")   
	@PostMapping("/getpotasks")
    public List<PoTaskMaster> getAllPoTasks(@RequestBody PoTaskMaster taskRequest){
        return poTaskMasterService.getAllPoTasks(taskRequest);
    }
    
    @GetMapping("/getpotaskbyid/{taskId}")
    public PoTaskMaster getPoTaskById(@PathVariable(name = "taskId") long taskId){
        return poTaskMasterService.getPoTaskById(taskId);
    }
    
    @PreAuthorize("hasAnyAuthority('PO_Approval_Tasks_VIEW','MOB_POTask_VIEW')")   
	@PostMapping("/getpendingpotasksfromview")
    public PageImpl<AllTaskView> getPendingPoTasksFromView(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody AllTaskView taskRequest){
        return poTaskMasterService.getPendingPoTasksFromView(taskRequest, page,size);
    }
    
    @GetMapping("/getpotaskbyidview/{taskId}")
    public Optional<PoTaskView> getPoTaskByIdView(@PathVariable(name = "taskId") long taskId){
        return poTaskMasterService.getPoTaskByIdView(taskId);
    }
    
    @PreAuthorize("hasAnyAuthority('PO_Approval_Tasks_VIEW','MOB_POTask_VIEW')")   
	 @PostMapping("/gethistorypotasksfromview")
    public PageImpl<AllTaskView> getHistoryPoTasksFromView(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody AllTaskView taskRequest){
        return poTaskMasterService.getHistoryPoTasksFromView(taskRequest, page, size);
    }

    //@PostMapping("/sendPoForApprovalTypeA")
    @PostMapping("/sendPoForApproval")
    public PoTaskMaster sendPoForApprovalTypeA(@RequestBody PoTaskMaster request){
        return poTaskMasterService.sendPoForApproval(request);
    	/*PoTaskMaster tMaster=new PoTaskMaster();
        tMaster.setWorkflowName("PO_APPROVAL_TYPE_A");
        tMaster.setRemark("Started by Controller");
        tMaster.setPoId(poId);

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        
        String status = null;
        try {
        	status = tmService.CreateTask(tMaster);
        	poService.updateApprovalStatus(poId, status);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return status;*/
    }
    
    @PostMapping("/sendPoForApprovalTypeB")
    public PoTaskMaster sendPoForApprovalTypeB(@RequestBody PoTaskMaster request){
    	return poTaskMasterService.sendPoForApprovalTypeB(request);
    	/*PoTaskMaster tMaster=new PoTaskMaster();
        tMaster.setWorkflowName("PO_APPROVAL_TYPE_B");
        tMaster.setRemark("Started by Controller");
        tMaster.setPoId(poId);

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        
        String status = null;
        try {
        	status = tmService.CreateTask(tMaster);
        	poService.updateApprovalStatus(poId, status);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return status;*/
    }
    
    @PostMapping("/sendPoForAmendment")
    public PoTaskMaster sendPoForAmendment(@RequestBody PoTaskMaster request){
    	return poTaskMasterService.sendPoForAmendment(request);
    	/*PoTaskMaster tMaster=new PoTaskMaster();
        tMaster.setWorkflowName("PO_AMENDMENT");
        tMaster.setRemark("Started by Controller");
        tMaster.setPoId(poId);

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        
        String status = null;
        status = tmService.CreateTask(tMaster);
        poService.updateApprovalStatus(poId, status);
        return status;*/
    }
    
    //cancellation of approved po that can be requested by po head
    @PostMapping("/sendPoForCancellation/{poId}")
    public PoTaskMaster sendPoForCancellation(@PathVariable(name = "poId") long poId){
    	return poTaskMasterService.sendPoForCancellation(poId);
    	/*PoTaskMaster tMaster=new PoTaskMaster();
        tMaster.setWorkflowName("PO_CANCELLATION");
        tMaster.setRemark("Started by Controller");
        tMaster.setPoId(poId);

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        String status = null;
        status = tmService.CreateTask(tMaster);
        poService.updateApprovalStatus(poId, status);
        return status;*/
    }
    
    @PostMapping("/processworkflow")
    public PoTaskMaster processWorkflow(@RequestBody PoTaskMaster request){
    	return poTaskMasterService.processWorkflow(request);
    	/*String msg = null;
        try {
        	msg = tmService.ProcessTask(request);
        	poService.updateApprovalStatus(request.getPoId(), msg);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    	
        return msg;*/
    }

    // Code for Approval Workflow Ends
    @Autowired
    private ProjectWorkflowLevelMappingService mappingService;
    //getting workflow history
    @GetMapping("/getpohistorybyid/{poId}")
    public List<PoTaskView> getPoHistoryById(@PathVariable(name = "poId") long poId){
    	List<PoTaskView> historyList = poTaskMasterService.getPoHistoryById(poId);
    	for(PoTaskView p : historyList) {
    		 List<SelectionList> emailList = mappingService.getListOfEmailId( p.getStageName(), p.getAccountName()); 
    		 if (emailList != null) {
    			 List<String> emailIdList =new ArrayList<String>();
    			 for(SelectionList email: emailList)
    			 {
    				 emailIdList.add(email.getSelectionid());
    			 }
    			 p.setEmail(emailIdList);
    		 }
    		if(p.getStageName().equalsIgnoreCase("PO TEAM") || p.getStageName().equalsIgnoreCase("PO TEAM SCSOFT")) {
	        	if(!StringUtils.isEmpty(p.getRemark())) {
	        		String remark_link[] = p.getRemark().split(":");
		        	p.setRemark(remark_link[0]);
		        	if(remark_link.length>=2) {
		        		//p.setUrl(messages.getProperty(Constant.IMG_UPLOAD_PATH_VIRTUAL.getValue())+ remark_link[1]);
		        		p.setUrl(remark_link[1]);
		        	}
	        	}
	        }
    	}
        return historyList;
    }
    
    @GetMapping("/getPoTaskCount")
    public List<TaskCountList> getPoTaskCount(){
        return poTaskMasterService.getPoTaskCount();
    }
    
    @GetMapping("/getTaskCount")
    public List<TaskCountList> getTaskCount(){
        return poTaskMasterService.getTaskCount();
    }
    
    @PostMapping("/uploadtaskattachment/{folderName}")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
    	String subFolder = "Purchase Order/Task Attachments/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
}
