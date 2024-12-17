package aurionpro.erp.ipms.projectmgmt.projectmaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
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

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.jkdframework.organization.OrgRepository;
import aurionpro.erp.ipms.jkdframework.organization.Organization;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.openbravo.project.OpenBravoProject;
import aurionpro.erp.ipms.openbravo.project.OpenBravoProjectRepository;
import aurionpro.erp.ipms.utility.AppProperties;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;
    
    @Autowired
    private ProjectViewRepository projectViewRepo;
    
    @Autowired
    ProjectApprovalTaskRepository taskRepo;
    
    @Autowired
	OpenBravoProjectRepository openBravoProjectRepo;
    
    @Autowired
    AppProperties appProperties;
    
    @Autowired
    OrgRepository orgRep;
    
    @Autowired
    ProjectUtil projectFilterService;
    
    @Autowired
	TaskMasterService tmService;
    
    @Autowired
    NotificationMailFormat notificaton;
    
	public String getProjectPin(long id) {
		Optional<Project> entityList= projectRepo.findById(id);
		return entityList.get().getProjectPin();
	}

	public Iterable<ProjectView> findAll(ProjectView proj, Integer page, Integer size) {
		       ExampleMatcher em=ExampleMatcher.matching()
	                           .withIgnoreNullValues()
	                           .withIgnoreCase()
	                           .withStringMatcher(StringMatcher.CONTAINING);

	       Example<ProjectView> assetEx=Example.of(proj,em);
	       List<ProjectView> projectList = projectViewRepo.findAll(assetEx);
	      
	       Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = getProjectsPendingList(profileId);
	       
	       List<ProjectView> filteredProject = projectList.stream()  
	         	     .filter(f->projectId.contains(f.getEntityId())).collect(Collectors.toList());
	         
	       
	  		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	  			{
	  				Pageable paging = PageRequest.of(page-1, size);
	  			    int start =  (int) paging.getOffset();
	  			    int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	  			     		
	  			    return new PageImpl<ProjectView>(filteredProject.subList(start, end), paging, filteredProject.size());
	  			      
	  			}
	  			else
	  			{
	  			    return new PageImpl<ProjectView>(filteredProject);
	  		   	}
	}

	private List<Long> getProjectsPendingList(Long profileId) {
		List<ProjectList> objList =  projectRepo.getProjectsList(profileId);
			List<Long> projectList = new ArrayList<>();
			
			if (objList.size()!= 0)
			{
				for (ProjectList obj : objList) {									
					projectList.add(obj.getSelectionid());
				}	
			}	
						
			return projectList;	 
	}

	public List<SelectionList> SelectionProjectList() {
		 Long profileId=MyPrincipal.getMyProfile().getUserProfileId();      
		return projectRepo.SelectionProjectList(profileId);
	}

	public Project getProjByName(String projectName) {
		List<Project> entityList= projectRepo.findByProjectName(projectName);

        if (entityList.size()>0)
            return entityList.get(0);
        else
            throw new EntityValidationException("Project Invalid","Project Not Found");
	}

	public Optional<Project> getProjectById(Long id) {
		Optional<Project> proObj = projectRepo.findById(id);
		
		if (proObj == null)
    	{
    		new RuntimeException("Project Not Found");
    	}
		return proObj;
	}

	public Project createProject(Project proj) {
		 List<Project> entityList= projectRepo.findByProjectName(proj.getProjectName());

	        if(entityList.size()>0)
	        {
	            throw new EntityExistsException("The Specified Project already exists");
	        }
	        else{
	            validate(proj);
	            proj.setApprovalStatus("PENDING");
	           return projectRepo.save(proj);	            
	        }
	}

	private void validate(Project proj) {
		Organization org = orgRep.findById(proj.getOrganization()).get();
		
		if (proj.getProjectType().equalsIgnoreCase("P/L Projects") || proj.getProjectType().equalsIgnoreCase("Miscellaneous"))
		{
			if ((org.getOrgName().equalsIgnoreCase("Aurionpro Solutions Ltd"))){
				 throw new EntityNotFoundException("Wrong Project Type select with "+ org.getOrgName());
			}
		}
		else
		{
			if (!(org.getOrgName().equalsIgnoreCase("Aurionpro Solutions Ltd"))){
				 throw new EntityNotFoundException("Wrong Project Type select with "+ org.getOrgName());
			}
			
		}
	}

	public Project updateProject(Project proj) {
		Optional<Project> entityList= projectRepo.findById(proj.getEntityId());

        if(!entityList.isPresent())
        {
            throw new EntityNotFoundException("The Specified Project does not exists");
        }
        else{
            validate(proj);
            return projectRepo.save(proj);
        }
	}

	public Project deleteProject(Long id) {
		  Optional<Project> entityList= projectRepo.findById(id);

	        if(!(entityList.isPresent()))
	        {
	            throw new EntityNotFoundException("The Specified Project does not exists");
	        }

	        if (entityList.get().getIsDeleted()){
	            throw new EntityNotFoundException("Project already deleted");
	        }
	        else{
	        	entityList.get().setIsDeleted(true);
	            return projectRepo.save(entityList.get());
	        }
	}

	public Project getSubmitForApproval(Long id) {
		
		  Optional<Project> proObj= projectRepo.findById(id);

	        if(!(proObj.isPresent()))
	        {
	            throw new EntityNotFoundException("The Specified Project does not exists");
	        }
	        List<ProjectApprovalTask> tcheck = taskRepo.findByProjectid(id);
	        if (tcheck.size()>0){
	            throw new RuntimeException("Workflow for the Project has already been initiated");
	        }
		
		ProjectApprovalTask tMaster=new ProjectApprovalTask();
        tMaster.setWorkflowName("PROJECT_APPROVAL");
        tMaster.setProjectid(proObj.get().getEntityId());
        tMaster.setProjectname(proObj.get().getProjectName());    

         TaskResponse task = tmService.CreateTask(tMaster);
         task.setAction("APPROVED");//set for notification
			
         proObj.get().setApprovalStatus(task.getStatus());
         notificaton.sendNotification(task, tMaster.getProjectid(),
      		   "Project Approval", "Project Name :: " + tMaster.getProjectname(), null);

        
 		return projectRepo.save(proObj.get());	
	}

	public Project updateApprovalStatus(Long id, String status) {
		Optional<Project> projTemp= projectRepo.findById(id);
	       if (!projTemp.isPresent()){
	           throw new RuntimeException("Invalid Project Specified");
	       }
	       
	       projTemp.get().setApprovalStatus(status);
	       
	       if (projTemp.get().getApprovalStatus().equalsIgnoreCase("APPROVED"))
		     {
		    	  String pin = generateProjectPin();
		    	  projTemp.get().setProjectPin(pin);
		     }
	      return projectRepo.save(projTemp.get());	      
	}
	
	public String generateProjectPin() {
		String pin = appProperties.getGeneratedPin;
		try {
			List<String> pinList = projectRepo.getProjectPin();
			
			if (pinList.size() !=0)
			{
				String tempPin= (String)pinList.get(0);
				Integer pin1= 0;
				 String [] arrOfStr = tempPin.split("GSG");
			       pin1= Integer.parseInt(arrOfStr[1]);
			       pin = "GSG"+String.format("%04d",pin1 + 1);
			}
			return pin;
		}catch (NullPointerException e) {
			return pin;
		}
		
		
		
    }

	public Project saveOpenBravoId(Long id, String proj) {
		Optional<Project> projTemp= projectRepo.findById(id);
	       if (!projTemp.isPresent()){
	           throw new RuntimeException("Invalid Project Specified");
	       }
	       
	       projTemp.get().setApprovalStatus("APPROVED");
	       
	       OpenBravoProject openbravoProject = new OpenBravoProject();
	       
	       openbravoProject.setOpenBravoId(proj);
	       openbravoProject.setProject(projTemp.get());
	       
	       openBravoProjectRepo.save(openbravoProject);
	       
	      return projectRepo.save(projTemp.get());	 
	}

	public List<ProjectList> getProjectListByOrg(List<Long> idList) {
		 Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		return projectRepo.getProjectListByOrg(idList, profileId);
	}

	public List<ProjectList> getProjectListByOrg(Long id) {
		 Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		return projectRepo.getProjectListByOrg(id, profileId);
	}

   
}