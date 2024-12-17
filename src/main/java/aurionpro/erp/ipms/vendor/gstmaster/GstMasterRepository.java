package aurionpro.erp.ipms.vendor.gstmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

public interface GstMasterRepository extends JpaRepository<GstMaster,Long> {

	public List<GstMaster> findByPartyMasterParent(PartyMaster partyMasterParent);
	
	@Query("select g.entityId as selectionid, g.gstNo as selectionvalue from GstMaster g where g.partyMasterParent.entityId=?1")
    public List<SelectionList> selectionGstList(long partyId);
	
	@Query("select g.entityId, g.gstNo from GstMaster g where g.partyMasterParent.entityId=?1 and g.state=?2")
    public GstMaster getGstByPartyAndState(long partyId, String stateId);

	@Query("select g from GstMaster g where g.partyMasterParent.entityId=?1 and g.state=(select a.state from AddressMaster a where a.entityId=?2)")
	public GstMaster getGstByPartyAndPartyId(long partyId, long addressid);
}
