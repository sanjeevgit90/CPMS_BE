package aurionpro.erp.ipms.projectmgmt.projecttypemaster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/projecttype")
public class ProjectTypeMasterController {

    @Autowired
    private ProjectTypeMasterRepository projectRepo;


    @GetMapping("/selectionlist")
    public List<SelectionList> getProjectList(){
        return projectRepo.SelectionProjectList();
    }

/*
    @PostMapping("/projectByFilters")
    public Iterable<ProjectTypeMaster> getAllProjects(@RequestBody ProjectTypeMaster proj){
        return projectRepo.findAll(proj);
    }
    
    @GetMapping("/{projectname}")
    public ProjectTypeMaster getProjByName(@PathVariable(value = "projectname") String projectName){
         return projectRepo.getProjByName(projectName);
    }
    
    @GetMapping("/{id}")
    public Optional<ProjectTypeMaster> getProjectById(@PathVariable(value = "id") Long id){
         return projectRepo.getProjectById(id);
    }

    @PostMapping()
    public ProjectTypeMaster createProject(@RequestBody ProjectTypeMaster proj){
    	return projectRepo.createProject(proj);
    }

    @PutMapping()
    public ProjectTypeMaster updateProject(@RequestBody ProjectTypeMaster proj){
    	return projectRepo.updateProject(proj);
    }

    @DeleteMapping("/{id}")
    public ProjectTypeMaster deleteProject(@PathVariable(value = "id") Long id){
      return projectRepo.deleteProject(id);
    }
    */

}