package aurionpro.erp.ipms.ordermgmt.constant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface ConstantMasterRepository extends JpaRepository<ConstantMaster, Long> {

	@Query("select c.entityId as selectionid, c.value as selectionvalue from ConstantMaster c order by c.value")
	List<SelectionList> selectionConstantList();
	
	@Query("select c.entityId as selectionid, c.value as selectionvalue from ConstantMaster c where c.organisationId = ?1 order by c.value")
	List<SelectionList> selectionConstantByOrgList(long orgId);
}
