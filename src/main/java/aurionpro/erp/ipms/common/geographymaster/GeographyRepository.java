package aurionpro.erp.ipms.common.geographymaster;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface GeographyRepository extends JpaRepository<GeographyMaster,GeographyIdentity>{
	
	@Query("select g.id.geographyname as selectionid, g.id.geographyname as selectionvalue from GeographyMaster g where g.id.parentgeography='NA' order by g.id.geographyname")
	List<SelectionList> getAllState();

	@Query("select g.id.geographyname as selectionid, g.id.geographyname as selectionvalue from GeographyMaster g where g.id.parentgeography='NA' and g.isDeleted=false  order by g.id.geographyname")
	List<SelectionList> getActiveState();

	@Query("select g.id.geographyname as selectionid, g.id.geographyname as selectionvalue from GeographyMaster g where g.id.parentgeography!='NA'  order by g.id.geographyname")
	List<SelectionList> getAllDistrict();
	
	@Query("select g.id.geographyname as selectionid, g.id.geographyname as selectionvalue from GeographyMaster g where g.id.parentgeography!='NA' and g.isDeleted=false  order by g.id.geographyname")
	List<SelectionList> getActiveDistrict();

	@Query("select g.id.geographyname as selectionid, g.id.geographyname as selectionvalue from GeographyMaster g where g.id.parentgeography!='NA' and g.id.parentgeography=?1  order by g.id.geographyname")
	List<SelectionList> getAllDistrictByState(String state);
	
	@Query("select g.id.geographyname as selectionid, g.id.geographyname as selectionvalue from GeographyMaster g where g.id.parentgeography!='NA' and g.id.parentgeography=?1 and g.isDeleted=false  order by g.id.geographyname")
	List<SelectionList> getActiveDistrictByState(String state);
	 
	@Query("select g from GeographyMaster g where g.isDeleted=false  order by g.id.geographyname")
	List<GeographyMaster> getActiveGeographyList();

	@Query("select g from GeographyMaster g  order by g.id.geographyname")
	List<GeographyMaster> getGeographyList();

	Optional<GeographyMaster> findByIdGeographynameAndIdParentgeography(String geographyname, String parentgeography);

}