package aurionpro.erp.ipms.assetmgmt.hsncodemaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface HsnMasterRepository extends JpaRepository<HsnCodeMaster,Long> {

	@Query("select h.hsncode as selectionid, h.hsncode as selectionvalue from HsnCodeMaster h order by h.hsncode")
	List<SelectionList> getHsnCodeList();

	@Query("select h.hsncode as selectionid, h.hsncode as selectionvalue from HsnCodeMaster h where h.isDeleted=false order by h.hsncode")
	List<SelectionList> getActiveHsnCodeList();

	Optional<HsnCodeMaster> findByHsncode(String hsncode);

}