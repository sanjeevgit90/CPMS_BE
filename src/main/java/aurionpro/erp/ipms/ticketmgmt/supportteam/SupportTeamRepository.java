package aurionpro.erp.ipms.ticketmgmt.supportteam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.ticketmgmt.licensemaster.LicenseMaster;
public interface SupportTeamRepository extends JpaRepository<SupportTeam,String> {

    @Query("select l.employeeId as selectionid, l.employeeId as selectionvalue from SupportTeam l")
	List<SelectionList> getAllSupportTeamList();

	@Query("select l.employeeId as selectionid, l.employeeId as selectionvalue from SupportTeam l where l.isDeleted=false")
	List<SelectionList> getActiveSupportTeamList();

	

	
}
