package aurionpro.erp.ipms.ticketmgmt.ticketclassification;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface TicketClassificationRepository extends JpaRepository<TicketClassification,Long> {
	
	@Query("select c.classificationValue as selectionid, c.classificationValue as selectionvalue from TicketClassificationView c")
	List<SelectionList> getAllClassificationList();

	@Query("select c.classificationValue as selectionid, c.classificationValue as selectionvalue from TicketClassification c where c.isDeleted=false")
	List<SelectionList> getActiveClassificationList();
		
	@Query("select c.classificationValue as selectionid, c.classificationValue as selectionvalue from TicketClassification c where c.projectId=?1 and c.isDeleted=false")
	List<SelectionList> getClassificationByProjectList(Long id);

	@Query("select c from TicketClassification c where c.projectId=?2 and c.classificationValue=?1")
	Optional<TicketClassification> findClassfication(String classificationValue, Long projectId);
    

}

