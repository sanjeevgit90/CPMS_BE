package aurionpro.erp.ipms.projectmgmt.projectmaster;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAuthority('Project_Master_VIEW')")
    @PostMapping("/projectByFilters")
    public Iterable<ProjectView> getAllProjects(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody ProjectView proj){
        return projectService.findAll(proj, page, size);
    }

    @GetMapping("/selectionlist")
    public List<SelectionList> getProjectList(){
        return projectService.SelectionProjectList();
    }

    @GetMapping("getProjByName/{projectname}")
    public Project getProjByName(@PathVariable(value = "projectname") String projectName){
         return projectService.getProjByName(projectName);
    }
    
    //@PreAuthorize("hasAuthority('Project_Master_VIEW')")
    @GetMapping("getProjectById/{id}")
    public Optional<Project> getProjectById(@PathVariable(value = "id") Long id){
         return projectService.getProjectById(id);
    }

    @PreAuthorize("hasAuthority('Project_Master_ADD')")
    @PostMapping()
    public Project createProject(@RequestBody Project proj){
    	return projectService.createProject(proj);
    }
    
    @PreAuthorize("hasAuthority('Project_Master_ADD')")
    @GetMapping("getSubmitForApproval/{id}")
    public Project getSubmitForApproval(@PathVariable(value = "id") Long id){
         return projectService.getSubmitForApproval(id);
    }

    @PreAuthorize("hasAuthority('Project_Master_EDIT')")
    @PutMapping()
    public Project updateProject(@RequestBody Project proj){
    	return projectService.updateProject(proj);
    }

    @PreAuthorize("hasAuthority('Project_Master_DELETE')")
    @DeleteMapping("/{id}")
    public Project deleteProject(@PathVariable(value = "id") Long id){
      return projectService.deleteProject(id);
    }
    
   @PreAuthorize("hasAuthority('Project_Master_ADD')")
    @PostMapping("/uploadPoAttachment/{folderName}")
    public FileResponse uploadPoAttachment(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder="project/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
  
   @PreAuthorize("hasAuthority('Project_Master_ADD')")
    @PostMapping("/uploadPlanAttachment/{folderName}")
    public FileResponse uploadPlanAttachment(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
        String subFolder="project/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
   
   @PreAuthorize("hasAuthority('Project_Master_EDIT')")
   @GetMapping("/saveOpenBravoId")
   public Project saveOpenBravoId(@RequestParam("id") Long id, @RequestParam("project") String proj){
     return projectService.saveOpenBravoId(id,proj);
   }
   
	/*
	 * @PostMapping("/getProjectListByOrg") public List<ProjectList>
	 * getProjectListByOrg(@RequestBody List<Long> idList){ return
	 * projectService.getProjectListByOrg(idList); }
	 */

   @GetMapping("/getProjectListByOrg/{id}")
   public List<ProjectList> getProjectListByOrg(@PathVariable(value="id") Long id){
       return projectService.getProjectListByOrg(id);
   }
}