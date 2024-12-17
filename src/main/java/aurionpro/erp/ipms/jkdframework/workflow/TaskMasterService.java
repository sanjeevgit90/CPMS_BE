package aurionpro.erp.ipms.jkdframework.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@Service
public class TaskMasterService {

    @Autowired
    WorkflowRespository wfRepo;

    @Autowired
    StageRepository stageRepo;

    @Autowired
    TaskMasterRepository tmRepo;
    
    public Optional<TaskMaster> getTaskById(long taskId) {
    	return tmRepo.findById(taskId);
    }

    public TaskResponse CreateTask(TaskMaster tm) {

        if (tm.getWorkflowName() == null)
            throw new RuntimeException("Workflow not Specified");

        Optional<WorkflowMaster> wf = wfRepo.findById(tm.getWorkflowName());

        if (!wf.isPresent()) {
            throw new RuntimeException("Specified Workflow does not exist");
        }

        if(tm.getAssignToRole()==null){tm.setAssignToRole(wf.get().getAssignToRole());}
        if(tm.getAssignToUser()==null){tm.setAssignToUser(wf.get().getAssignToUser());}
        tm.setIsDeleted(false);
        tm.setStageName(wf.get().getStartStageName());
        tm.setWorkflowName(wf.get().getWorkflowName());
        tm.setApprovalStatus("PENDING");
       TaskMaster task = SaveTask(tm);
       
       TaskResponse response = new TaskResponse(task.getEntityId(), task.getAssignToRole(),
    		   task.getAssignToUser(), wf.get().getEntityStatus(), tm.getApprovalStatus());

        return response;
    }

    public TaskResponse ProcessTask(TaskMaster tMaster) {

        WorkflowStage wfStage=updatetask(tMaster);
        TaskMaster newTask =  new TaskMaster();
        TaskResponse response = null;

        // Create Task for Next Stage and save
        if (wfStage.getNextStageName() != null && wfStage.getNextStageName() != "") {
            TaskMaster tmNextStage;
            tmNextStage=GetNextStageTask(wfStage,tMaster);
            newTask = SaveTask(tmNextStage);
        }
        else
        {
        	newTask.setEntityId(null);
        	newTask.setAssignToRole(null);
        	newTask.setAssignToUser(null);
        }

        if(newTask!=null) {
        	response = new TaskResponse(newTask.getEntityId(), newTask.getAssignToRole(),
        			newTask.getAssignToUser(), wfStage.getEntityStatus(), tMaster.getApprovalStatus());
        }

         return response;
    }


    public TaskResponse ProcessTask(TaskMaster tMaster, String assignToUser, String assignToRole){

        WorkflowStage wfStage=updatetask(tMaster);
        TaskMaster newTask =  new TaskMaster();
        TaskResponse response = null;

        // Create Task for Next Stage and save
        if (wfStage.getNextStageName() != null && wfStage.getNextStageName() != "") {
            TaskMaster tmNextStage;
            tmNextStage=GetNextStageTask(wfStage,tMaster);
            //This will set the passed User as assign to user
            tmNextStage.setAssignToUser(assignToUser);
            tmNextStage.setAssignToRole(assignToRole);
            newTask = SaveTask(tmNextStage);
        }else
        {
        	newTask.setEntityId(null);
        	newTask.setAssignToRole(null);
        	newTask.setAssignToUser(null);
        }

        if(newTask!=null) {
        	response = new TaskResponse(newTask.getEntityId(), newTask.getAssignToRole(),
        			newTask.getAssignToUser(), wfStage.getEntityStatus(), tMaster.getApprovalStatus());
        }
        
         return response;
    }

    private WorkflowStage updatetask(TaskMaster tMaster){

        if(tMaster.getApprovalStatus()==null)
            throw new EntityValidationException("Task Invalid","Approval Status Cannot be Blank");

        Optional<TaskMaster> tmTemp = tmRepo.findById(tMaster.getEntityId());

        if (!tmTemp.isPresent())
            throw new RuntimeException("Invalid Task send for Processing");

        if (!(tmTemp.get().getApprovalStatus().equalsIgnoreCase("PENDING")))
            throw new RuntimeException("Task already Processed");

        if (!tmTemp.get().getWorkflowName().equalsIgnoreCase(tMaster.getWorkflowName())
                || !tmTemp.get().getStageName().equalsIgnoreCase(tMaster.getStageName()))
            throw new RuntimeException("Invalid Workflow or Stage Name");

        WorkflowStage wfStage = stageRepo.GetWorkflowStage(tmTemp.get().getStageName(), tMaster.getApprovalStatus(),
                tmTemp.get().getWorkflowName());
        if (wfStage == null)
            throw new RuntimeException("Invalid Workflow, Stage Name or Approval Status");
        
        //update Current Task
        tmRepo.save(tMaster);
        
        return wfStage;
    }

    private TaskMaster SaveTask(TaskMaster tMaster){

        List<String> errDetails=new ArrayList<String>();

        if(tMaster.getAssignToRole()==null && tMaster.getAssignToUser()==null)
            errDetails.add("Either Assign-to-Role or Assign-to-User must be set");

        if(tMaster.getEntityId()!=null)
            errDetails.add("Task with the specified Id already exists");

        if(errDetails.size()>0)
            throw new EntityValidationException("Invalid Task Entity", errDetails);

        return tmRepo.save(tMaster);
        
    }

    private TaskMaster GetNextStageTask(WorkflowStage wfStage, TaskMaster tMaster){

        TaskMaster tm;
        try {
            tm = (TaskMaster) tMaster.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

        tm.setWorkflowName(wfStage.getWorkflowName().getWorkflowName());
        tm.setStageName(wfStage.getNextStageName());
        tm.setAssignToRole(wfStage.getAssignToRole());
        tm.setAssignToUser(wfStage.getAssignToUser());
        tm.setIsDeleted(false);
        tm.setRemark(null);
        tm.setApprovalStatus("PENDING");
        tm.setEntityId(null);

        return tm;
    }
}