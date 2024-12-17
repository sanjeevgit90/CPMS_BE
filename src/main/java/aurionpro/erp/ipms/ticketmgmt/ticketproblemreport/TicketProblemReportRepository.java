package aurionpro.erp.ipms.ticketmgmt.ticketproblemreport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;


public interface TicketProblemReportRepository extends JpaRepository<TicketProblemReport,Long> {

	@Query("select p.problemReportValue as selectionid, p.problemReportValue as selectionvalue from TicketProblemReport p")
	List<SelectionList> getAllProblemReportList();

	@Query("select p.problemReportValue as selectionid, p.problemReportValue as selectionvalue from TicketProblemReport p where p.isDeleted=false")
	List<SelectionList> getActiveProblemReportList();
	
	@Query("select a.problemReportValue as selectionid, a.problemReportValue as selectionvalue from TicketProblemReport a where a.projectId=?1 and a.isDeleted=false")
	List<SelectionList> getProblemByProjectList(Long id);

	@Query("select c from TicketProblemReport c where c.projectId=?2 and c.problemReportValue=?1")
	Optional<TicketProblemReport> findProblemReported(String problemReportValue, Long projectId); 


}
