package aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.projectmgmt.projectmaster.Project;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectRepository;
import aurionpro.erp.ipms.utility.MyPrincipal;

@Service
public class ProjectWorkflowLevelMappingService {

    @Autowired
    private ProjectWorkflowLevelMappingRepository projectLevelRepo;
    
    @Autowired
    private ProjectWorkflowLevelMappingViewRepository projectLevelViewRepo;
    
    @Autowired
    private ProjectRepository projectRepo;

	public Iterable<ProjectWorkflowLevelMapping> mappingByProject(Long project) {
		return projectLevelRepo.mappingByProject(project);
	}

	

	public List<ProjectWorkflowLevelMapping> createMapping(List<ProjectWorkflowLevelMapping> levelList, Long projectid, Long org) {
		List<ProjectWorkflowLevelMapping> projectLevelList = new ArrayList<ProjectWorkflowLevelMapping>();
		for(ProjectWorkflowLevelMapping proj: levelList) {
			
			proj.setProjectid(projectid);
			proj.setOrgid(org);
			proj.setWorkflowlevel(proj.getWorkflowlevel());
	    	
			Optional<ProjectWorkflowLevelMapping> mappingTemp=  projectLevelRepo.findMapping(proj.getProjectid(),
	    			proj.getWorkflowlevel(), proj.getOrgid(), proj.getUserid());
	    	
			 if(mappingTemp.isPresent())
	      {
	          throw new EntityExistsException("The Specified Mapping already exists");
	      }
			 projectLevelList.add(projectLevelRepo.save(proj));
		}
		return projectLevelList;
	}

	public List<ProjectWorkflowLevelMapping> updateMapping(List<ProjectWorkflowLevelMapping> levelList, Long projectid, Long org) {
		
		List<ProjectWorkflowLevelMapping> projectLevelList = new ArrayList<ProjectWorkflowLevelMapping>();
		for(ProjectWorkflowLevelMapping proj: levelList) {
	    	proj.setProjectid(projectid);
	    	proj.setOrgid(org);
	    	proj.setWorkflowlevel(proj.getWorkflowlevel());
	    	
	    	Optional<ProjectWorkflowLevelMapping> mappingTemp=  projectLevelRepo.findMapping(proj.getProjectid(),
	    			proj.getWorkflowlevel(), proj.getOrgid(), proj.getUserid());
	   	if (mappingTemp == null)
	   		throw new EntityExistsException("The Specified Mapping doesn't exists");
	   		 	
	    projectLevelList.add(projectLevelRepo.save(proj));
		}
		return projectLevelList;
	}

	
	public ProjectWorkflowLevelMapping deleteMapping(Long id) {
		Optional<ProjectWorkflowLevelMapping> mappingTemp= projectLevelRepo.findById(id);
	   	if (mappingTemp == null)
	   		throw new EntityExistsException("The Specified Mapping doesn't exists");
	   	projectLevelRepo.deleteById(id);
	    return null;
	}

	public List<ProjectWorkflowLevelMapping> getAllLevels(Long project) {
		Project pro = projectRepo.findById(project).get();
		String projectType = null;
		    if (pro.getProjectType().equals("P/L Projects") ||pro.getProjectType().equals("Miscellaneous")) {
		      projectType = pro.getProjectType();

		    }
		    else {
		      projectType = "ALL";
		    }
		
		 List<ProjectWorkflowLevelMapping> userWithLevel= projectLevelRepo.getLevelFromDefaultMapping(projectType,pro.getOrganization());
	     List<String> levelList = projectLevelRepo.getLevelListFromWorkflowStages(projectType,pro.getOrganization());
	     
	     if (levelList.size()>0) {
	    	 for(String level: levelList)
	    	 {
	    		 ProjectWorkflowLevelMapping dto = new ProjectWorkflowLevelMapping(level, null);
	    		 	    		
	    		 userWithLevel.add(dto);
	    	 }
	     }
	      
	     return userWithLevel;
	}

	public List<SelectionList> getListOfEmailId(String assignToRole, Long projectid) {
		
		try {
			return projectLevelRepo.getListOfEmailId(assignToRole, projectid);
		}
		catch (NullPointerException ex) {
			return null;
		}
				
	}



	public List<ProjectWorkflowLevelMapping> getDefaultAllLevels(Long project) {
		Project pro = projectRepo.findById(project).get();
		String projectType= null;
	    if (pro.getProjectType().equals("P/L Projects") ||pro.getProjectType().equals("Miscellaneous")) {
		      projectType = pro.getProjectType();

		    }
		    else {
		      projectType = "ALL";
		    }
	    List<ProjectWorkflowLevelMapping> levellist = projectLevelRepo.getLevelFromDefaultMapping(projectType, pro.getOrganization());
	    
	    ProjectWorkflowLevelMapping level = new ProjectWorkflowLevelMapping();
	    level.setUserid(pro.getProjectManager());
	    level.setWorkflowlevel("PROJECT MANAGER");
	    levellist.add(level);
	     return levellist;
	    
	}

	public List<String> getAllLevelsOfUserById(Long userId) {
		return projectLevelRepo.getAllLevelsOfUserById(userId);
	}
	
	public List<ProjectWorkflowLevelMapping> getAllLevelsByProjectId(Long projectId, Long user) {
		Project pro = projectRepo.findById(projectId).get();
		
		List<String> levelList= projectLevelRepo.getAllLevelsByProjectId(projectId, user, pro.getOrganization());
		List<ProjectWorkflowLevelMapping> plmList = new ArrayList<>();
		if (levelList.size()>0) {
	    	 for(String level: levelList)
	    	 {
	    		 ProjectWorkflowLevelMapping dto = new ProjectWorkflowLevelMapping();
	    		 	   dto.setWorkflowlevel(level); 		
	    		 	  plmList.add(dto);
	    	 }
	     }
		return plmList;
	}



	public PageImpl<ProjectWorkflowLevelMappingView> getAllProjectsOfUsers(ProjectWorkflowLevelMapping proj, Long user, Integer page, Integer size) {
		proj.setUserid(user);
		ProjectWorkflowLevelMapping projectLevel = castFilterValues(proj);
		List<ProjectWorkflowLevelMappingView> projectLevelList= projectLevelViewRepo.getProjectListByFilter(projectLevel.getProjectid(),
				projectLevel.getOrgid(), projectLevel.getWorkflowlevel(), projectLevel.getUserid());
	
	        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	           Pageable paging = PageRequest.of(page-1, size);
			   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), projectLevelList.size());
	     		
	           return new PageImpl<ProjectWorkflowLevelMappingView>(projectLevelList.subList(start, end), paging, projectLevelList.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<>(projectLevelList);
	    	}

	}
	
	private ProjectWorkflowLevelMapping castFilterValues(ProjectWorkflowLevelMapping plm) {
		ProjectWorkflowLevelMapping plmRequest = new ProjectWorkflowLevelMapping();
		long value = 0;
		if (plm.getProjectid() == null)
		{
			plmRequest.setProjectid(value);
		}
		else
		{
			plmRequest.setProjectid(plm.getProjectid());
		}
		
		if (plm.getOrgid() == null)
		{
			plmRequest.setOrgid(value);
		}
		else
		{
			plmRequest.setOrgid(plm.getOrgid());
		}
		
		if (plm.getWorkflowlevel() == null)
		{
			plmRequest.setWorkflowlevel("");
		}
		else
		{
			plmRequest.setWorkflowlevel(plm.getWorkflowlevel());
		}
		
		if (plm.getUserid() == null)
		{
			plmRequest.setUserid(value);
		}
		else
		{
			plmRequest.setUserid(plm.getUserid());
		}
		
		return plmRequest;
	}



	public List<ProjectWorkflowLevelMapping> saveUsersProjects(List<ProjectWorkflowLevelMapping> levelList) {
		List<ProjectWorkflowLevelMapping> projectLevelList = new ArrayList<ProjectWorkflowLevelMapping>();
		for(ProjectWorkflowLevelMapping proj: levelList) {
			
			Optional<ProjectWorkflowLevelMapping> mappingTemp=  projectLevelRepo.findMapping(proj.getProjectid(),
	    			proj.getWorkflowlevel(), proj.getOrgid(), proj.getUserid());
	    	
			 if(mappingTemp.isPresent())
	      {
	          throw new EntityExistsException("The Specified Mapping already exists");
	      }
			 projectLevelList.add(projectLevelRepo.save(proj));
		}
		return projectLevelList;
	}



	public Boolean saveAllProjects(Long org, Long user, String level) {

		 Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		return projectLevelRepo.saveAllProjects(org, user, level, profileId);
	}



	public List<ProjectWorkflowLevelMapping> getAllLevelsByOrg(Long org) {
		List<String> levelList= projectLevelRepo.getAllLevelsByOrg(org);
		List<ProjectWorkflowLevelMapping> plmList = new ArrayList<>();
		
		if (levelList.size()>0) {
	    	 for(String level: levelList)
	    	 {
	    		 ProjectWorkflowLevelMapping dto = new ProjectWorkflowLevelMapping();
	    		 	   dto.setWorkflowlevel(level); 		
	    		 	  plmList.add(dto);
	    	 }
	     }
		return plmList;
	}
}