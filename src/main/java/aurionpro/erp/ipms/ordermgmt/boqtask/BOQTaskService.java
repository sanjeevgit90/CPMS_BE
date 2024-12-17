package aurionpro.erp.ipms.ordermgmt.boqtask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.ordermgmt.boq.BOQ;
import aurionpro.erp.ipms.ordermgmt.boq.BOQService;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@Service
public class BOQTaskService {
	
	@Autowired
	BOQService boqService;
	
	@Autowired
	TaskMasterService tmService;
	
	@Autowired
	BOQTaskRepository boqTaskRepo;
	
	@Autowired
    NotificationMailFormat notificaton;

	public TaskResponse sendPrsForApproval(long boqId) {
		BOQTask tMaster = new BOQTask();
		tMaster.setWorkflowName("BOQ_APPROVAL");
		tMaster.setRemark("");
		tMaster.setBoqId(boqId);
		
		TaskResponse task = null;
		try {
			task = tmService.CreateTask(tMaster);
			boqService.changeStatusById(boqId, task.getStatus());
			BOQ boq = boqService.getSingleBOQRecord(boqId).get();
			notificaton.sendNotification(task, boq.getAccountId(), "BOQ Approval", "BOQ No :: " + boq.getBoqNo(), "BOQ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

	public TaskResponse processWorkflow(BOQTask request) {
		TaskResponse task = null;
		try {
			task = tmService.ProcessTask(request);
			boqService.changeStatusById(request.getBoqId(), task.getStatus());
			BOQ boq = boqService.getSingleBOQRecord(request.getBoqId()).get();
			notificaton.sendNotification(task, boq.getAccountId(), "BOQ Approval", "BOQ No :: " + boq.getBoqNo(), "BOQ");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

	public BOQTask getBoqTaskById(long taskId) {
		return boqTaskRepo.findById(taskId).get();
	}

	public List<BOQTask> getAllBoqTasks() {
		return boqTaskRepo.findAll();
	}

	public List<BOQTask> getAllBoqHistoryTasks() {
		return boqTaskRepo.getAllBoqHistoryTasks();
	}

	public List<BOQTask> getBoqTaskHistoryById(long boqId) {
		return boqTaskRepo.getBoqTaskHistoryById(boqId);
	}
}
