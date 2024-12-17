package aurionpro.erp.ipms.authorization.department;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository deptRepo;

    @PreAuthorize("hasAuthority('Department_Master_VIEW')")
    @GetMapping()
    public Iterable<Department> getAllDepartments(){
        return deptRepo.findAll();
    }

    @GetMapping("/selectionlist")
    public List<SelectionList> getDeptList(){
        return deptRepo.SelectionDeptList();
    }

  //  @PreAuthorize("hasAuthority('Department_Master_VIEW')")
    @GetMapping("/{deptname}")
    public Department getOrgByName(@PathVariable(value = "deptname") String deptName){
        List<Department> deptList= deptRepo.findByDepartmentName(deptName);

        if (deptList.size()>0)
            return deptList.get(0);
        else
            throw new EntityValidationException("Department Invalid","Department Not Found");
    }

    @PreAuthorize("hasAuthority('Department_Master_ADD')")
    @PostMapping()
    public Department CreateDepartment(@RequestBody Department dept){

        List<Department> deptList= deptRepo.findByDepartmentName(dept.getDepartmentName());

        if(deptList.size()>0)
        {
            throw new EntityExistsException("The Specified Department already exists");
        }
        else{
            validate();
            return deptRepo.save(dept);
        }
    }

    @PreAuthorize("hasAuthority('Department_Master_EDIT')")
    @PutMapping()
    public Department UpdateDepartment(@RequestBody Department dept){

        Optional<Department> deptList= deptRepo.findById(dept.getEntityId());

        if(!deptList.isPresent())
        {
            throw new EntityNotFoundException("The Specified Department does not exists");
        }
        else{
            validate();
            return deptRepo.save(dept);
        }
    }

    @PreAuthorize("hasAuthority('Department_Master_DELETE')")
    @DeleteMapping("{deptname}")
    public Department DeleteDepartment(@PathVariable(value = "deptname") String deptName){

        List<Department> deptList= deptRepo.findByDepartmentName(deptName);

        if(deptList.size()==0)
        {
            throw new EntityNotFoundException("The Specified Department deos not exists");
        }

        if (deptList.get(0).getIsDeleted()){
            throw new EntityNotFoundException("Department already deleted");
        }
        else{
        	deptList.get(0).setIsDeleted(true);
            return deptRepo.save(deptList.get(0));
        }
    }

    private void validate() {
    }
}