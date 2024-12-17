package aurionpro.erp.ipms.projectmgmt.projectprogressstatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface ProjectProgressStatusRepository extends JpaRepository<ProjectProgressStatus,Long>{

    @Query("select p.progressname as selectionid, p.progressname as selectionvalue from ProjectProgressStatus p")
    public List<SelectionList> SelectionStatustList();

}