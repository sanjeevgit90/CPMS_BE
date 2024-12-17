package aurionpro.erp.ipms.ordermgmt.ratecontract;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface RateContractRepository extends JpaRepository<RateContractMaster, Long> {

	public List<RateContractMaster> findByRateContractNo(String rateContractNo);
	
	@Query(value="select p.entityid as selectionid, p.rate_contract_no as selectionvalue from ordermgmt.rate_contract_master p"
			+ " where p.account_name in (select id from projectmgmt.getproject(?1) order by p.rate_contract_no)", nativeQuery = true)
    public List<SelectionList> selectionRcList(Long profileId);
	
	@Query(value="select p.entityid as selectionid, p.rate_contract_no as selectionvalue from ordermgmt.rate_contract_master p"
			+ " where p.account_name in (select id from projectmgmt.getproject(?1)) and p.approval_status = 'APPROVED' order by p.rate_contract_no", nativeQuery = true)
    public List<SelectionList> selectionApprovedRcList(Long profileId);
	
	@Query(value="select p.attachment from ordermgmt.rate_contract_attachments p where p.ratecontractid = ?1", nativeQuery = true)
    public List<String> getAttachmentList(long rcId);
}