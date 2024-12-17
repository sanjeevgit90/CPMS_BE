package aurionpro.erp.ipms.authorization.menu;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.authorization.role.Role;
import aurionpro.erp.ipms.authorization.role.RoleService;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/menu")
public class MenuController {

    @Autowired
    MenuRepository menuRepo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    RoleService roleService;
    
    @Autowired
    MenuService menuService;

    @GetMapping("/selectionlist")
    public List<SelectionList> getSelectionMenu(){
        return menuRepo.SelectionMenuList();
    }

    
    @GetMapping()
    public List<Menu> getAllParentMenu(){
        return menuRepo.GetAllParentMenu();
    }

    @GetMapping("/mymenus")
    public List<Menu> getMyMenu(){

        String mymenus=getMyMenus();
        List<Menu> pMenus=menuRepo.GetAllParentMenu();
       // for web menu
        //return pMenus.stream().filter(m -> mymenus.contains(m.getMenuname())).collect(Collectors.toList());
        List<Menu> tempList = filterMyMenus(pMenus, mymenus);
        List<Menu> menuList = tempList.stream()  
       	     .filter(f->!(f.getMenuname().startsWith("MOB"))).collect(Collectors.toList());
       
        return menuList;
    }
 // for web menu
    @GetMapping("/mymobilemenus")
    public List<Menu> getMyMobileMenu(){

        String mymenus=getMyMenus();
        List<Menu> pMenus=menuRepo.GetAllParentMenu();
       
        //return pMenus.stream().filter(m -> mymenus.contains(m.getMenuname())).collect(Collectors.toList());
        List<Menu> tempList = filterMyMenus(pMenus, mymenus);
        List<Menu> menuList = tempList.stream()  
       	     .filter(f->f.getMenuname().startsWith("MOB")).collect(Collectors.toList());
       
        return menuList;
    }

    private String getMyMenus() {
        List<Role> myroles=roleService.getMyRoles();
        StringJoiner myMenus=new StringJoiner(",");

        myroles.forEach(r-> {
            r.getMenuRights().forEach(mr -> myMenus.add(mr.getMenuName()));  
        });

        return myMenus.toString();
    }

    private List<Menu> filterMyMenus(List<Menu> menu, String myMenu)
    {
        List<Menu> tempList=menu.stream().filter(m -> myMenu.contains(m.getMenuname()))
        		.sorted(Comparator.comparing(Menu::getMenuorder))
        		.collect(Collectors.toList());
       
        tempList.forEach(m -> {
            if(m.getChildmenu()!=null)
            {
                m.setChildmenu(filterMyMenus(m.getChildmenu(), myMenu));
            }
        });

        return tempList;
    }

    @GetMapping("/childmenu/{menuname}")
    public List<Menu> getAllchildMenu(@PathVariable(value = "menuname") String parentName){
        return menuRepo.GetAllChildMenu(parentName);

        // Menu menuobj=new Menu();
        // ExampleMatcher em=ExampleMatcher.matching().withIgnoreCase();
        // Example<Menu> mExample=Example.of(menuobj, em);
        
        // List<Menu> filtermenu= menuRepo.findAll(mExample);
    }

    @PreAuthorize("hasAuthority('Menu_Master_VIEW')")
    @PostMapping("/menuByFilter")
    public List<Menu> getMenuByFilter(@RequestBody Menu menu){
        
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
        					.withStringMatcher(StringMatcher.CONTAINING);
        
        Example<Menu> menuEx=Example.of(menu,em);

        return menuRepo.findAll(menuEx);
        
    }

    
    @PreAuthorize("hasAuthority('Menu_Master_ADD')")
    @PostMapping()
    public Menu createMenu(@RequestBody Menu menu){

        Optional<Menu> menuTemp=menuRepo.findById(menu.getMenuname());

        if(menuTemp.isPresent())
        {
            throw new EntityExistsException("The Menu already exists");
        }
        else{
            Validated();
            return menuRepo.save(menu);
        }
    }
    
   
    @PreAuthorize("hasAuthority('Menu_Master_EDIT')")
    @PutMapping()
    public Menu UpdateMenu(@RequestBody Menu menu){

        Optional<Menu> menuTemp=menuRepo.findById(menu.getMenuname());

        if(!menuTemp.isPresent())
        {
            throw new EntityExistsException("Menu does not exist");
        }
        else{
            Validated();
            return menuRepo.save(menu);
        }
    }

    @PreAuthorize("hasAuthority('Menu_Master_DELETE')")
     @DeleteMapping("{menuname}")
    public Menu DeleteMenu(@PathVariable(value = "menuname") String parentName){
    	return menuService.deleteMenu(parentName);
    }

    private boolean Validated(){

        return true;
    }

    
}