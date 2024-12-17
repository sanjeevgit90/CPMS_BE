package aurionpro.erp.ipms.ordermgmt.prstask;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.ordermgmt.prs.Prs;
import aurionpro.erp.ipms.ordermgmt.prs.PrsService;
import aurionpro.erp.ipms.projectmgmt.projectmaster.Project;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectApprovalTask;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@Service
public class PrsTaskService {

	@Autowired
	PrsService prsService;

	@Autowired
	TaskMasterService tmService;

	@Autowired
	PrsTaskRepository prsTaskRepository;

	@Autowired
	PrsViewTaskRepository prsTaskViewRepo;
	
	 @Autowired
	 NotificationMailFormat notificaton;
	 
	public PageImpl<PrsTaskView> getAllPrsTasks(PrsTaskView request, Integer page, Integer size) {
		request.setApprovalStatus("PENDING");
		ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

    	Example<PrsTaskView> taskEx=Example.of(request,em);
    	
    	List<PrsTaskView> prsTaskList =  prsTaskViewRepo.findAll(taskEx);
	   
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = getTask(profileId);
	       
	       List<PrsTaskView> filteredData = prsTaskList.stream()  
	         	     .filter(f->projectId.contains(f.getTaskid())).collect(Collectors.toList());
	         
	       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	       {
			 Pageable paging = PageRequest.of(page-1, size);
			 	int start =  (int) paging.getOffset();
	          int end = Math.min((start + paging.getPageSize()), filteredData.size());
	    		
	          return new PageImpl<PrsTaskView>(filteredData.subList(start, end), paging, filteredData.size());
	          
	   		}
	   		else
	   		{
	   			return new PageImpl<PrsTaskView>(filteredData);
	   		}
	}

	private List<Long> getTask(Long profileId) {
		return prsTaskRepository.getTask(profileId);
	}

	public PrsTask getPrsTaskById(long taskId) {
		return prsTaskRepository.findById(taskId).get();
	}

	public PrsTask sendPrsForApproval(long prsId) {
		
       List<PrsTask> tcheck = prsTaskRepository.findByPrsId(prsId);
	   if (tcheck.size()>0){
	            throw new RuntimeException("Workflow for the PRS has already been initiated");
	    } 
	        
		PrsTask tMaster = new PrsTask();
		tMaster.setWorkflowName("PRS_APPROVAL");
		tMaster.setRemark("");
		tMaster.setPrsId(prsId);

		TaskResponse task = null;
		try {
			task = tmService.CreateTask(tMaster);
			task.setAction("APPROVED");//set for notification
			prsService.changeStatusById(prsId, task.getStatus(), null);
			
			Prs prs = prsService.getPrsById(prsId).get();
			notificaton.sendNotification(task,prs.getProjectName(), 
					"PRS Approval", "PRS No :: " + prs.getPrsNo(), "PRS");
		} catch (Exception e) {
			e.printStackTrace();
			//throw new EntityExistsException("Task not submitted");
		}
		return tMaster;
	}

	public PrsTask processWorkflow(PrsTask request) {
		TaskResponse task = null;
		try {
			task = tmService.ProcessTask(request);
			String approvedBy = MyPrincipal.getMyProfile().getFirstName() + " " + MyPrincipal.getMyProfile().getLastName();
			if(task == null) {
				prsService.changeStatusById(request.getPrsId(), "APPROVED", approvedBy);
	        	request.setStageName("APPROVED");//setting new stage name in response
			} else {
				prsService.changeStatusById(request.getPrsId(), task.getStatus(), approvedBy);
	        	request.setStageName(task.getStatus());//setting new stage name in response
			}
        	
			Prs prs = prsService.getPrsById(request.getPrsId()).get();
			/*notificaton.sendNotification(task,prs.getProjectName(), 
					"PRS Approval", "PRS No :: " + prs.getPrsNo(), "PRS");*/
			
			//for sending notification
        	List<String> emailList = null;
        	if(prs != null) {
        		if(prs.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
        			emailList = prsTaskViewRepo.getAllEmailListFromPrsTask(prs.getEntityId());
        			if(emailList != null) {
        				notificaton.sendNotificationToAllLevel(emailList, "PRS No :: " + prs.getPrsNo());
        			}
        		} else {
        			notificaton.sendNotification(task, prs.getProjectName(), "PRS Approval", "PRS No :: " + prs.getPrsNo(), "PRS");
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
			//throw new EntityExistsException("Task not submitted");
		}
		return request;
	}

	public PageImpl<PrsTaskView> getAllPrsHistoryTasks(PrsTaskView request, Integer page, Integer size) {
		request.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
		ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

    	Example<PrsTaskView> taskEx=Example.of(request,em);
    	
    	List<PrsTaskView> prsTaskList =  prsTaskViewRepo.findAll(taskEx);
	   
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = getTask(profileId);
	       
	       List<PrsTaskView> filteredData = prsTaskList.stream()  
	         	     .filter(f->projectId.contains(f.getTaskid())).collect(Collectors.toList());
	         
	       List<PrsTaskView> historyTask = filteredData.stream().filter(f->!(f.getApprovalStatus().startsWith("PENDING"))).collect(Collectors.toList());
	       
	       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	       {
			 Pageable paging = PageRequest.of(page-1, size);
			 	int start =  (int) paging.getOffset();
	          int end = Math.min((start + paging.getPageSize()), historyTask.size());
	    		
	          return new PageImpl<PrsTaskView>(historyTask.subList(start, end), paging, filteredData.size());
	          
	   		}
	   		else
	   		{
	   			return new PageImpl<PrsTaskView>(historyTask);
	   		}
	}

	public List<PrsTaskView> getPrsTaskHistoryById(long prsId) {
		//return prsTaskRepository.findByPrsId(prsId);
		return prsTaskViewRepo.getPrsTaskHistoryById(prsId);
	}

}
