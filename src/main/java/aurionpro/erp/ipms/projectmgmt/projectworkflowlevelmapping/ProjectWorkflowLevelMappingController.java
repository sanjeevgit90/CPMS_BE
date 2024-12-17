package aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.utility.MyPrincipal;

@RestController
@RequestMapping(value = "/ipms/projectmapping")
public class ProjectWorkflowLevelMappingController {

    @Autowired
    private ProjectWorkflowLevelMappingService projectService;


    @GetMapping("/mappingByProject/{project}")
    public Iterable<ProjectWorkflowLevelMapping> mappingByProject(@PathVariable(value = "project") Long project){
        return projectService.mappingByProject(project);
    }
    
    @PreAuthorize("hasAuthority('Project_Master_ADD')")
    @PostMapping("/{projectid}/{org}")
    public List<ProjectWorkflowLevelMapping> createMapping(@PathVariable(value = "projectid") Long projectid,
			@PathVariable(value = "org") Long org, @RequestBody List<ProjectWorkflowLevelMapping> proj){
	  return projectService.createMapping(proj, projectid, org);
	
    }

    @PreAuthorize("hasAuthority('Project_Master_EDIT')")
    @PutMapping("/{projectid}/{org}")
    public List<ProjectWorkflowLevelMapping> updateMapping(@PathVariable(value = "projectid") Long projectid,
			@PathVariable(value = "org") Long org, @RequestBody List<ProjectWorkflowLevelMapping> proj){
    	
	   	return projectService.updateMapping(proj, projectid,org);	
    }

   // @PreAuthorize("hasAuthority('Project_Master_DELETE')")
    @DeleteMapping("/{id}")
    public ProjectWorkflowLevelMapping deleteProject(@PathVariable(value = "id") Long id){
    	 return projectService.deleteMapping(id);
    }
    
    @GetMapping("/getAllLevels")
    public List<ProjectWorkflowLevelMapping> getAllLevels(@RequestParam(name = "project") Long project){
    	return projectService.getAllLevels(project);
    }
    
    @GetMapping("/getDefaultAllLevels")
    public List<ProjectWorkflowLevelMapping> getDefaultAllLevels(@RequestParam(name = "project") Long project){
    	return projectService.getDefaultAllLevels(project);
    }

    @GetMapping("/getAllLevelsOfUserById")
    public List<String> getAllLevelsOfUserById(){
    	Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
   		return projectService.getAllLevelsOfUserById(profileId);
    }
    
    @GetMapping("/getAllLevelsByProjectId/{id}/{user}")
    public List<ProjectWorkflowLevelMapping> getAllLevelsByProjectId(@PathVariable(name = "id") Long id, @PathVariable(name = "user") Long user){
    	return projectService.getAllLevelsByProjectId(id, user);
    }
    
    @PostMapping("/getAllProjectsOfUsers/{user}")
    public PageImpl<ProjectWorkflowLevelMappingView> getAllProjectsOfUsers(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @PathVariable(name = "user") Long user, @RequestBody ProjectWorkflowLevelMapping proj){
   		return projectService.getAllProjectsOfUsers(proj,user, page, size);
    }
    
    @PostMapping("/saveUsersProjects")
    public List<ProjectWorkflowLevelMapping> saveUsersProjects(@RequestBody List<ProjectWorkflowLevelMapping> proj){
	  return projectService.saveUsersProjects(proj);
	
    }
    
    @GetMapping("/saveAllProjects/{org}/{user}/{level}")
    public Boolean saveAllProjects(@PathVariable(name = "org") Long org, @RequestBody @PathVariable(name = "user") Long user, @RequestBody @PathVariable(name = "level") String level){
	  return projectService.saveAllProjects(org, user, level);
	
    }
    
    @GetMapping("/getAllLevelsByOrg/{org}")
    public List<ProjectWorkflowLevelMapping> getAllLevelsByOrg(@PathVariable(name = "org") Long org){
	  return projectService.getAllLevelsByOrg(org);
	
    }
}