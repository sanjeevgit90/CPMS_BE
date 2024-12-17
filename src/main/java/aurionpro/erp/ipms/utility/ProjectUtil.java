package aurionpro.erp.ipms.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectList;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectRepository;

@Service
public  class ProjectUtil {
	  
	    @Autowired
	    private ProjectRepository projectRepo;
	   
	
	 public List<Long>  getProjectsListByProfileId(Long profileId) {
		 
		List<ProjectList> objList = projectRepo.getProjectsListByProfileId(profileId);
		 
				 		
		List<Long> projectList = new ArrayList<>();
		
		if (objList.size()!= 0)
		{
			for (ProjectList obj : objList) {									
				projectList.add(obj.getSelectionid());
			}	
		}	
					
		return projectList;
    }
	
}
