package aurionpro.erp.ipms.projectmgmt.projectmaster;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@RestController
@RequestMapping(value = "/ipms/projectApproval")
public class ProjectApprovalController {

    @Autowired
    private ProjectApprovalTaskRepository taskRepo;
    
    @Autowired
    private ProjectApprovalTaskViewRepository taskViewRepo;
    
	@Autowired
	TaskMasterService tmService;
	
	@Autowired
	ProjectService projectService;

    @Autowired
    NotificationMailFormat notificaton;

	@PreAuthorize("hasAuthority('Project_Approval_Task_VIEW')")
    @PostMapping("/taskByFilters")
    public Iterable<ProjectTaskView> getAllProjectsApproval(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody ProjectTaskView proj){
    	  proj.setWorkflowname("PROJECT_APPROVAL");
    	  proj.setApprovalStatus("PENDING");
	       ExampleMatcher em=ExampleMatcher.matching()
	                           .withIgnoreNullValues()
	                           .withIgnoreCase()
	                           .withStringMatcher(StringMatcher.CONTAINING);

	       Example<ProjectTaskView> proEx=Example.of(proj,em);
	      
	       List<ProjectTaskView> taskList ;
		      if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		    	{
		    		Pageable paging = PageRequest.of(page, size);
		    		taskList = taskViewRepo.findAll(proEx, paging).getContent();
		    	}
		    	else
		    	{
		    		taskList = taskViewRepo.findAll(proEx);
		    	}
		      Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		       
		       List<Long> taskId =taskViewRepo.getTaskList(profileId);
		       
		       List<ProjectTaskView> filteredTask = taskList.stream()  
		         	     .filter(f->taskId.contains(f.getTaskid())).collect(Collectors.toList());
		         
		          return filteredTask;
    }

    @PreAuthorize("hasAuthority('Project_Approval_Task_VIEW')")
    @GetMapping("/{id}")
    public Optional<ProjectApprovalTask> gettaskById(@PathVariable(value = "id") Long id){
        Optional<ProjectApprovalTask> taskObj = taskRepo.findById(id);
 		
 		if (taskObj == null)
     	{
     		new RuntimeException("Project Approval Task Not Found");
     	}
 		return taskObj;
    }

	@PreAuthorize("hasAuthority('Project_Approval_Task_EDIT')")
    @PutMapping()
    public ProjectApprovalTask updateProject(@RequestBody ProjectApprovalTask proj){
		Optional<ProjectApprovalTask> tcheck=taskRepo.findById(proj.getEntityId());

        if (!tcheck.isPresent()){
            throw new RuntimeException("Invalid Task Specified");
        }

        if(!tcheck.get().getProjectid().equals(proj.getProjectid()))
            throw new RuntimeException("Task Id does not match with the Project Name");

       TaskResponse task = tmService.ProcessTask(proj);
       
       projectService.updateApprovalStatus(proj.getProjectid(),task.getStatus());
       notificaton.sendNotification(task, proj.getProjectid(),
    		   "Project Approval", "Project Name :: " + proj.getProjectname(), null);

       
       return proj;
    }
	@PreAuthorize("hasAuthority('Project_Approval_Task_VIEW')")
    @PostMapping("/historyTaskByFilter")
    public Iterable<ProjectTaskView> historyTaskByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody ProjectTaskView proj){
    	  proj.setWorkflowname("PROJECT_APPROVAL");
    	  
    	  String profileId=MyPrincipal.getMyProfile().getUsername();
	       
    	  proj.setUpdatedby(profileId);
	       ExampleMatcher em=ExampleMatcher.matching()
	                           .withIgnoreNullValues()
	                           .withIgnoreCase()
	                           .withStringMatcher(StringMatcher.CONTAINING);

	       Example<ProjectTaskView> proEx=Example.of(proj,em);
	      List<ProjectTaskView> taskList ;
	      if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	    		Pageable paging = PageRequest.of(page, size);
	    		taskList = taskViewRepo.findAll(proEx, paging).getContent();
	    	}
	    	else
	    	{
	    		taskList = taskViewRepo.findAll(proEx);
	    	}
	     return taskList;
    }
	
	@PreAuthorize("hasAuthority('Project_Approval_Task_VIEW')")
    @GetMapping("/taskHistory/{id}")
    public List<ProjectApprovalTask> taskHistory(@PathVariable(value = "id") Long id){
        List<ProjectApprovalTask> taskObj = taskRepo.findByProjectid(id);
 		
 		if (taskObj == null)
     	{
     		new RuntimeException("Project Approval Task History Not Found");
     	}
 		return taskObj;
    }

}