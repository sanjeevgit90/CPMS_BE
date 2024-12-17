package aurionpro.erp.ipms.authorization.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface MenuRepository extends JpaRepository<Menu,String>{

    @Query("select m.menuname as selectionid, concat(m.displayname, ' (', m.menuname, ')') as selectionvalue from Menu m")
    public List<SelectionList> SelectionMenuList();
    
    @Query("select m from Menu m where m.parent=null order by m.menuorder")
    public List<Menu> GetAllParentMenu();

    @Query("select m from Menu m where m.parent!=null and m.parent.menuname=?1 order by m.menuorder")
    public List<Menu> GetAllChildMenu(String parentName);
    
    @Modifying
	@Query(value="DELETE FROM authentication.menu_rights WHERE menuname=?1", nativeQuery = true)
	public void deleteRight(String parentName);

}