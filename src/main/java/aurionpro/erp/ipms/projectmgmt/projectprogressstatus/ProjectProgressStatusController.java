package aurionpro.erp.ipms.projectmgmt.projectprogressstatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/progressstatus")
public class ProjectProgressStatusController {

    @Autowired
    private ProjectProgressStatusRepository projectService;

  
    @GetMapping("/selectionlist")
    public List<SelectionList> getStatusList(){
        return projectService.SelectionStatustList();
    }
   /* 
    @PostMapping("/projectByFilters")
    public Iterable<ProjectProgressStatus> getAllProjects(@RequestBody ProjectProgressStatus proj){
        return projectService.findAll(proj);
    }


    @GetMapping("/{projectname}")
    public ProjectProgressStatus getProjByName(@PathVariable(value = "projectname") String projectName){
         return projectService.getProjByName(projectName);
    }
    
    @GetMapping("/{id}")
    public Optional<ProjectProgressStatus> getProjectById(@PathVariable(value = "id") Long id){
         return projectService.getProjectById(id);
    }

    @PostMapping()
    public ProjectProgressStatus createProject(@RequestBody ProjectProgressStatus proj){
    	return projectService.createProject(proj);
    }

    @PutMapping()
    public ProjectProgressStatus updateProject(@RequestBody ProjectProgressStatus proj){
    	return projectService.updateProject(proj);
    }

    @DeleteMapping("/{id}")
    public ProjectProgressStatus deleteProject(@PathVariable(value = "id") Long id){
      return projectService.deleteProject(id);
    }
    */

}