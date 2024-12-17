package aurionpro.erp.ipms.projectmgmt.projectmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectApprovalTaskRepository extends JpaRepository<ProjectApprovalTask,Long>{

	List<ProjectApprovalTask> findByProjectid(Long id);

}