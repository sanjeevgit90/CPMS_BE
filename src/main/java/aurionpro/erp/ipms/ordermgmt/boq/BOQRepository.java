package aurionpro.erp.ipms.ordermgmt.boq;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface BOQRepository extends JpaRepository<BOQ, Long> {

	public List<BOQ> findByBoqNo(String boqNo);
	
	@Query("select b.entityId as selectionid, b.boqNo as selectionvalue from BOQ b")
    public List<SelectionList> selectionBoqList();
}
