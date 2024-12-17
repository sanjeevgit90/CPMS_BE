package aurionpro.erp.ipms.jkdframework.officelocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface OfficeLocationRepository extends JpaRepository<OfficeLocation,Long> {

    @Query("select o.entityId as selectionid, o.officename as selectionvalue from OfficeLocation o")
    public List<SelectionList> getOfficeList();

	public List<OfficeLocation> findByOfficename(String officename);

}