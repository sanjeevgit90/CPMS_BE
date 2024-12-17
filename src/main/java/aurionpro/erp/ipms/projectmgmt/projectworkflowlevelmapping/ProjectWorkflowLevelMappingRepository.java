package aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface ProjectWorkflowLevelMappingRepository extends JpaRepository<ProjectWorkflowLevelMapping,Long>{

   @Query("select p from ProjectWorkflowLevelMapping p where p.projectid = ?1")
   public List<ProjectWorkflowLevelMapping> mappingByProject(Long project);
	    
   @Query("SELECT NEW ProjectWorkflowLevelMapping( workflowlevel as level, userid as user) FROM DefaultMappingConfig "
	    		+ " where  organizationid = ?2 and projecttype = ?1")
   public List<ProjectWorkflowLevelMapping> getLevelFromDefaultMapping(String projectType, Long org);

   @Query(value="SELECT  distinct(assigntorole) FROM public.workflowstage where (assigntorole !='NONE') and assigntorole "
   		+ " not in (SELECT  workflowlevel  FROM projectmgmt.default_mapping_config) and assigntorole not like 'ROLE_%'", nativeQuery = true)
   public List<String> getLevelListFromWorkflowStages(String projectType, Long org);

   @Query(" SELECT p FROM ProjectWorkflowLevelMapping p where p.orgid=?3 "
   		+ "and p.projectid=?1 and p.workflowlevel=?2 and p.userid=?4")
public Optional<ProjectWorkflowLevelMapping> findMapping(Long projectid, String workflowlevel, Long orgid, Long userid);

   @Query(value="select emailid as selectionid, mobileappkey as selectionvalue FROM projectmgmt.project_workflowlevel_mapping_view where workflowlevel=?1 and projectid=?2", nativeQuery = true)
   public List<SelectionList> getListOfEmailId(String assignToRole, Long projectid);
   
   @Query("select distinct(workflowlevel) from ProjectWorkflowLevelMapping where userid = ?1")
   public List<String> getAllLevelsOfUserById(Long userId);

   @Query(value="select distinct * from  (select workflowlevel  FROM projectmgmt.default_mapping_config"
   		+ " where organizationid= ?3 "
   		+ " union all "
   		+ " SELECT distinct(assigntorole) FROM public.workflowstage where (assigntorole !='NONE') and assigntorole not like 'ROLE_%' )as level "
   		+ " except"
   		+ "	SELECT  workflowlevel from projectmgmt.project_workflowlevel_mapping where userid=?2 and projectid=?1", nativeQuery = true)
   public List<String> getAllLevelsByProjectId(Long projectId, Long user, Long orgid);

   @Query(value="select * from projectmgmt.saveallprojectlevels(?3,?2,?1,?4)", nativeQuery = true)
   public Boolean saveAllProjects(Long org, Long user, String level, Long profileId);

 @Query(value="select distinct * from  (select workflowlevel  FROM projectmgmt.default_mapping_config"
   		+ " where organizationid= ?1 "
   		+ " union all "
   		+ " SELECT distinct(assigntorole) FROM public.workflowstage where (assigntorole !='NONE') and assigntorole not like 'ROLE_%' )as level", nativeQuery = true)
 public List<String> getAllLevelsByOrg(Long org);

}