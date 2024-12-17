package aurionpro.erp.ipms.authorization.department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface DepartmentRepository extends JpaRepository<Department,Long>{

    @Query("select d.entityId as selectionid, d.departmentName as selectionvalue from Department d order by d.departmentName")
    public List<SelectionList> SelectionDeptList();

    public List<Department> findByDepartmentName(String deptName);

}