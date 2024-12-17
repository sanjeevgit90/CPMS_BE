package aurionpro.erp.ipms.ordermgmt.ratecontracttask;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.jkdframework.common.TaskCountList;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractMaster;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractService;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@Service
public class RcTaskMasterService {

	@Autowired
    RcTaskMasterRepository rcTaskMasterRepository;
	
    @Autowired
    TaskMasterService tmService;
    
    @Autowired
    RcTaskViewRepository rcTaskViewRepository;
    
    @Autowired
    RateContractService rcService;
    
    

    @Autowired
    NotificationMailFormat notificaton;
    
    public List<RcTaskMaster> getAllRcTasks(RcTaskMaster taskRequest, Integer page, Integer size){
    	ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<RcTaskMaster> taskEx = Example.of(taskRequest, em);
       
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	    		Pageable paging = PageRequest.of(page, size);
	    		 return rcTaskMasterRepository.findAll(taskEx, paging).getContent();
	    	}
	    	else
	    	{
	    		 return rcTaskMasterRepository.findAll(taskEx);
	    	}
    }
    
    public RcTaskMaster getRcTaskById(long taskId){
        return (RcTaskMaster) tmService.getTaskById(taskId).get();
    }

    public RcTaskMaster processWorkflow(RcTaskMaster request){
    	TaskResponse task = null;
        try {
        	/*if(request.getStageName().equalsIgnoreCase("DELIVERY HEAD")) {
        		PurchaseOrderMaster po = poService.getOrderById(request.getPoId()).get();
        		Project proj = projectRepo.findById(po.getAccountName()).get();
        		//we need to write the code for assigning the task to delivery head user.
        		msg = tmService.ProcessTask(request, assignToUser, assignToRole);
        	} else {
        		msg = tmService.ProcessTask(request);
        	}*/
        	
        	task = tmService.ProcessTask(request);
        	if(task == null) {
        		rcService.updateApprovalStatus(request.getRcId(), "APPROVED");
            	request.setStageName("APPROVED");//setting new stage name in response
        	} else {
        		rcService.updateApprovalStatus(request.getRcId(), task.getStatus());
            	request.setStageName(task.getStatus());//setting new stage name in response
        	}
        	
        	RateContractMaster rc = rcService.getOrderById(request.getRcId()).get();
        	/*notificaton.sendNotification(task, rc.getAccountName(),
        			"RC Approval", "RC No :: " + rc.getRateContractNo(), "RC");*/
        	
        	//for sending notification
        	List<String> emailList = null;
        	if(rc != null) {
        		if(rc.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
        			emailList = rcTaskViewRepository.getAllEmailListFromRcTask(rc.getEntityId());
        			if(emailList != null) {
        				notificaton.sendNotificationToAllLevel(emailList, "RC No :: " + rc.getRateContractNo());
        			}
        		} else {
        			notificaton.sendNotification(task, rc.getAccountName(), "RC Approval", "RC No :: " + rc.getRateContractNo(), "RC");
        		}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
    	
        return request;
    }

	public List<RcTaskView> getPendingRcTasksFromView(RcTaskView taskRequest, Integer page, Integer size) {
		taskRequest.setApprovalStatus("PENDING");
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<RcTaskView> taskEx = Example.of(taskRequest, em);
       
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	    		Pageable paging = PageRequest.of(page, size);
	    		return rcTaskViewRepository.findAll(taskEx, paging).getContent();
	    	}
	    	else
	    	{
	    		return rcTaskViewRepository.findAll(taskEx);
	    	}
	}

	public Optional<RcTaskView> getRcTaskByIdView(long taskId) {
		return rcTaskViewRepository.findById(taskId);
		//return (PoTaskView) tmService.getTaskById(taskId).get();
	}

	public PageImpl<RcTaskView> getHistoryRcTasksFromView(RcTaskView taskRequest, Integer page, Integer size) {
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<RcTaskView> taskEx = Example.of(taskRequest, em);
        //return poTaskViewRepository.findAll(taskEx);
        List<RcTaskView> taskList = rcTaskViewRepository.findAll(taskEx);
        //List<CollectionTaskView> taskList= taskViewRepo.findAll(collectioneEx);
        List<RcTaskView> historyTask = taskList.stream().filter(f->!(f.getApprovalStatus().startsWith("PENDING"))).collect(Collectors.toList());
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		{
			Pageable paging = PageRequest.of(page-1, size);
		    int start =  (int) paging.getOffset();
		    int end = Math.min((start + paging.getPageSize()), historyTask.size());
		     		
		    return new PageImpl<RcTaskView>(historyTask.subList(start, end), paging, historyTask.size());
		      
		}
		else
		{
		    return new PageImpl<RcTaskView>(historyTask);
	   	}
	}

	public RcTaskMaster sendRcForApproval(RcTaskMaster request) {
		
		List<RcTaskMaster> tcheck = rcTaskMasterRepository.findByRcId(request.getRcId());
		
		if (tcheck.size()>0){
		            throw new RuntimeException("Workflow for the RC has already been initiated");
		    } 
		        
		RcTaskMaster tMaster=new RcTaskMaster();
        tMaster.setWorkflowName("RC_APPROVAL");
        tMaster.setRemark(null);
        tMaster.setRcId(request.getRcId());
        tMaster.setPoRcFlag(request.getPoRcFlag());

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        
        TaskResponse task = null;
        try {
        	task = tmService.CreateTask(tMaster);
        	task.setAction("APPROVED");//set for notification
        	rcService.updateApprovalStatus(request.getRcId(), task.getStatus());
        	RateContractMaster rc = rcService.getOrderById(request.getRcId()).get();
        	
        	notificaton.sendNotification(task, rc.getAccountName(),
        			"RC Approval", "RC No :: " + rc.getRateContractNo(), "RC");

        } catch (Exception e) {
        	e.printStackTrace();
        }
        return tMaster;
	}
	
	public List<RcTaskView> getRcHistoryById(long rcId) {
		return rcTaskViewRepository.findByRcId(rcId);
	}

	public List<TaskCountList> getRCTaskCount() {
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		return rcTaskViewRepository.getRCTaskCount(profileId);
		 
		/*BigInteger count = rcTaskViewRepository.getRCTaskCount(profileId);
		DashboardCount dt = new DashboardCount();
		dt.setName("RC Task Count");
		dt.setCount(count);
		return dt;*/
	}
}
