package aurionpro.erp.ipms.authorization.role;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

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

@RestController
@RequestMapping(value = "/ipms/role")
public class RoleController {
   
    @Autowired
    RoleRepository roleRepo;

    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAuthority('Role_Master_VIEW')")
    @GetMapping()
    public Iterable<Role> getAllRole(){
        return roleRepo.findAll();
    }

    @GetMapping("/selectionlist")
    public List<SelectionList> getSelectionRole(){
        return roleRepo.SelectionRoleList();
    }

    @GetMapping("/myroles")
    //@JsonIgnoreProperties("userProfile")
    public List<Role> getMyRole(){
        return roleService.getMyRoles();
    }

    @GetMapping("/{rolename}")
    public Optional<Role> getRoleByName(@PathVariable(value = "rolename") String rolename){
        return roleService.getRoleByName(rolename);
    }

    @GetMapping("/menuhierarchy/{rolename}")
    public RoleReponse getRoleByMenuHierarchy(@PathVariable(value = "rolename") String rolename){
        RoleReponse rr= roleService.getRoleByMenuHierarchy(rolename);
        return rr;
    }

    @GetMapping("/menuhierarchy")
    public RoleReponse getRoleByMenuHierarchy(){
        RoleReponse rr= roleService.getRoleByMenuHierarchy("");
        return rr;
    }

    @PreAuthorize("hasAuthority('Role_Master_ADD')")
    @PostMapping()
    public Role CreateRole(@RequestBody Role role){

        Optional<Role> roleTemp= roleRepo.findById(role.getRolename());

        if(roleTemp.isPresent())
        {
            throw new EntityExistsException("The Role already exists");
        }
        else{
            validated();
            return roleRepo.save(role);
        }
    }

    @PreAuthorize("hasAuthority('Role_Master_EDIT')")
     @PutMapping()
    public Role UpdateRole(@RequestBody Role role){

        Optional<Role> roleTemp= roleRepo.findById(role.getRolename());

        if(!roleTemp.isPresent())
        {
            throw new EntityNotFoundException("Role does not exists");
        }
        else{
            validated();
            return roleRepo.save(role);
        }
    }

    @PreAuthorize("hasAuthority('Role_Master_DELETE')")
     @DeleteMapping("{rolename}")
    public Role DeleteRole(@PathVariable(value = "rolename") String rolename){

        Optional<Role> roleTemp= roleRepo.findById(rolename);

        if(!roleTemp.isPresent())
        {
            throw new EntityNotFoundException("Role does not exists");
        }

        if(roleTemp.get().isDeleted())
        {
            throw new EntityNotFoundException("Role has already been deleted");
        }
        else{
            validated();
            roleTemp.get().setDeleted(true);
            return roleRepo.save(roleTemp.get());
        }
    }

    private boolean validated()
    {
        return true;
    }
}