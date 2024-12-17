package aurionpro.erp.ipms.vendor.addressmaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;

public interface AddressMasterRepository extends JpaRepository<AddressMaster,Long> {

	public List<AddressMaster> findByPartyMasterParent(PartyMaster partyMasterParent);

	//@Query(value="select c.entityid as selectionid,  concat(c.address1, ' ', c.address2,' ',c.landmark,' ',c.city,' ',c.district,' ',c.state,' ',c.country,' - ',c.pin_code) as selectionvalue from ordermgmt.addressmaster c where c.party_id=?1", nativeQuery = true)
	@Query(value="select c.entityid as selectionid, c.fulladdress as selectionvalue from ordermgmt.addressmaster c where c.party_id=?1 order by c.fulladdress", nativeQuery = true)
	public List<SelectionList> getAllAddressByPartyId(long partyId);
	
	@Query(value="select ((select a.state from ordermgmt.addressmaster a where a.entityId = ?1) = (select b.state from ordermgmt.addressmaster b where b.entityId = ?2))", nativeQuery = true)
	public boolean compareStateOfAddress(long firstAddId, long secondAddId);
}