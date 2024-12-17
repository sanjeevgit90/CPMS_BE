package aurionpro.erp.ipms.projectmgmt.projecttypemaster;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface ProjectTypeMasterRepository extends JpaRepository<ProjectTypeMaster,String>{

    @Query("select p.projecttype as selectionid, p.projecttype as selectionvalue from ProjectTypeMaster p order by  p.projecttype")
    public List<SelectionList> SelectionProjectList();

}