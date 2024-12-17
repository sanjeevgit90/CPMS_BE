package aurionpro.erp.ipms.vendor.partymaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface PartyMasterRepository extends JpaRepository<PartyMaster, Long> {

	public List<PartyMaster> findByPartyNo(String partyNo);
	
	@Query("select p.entityId as selectionid, p.partyName as selectionvalue from PartyMaster p order by p.partyName")
    public List<SelectionList> selectionPartyList();
	
	@Query("select p.entityId as selectionid, p.partyName as selectionvalue from PartyMaster p where p.organisationId = ?1 order by p.partyName")
    public List<SelectionList> selectionPartyListByOrg(long orgId);
}
