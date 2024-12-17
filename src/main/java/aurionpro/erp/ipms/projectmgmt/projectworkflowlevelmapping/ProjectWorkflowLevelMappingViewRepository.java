package aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectWorkflowLevelMappingViewRepository extends JpaRepository<ProjectWorkflowLevelMappingView,Long>{

	@Query(value="select * from projectmgmt.projectByfilter(?1,?2,?3,?4)",  nativeQuery = true)
	List<ProjectWorkflowLevelMappingView> getProjectListByFilter(Long projectid, Long orgid, String workflowlevel,
			Long userid);

}