package aurionpro.erp.ipms.authorization.menu;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;

public class TaskExampleController {

    // Code for Approval Workflow
    @Autowired
    MenuTaskRepository mtRepo;

    @GetMapping("/tasks")
    public Iterable<MenuTaskMaster> GetTasks(){
        return mtRepo.findAll();
    }

    @Autowired
    TaskMasterService tmService;

    @PostMapping("/startworkflow/{name}")
    public String StartWorkflow(@PathVariable(name = "name") String workflowName){
        
        List<MenuTaskMaster> tcheck=mtRepo.findByMenuname("PO Menu");
        if (tcheck.size()>0){
            throw new RuntimeException("Workflow for the Entity has already been initiated");
        }

        MenuTaskMaster tMaster=new MenuTaskMaster();
        tMaster.setWorkflowName(workflowName);
        tMaster.setRemark("Started by Controller");
        tMaster.setMenuname("PO Menu");

        return tmService.CreateTask(tMaster).getStatus();
    }

    @PostMapping("/processworkflow")
    public String processWorkflow(@RequestBody MenuTaskMaster mtMaster){
        
        Optional<MenuTaskMaster> tcheck=mtRepo.findById(mtMaster.getEntityId());

        if (!tcheck.isPresent()){
            throw new RuntimeException("Invalid Task Specified");
        }

        if(!tcheck.get().getMenuname().equals(mtMaster.getMenuname()))
            throw new RuntimeException("Task Id does not match with the Entity Name");

       return tmService.ProcessTask(mtMaster).getStatus();
    }

    @PostMapping("/processworkflow/{assignName}")
    public String processWorkflow(@RequestBody MenuTaskMaster mtMaster, 
                    @PathVariable(value = "assignName") String assignName){
        
        Optional<MenuTaskMaster> tcheck=mtRepo.findById(mtMaster.getEntityId());

        if (!tcheck.isPresent()){
            throw new RuntimeException("Invalid Task Specified");
        }

        if(!tcheck.get().getMenuname().equals(mtMaster.getMenuname()))
            throw new RuntimeException("Task Id does not match with the Entity Name");

      return tmService.ProcessTask(mtMaster, assignName, null).getStatus();
       // return tmService.ProcessTask(mtMaster, assignName, null);
    }

    // Code for Approval Workflow Ends
}