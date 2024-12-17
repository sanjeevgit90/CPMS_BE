package aurionpro.erp.ipms.jkdframework.workflow;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/workflow")
public class WorkflowController {

    @Autowired
    WorkflowRespository wfRepo;
    
    @Autowired
    WorkflowConfigRepository workflowConfigRepo;

    @GetMapping()
    public Iterable<WorkflowMaster> GetWorkflow(){
        return wfRepo.findAll();
    }

    @PostMapping()
    public WorkflowMaster CreateWorkflow(@RequestBody @Valid WorkflowMaster wfMaster){
        return wfRepo.save(wfMaster);
    }
    
    @PostMapping("/getworkflowlist")
    public List<SelectionList> getAllWorkflowList(@RequestBody WorkflowConfig request){
    	List<SelectionList> list = null;
    	if(StringUtils.isEmpty(request.getWorkflowType()))
    		throw new RuntimeException("Please enter workflow type.");
    	
    	if(StringUtils.isEmpty(request.getProjectType()) && StringUtils.isEmpty(request.getOrganisation()))
    		list = workflowConfigRepo.selectionWorkflowListByWorkflowType(request.getWorkflowType());
    	if(!StringUtils.isEmpty(request.getProjectType()) && !StringUtils.isEmpty(request.getOrganisation()))
    		list = workflowConfigRepo.selectionWorkflowListByProjectTypeAndOrg(request.getWorkflowType(), request.getProjectType(), request.getOrganisation());
    	if(!StringUtils.isEmpty(request.getProjectType()) && StringUtils.isEmpty(request.getOrganisation()))
    		list = workflowConfigRepo.selectionWorkflowListByProjectType(request.getWorkflowType(), request.getProjectType());
    	if(StringUtils.isEmpty(request.getProjectType()) && !StringUtils.isEmpty(request.getOrganisation()))
    		list = workflowConfigRepo.selectionWorkflowListByOrg(request.getWorkflowType(), request.getOrganisation());
		return list;
    }
}