package aurionpro.erp.ipms.projectmgmt.projectmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface ProjectRepository extends JpaRepository<Project,Long>{

    @Query(value = "select p.entityid as selectionid, "
    		+ "case "
    		+ "when p.projectpin is null then p.projectname "
    		+ "when p.projectpin is not null then concat(p.projectname, ' (', p.projectpin, ')') "
    		+ "end as selectionvalue "
    		+ "from projectmgmt.project p where p.entityid in "
    		+ "(select id from projectmgmt.getproject(?1))  order by p.projectname", nativeQuery = true)
    public List<SelectionList> SelectionProjectList(Long profileId);

    public List<Project> findByProjectName(String projectName);

    @Query(value="SELECT p.projectpin FROM projectmgmt.project p  where p.projectpin is not null order by p.createdDate desc limit 1", nativeQuery = true)
	public List<String> getProjectPin();

	@Query(value="select id as selectionid, projectname as selectionvalue from projectmgmt.getproject(?1)", nativeQuery = true)
	public List<ProjectList> getProjectsListByProfileId(Long profileId);
	

	@Query(value="select id as selectionid, projectname as selectionvalue from projectmgmt.getallproject(?1)", nativeQuery = true)
	public List<ProjectList> getProjectsList(Long profileId);


	 @Query(value = "select p.entityid as selectionid, "
	    		+ "case "
	    		+ "when p.projectpin is null then p.projectname "
	    		+ "when p.projectpin is not null then concat(p.projectname, ' (', p.projectpin, ')') "
	    		+ "end as selectionvalue "
	    		+ "from projectmgmt.project p where p.organization in (?1) and p.entityid in "
	    		+ "(select id from projectmgmt.getproject(?2)) order by p.projectname", nativeQuery = true)
	    public List<ProjectList> getProjectListByOrg(List<Long> idList, Long profileId);

	 @Query(value = "select p.entityid as selectionid, "
	    		+ "case "
	    		+ "when p.projectpin is null then p.projectname "
	    		+ "when p.projectpin is not null then concat(p.projectname, ' (', p.projectpin, ')') "
	    		+ "end as selectionvalue "
	    		+ "from projectmgmt.project p where p.organization =?1 and p.entityid in "
	    		+ "(select id from projectmgmt.getproject(?2)) order by p.projectname", nativeQuery = true)
	public List<ProjectList> getProjectListByOrg(Long id, Long profileId);

	/* created by sachin */
	@Query(value = "select  case when count(*)>0 then 'true' else 'false' END "
			+ "from projectmgmt.project_workflowlevel_mapping_view " + "where projectid =?1 " + "and workflowlevel=?2 "
			+ "and emailid =?3", nativeQuery = true)
	public Boolean cheeckServiceHeadLevel(Long projectid, String level, String emailid);



}