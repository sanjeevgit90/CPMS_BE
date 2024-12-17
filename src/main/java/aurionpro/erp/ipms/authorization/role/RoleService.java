package aurionpro.erp.ipms.authorization.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.authorization.menu.Menu;
import aurionpro.erp.ipms.authorization.menu.MenuService;
import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.utility.MyPrincipal;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    MenuService menuService;

    @Autowired
    UserProfileService uProfileService;
    
    public List<Role> getMyRoles(){
        Long myProfileId=MyPrincipal.getMyProfile().getUserProfileId();
        UserProfile uProfile=uProfileService.getUserProfileById(myProfileId).get();

        return uProfile.getRoles();
    }

    public Optional<Role> getRoleByName(String roleName){
        return roleRepo.findById(roleName);
    }

    public RoleReponse getRoleByMenuHierarchy(String roleName){

        //isDeleted to be checked

        Optional<Role> role=Optional.empty();
        if(!roleName.isEmpty()){
            role=getRoleByName(roleName);
        }
        
        List<Menu> parentMenus=menuService.getAllParentMenu();
        RoleReponse roleResponse=new RoleReponse(roleName);

        if(role.isPresent()){
            roleResponse.menuRights=getMenuRightsByHierarchy(parentMenus,role.get().getMenuRights());
        }else{
            roleResponse.menuRights=getMenuRightsByHierarchy(parentMenus,null);
        }
        

        return roleResponse;
        

    }

    private List<MenuRightsWithChild> getMenuRightsByHierarchy(List<Menu> Menus, Collection<MenuRight> roleRight){
        List<MenuRightsWithChild> mnuRights=new ArrayList<MenuRightsWithChild>();

        Menus.forEach(m-> {
            Optional<MenuRight> mrTemp=Optional.empty();
            if(roleRight!=null){
                mrTemp= roleRight.stream().filter(rr->rr.getMenuName().equalsIgnoreCase(m.getMenuname())).findFirst();
            }
            MenuRightsWithChild rightsTemp;
            if(mrTemp.isPresent()){
                rightsTemp=new MenuRightsWithChild(m.getMenuname(),m.getDisplayname(), mrTemp.get().isEnableAdd(), 
                    mrTemp.get().isEnableEdit(), mrTemp.get().isEnableView(), mrTemp.get().isEnableDelete());
            }else{
                rightsTemp=new MenuRightsWithChild(m.getMenuname(),m.getDisplayname(), false, false, false, false);
            }
            if(m.getChildmenu()!=null){
                List<MenuRightsWithChild> childMenu=getMenuRightsByHierarchy(m.getChildmenu(),roleRight);
                rightsTemp.setChildMenu(childMenu);
            }

            mnuRights.add(rightsTemp);
        });

        return mnuRights;
        
    }
    
}