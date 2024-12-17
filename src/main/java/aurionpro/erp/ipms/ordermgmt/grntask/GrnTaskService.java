package aurionpro.erp.ipms.ordermgmt.grntask;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.ordermgmt.grn.GRNMasterService;
import aurionpro.erp.ipms.ordermgmt.prstask.PrsTask;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@Service
public class GrnTaskService {

	@Autowired
	TaskMasterService tmService;
	
	@Autowired
	GRNMasterService grnMasterService;
	
	@Autowired
	GrnTaskRepository grnTaskRepo;
	
	@Autowired
	GrnTaskViewRepository grnTaskViewRepo;
	
	@Autowired
	NotificationMailFormat notificaton;

	public GrnTask sendGrnForApproval(long grnId) {
		 List<GrnTask> tcheck = grnTaskRepo.findByGrnId(grnId);
		   if (tcheck.size()>0){
		            throw new RuntimeException("Workflow for the GRN has already been initiated");
		    } 
		   
		GrnTask tMaster = new GrnTask();
		tMaster.setWorkflowName("GRN_APPROVAL");
		tMaster.setRemark("");
		tMaster.setGrnId(grnId);

		TaskResponse task = null;
		try {
			task = tmService.CreateTask(tMaster);
			task.setAction("APPROVED");//set for notification
			grnMasterService.changeStatusById(grnId, task.getStatus());
			
			//GRNMasterView grn = grnMasterService.getGrnById(grnId).get();
			//notificaton.sendNotification(task, grn.getProjectName(), "GRN Approval", "GRN No :: " + grn.getGrnNumber(), "GRN");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tMaster;
	}

	public GrnTask processWorkflow(GrnTask request) {
		TaskResponse task = null;
		try {
			task = tmService.ProcessTask(request);
			if(task == null) {
				grnMasterService.changeStatusById(request.getGrnId(), "APPROVED");
	        	request.setStageName("APPROVED");//setting new stage name in response
			} else {
				grnMasterService.changeStatusById(request.getGrnId(), task.getStatus());
	        	request.setStageName(task.getStatus());//setting new stage name in response
			}
        	
			//GRNMasterView grn = grnMasterService.getGrnById(request.getGrnId()).get();
			//notificaton.sendNotification(task, grn.getProjectName(), "GRN Approval", "GRN No :: " + grn.getGrnNumber(), "GRN");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}
	
	public GrnTask getGrnTaskById(long taskId) {
		return grnTaskRepo.findById(taskId).get();
	}
	
	public GrnTaskView getGrnTaskViewById(long taskId) {
		return grnTaskViewRepo.findById(taskId).get();
	}

	public List<GrnTaskView> getAllGrnTasks(GrnTaskView request, Integer page, Integer size) {
		request.setApprovalStatus("PENDING");
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);

		Example<GrnTaskView> taskEx = Example.of(request, em);

		List<GrnTaskView> grnTaskList;
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
			Pageable paging = PageRequest.of(page, size);
			grnTaskList = grnTaskViewRepo.findAll(taskEx, paging).getContent();
		} else {
			grnTaskList = grnTaskViewRepo.findAll(taskEx);
		}

		Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		List<Long> projectId = getTask(profileId);
		List<GrnTaskView> filteredData = grnTaskList.stream().filter(f -> projectId.contains(f.getEntityId())).collect(Collectors.toList());

		return filteredData;
	}
	
	public List<GrnTaskView> getAllGrnHistoryTasks(GrnTaskView request, Integer page, Integer size) {
		request.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);

		Example<GrnTaskView> taskEx = Example.of(request, em);

		List<GrnTaskView> grnTaskList;
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
			Pageable paging = PageRequest.of(page, size);
			grnTaskList = grnTaskViewRepo.findAll(taskEx, paging).getContent();
		} else {
			grnTaskList = grnTaskViewRepo.findAll(taskEx);
		}

		Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		List<Long> projectId = getTask(profileId);
		List<GrnTaskView> filteredData = grnTaskList.stream().filter(f -> projectId.contains(f.getEntityId())).collect(Collectors.toList());
		
		List<GrnTaskView> grnHistoryTasks = filteredData.stream().filter(f->!(f.getApprovalStatus().startsWith("PENDING"))).collect(Collectors.toList());

		return grnHistoryTasks;
	}

	public List<GrnTaskView> getGrnWorkflowHistoryById(long grnId) {
		return grnTaskViewRepo.findByGrnId(grnId);
	}
	
	private List<Long> getTask(Long profileId) {
		return grnTaskViewRepo.getTask(profileId);
	}
}
