package aurionpro.erp.ipms.ordermgmt.grntask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GrnTaskRepository extends JpaRepository<GrnTask, Long> {

	List<GrnTask> findByGrnId(long grnId);

}
