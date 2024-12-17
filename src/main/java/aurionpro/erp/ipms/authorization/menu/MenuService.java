package aurionpro.erp.ipms.authorization.menu;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MenuService {

    @Autowired
    MenuRepository menuRepo;

    public List<Menu> getAllParentMenu(){
        return menuRepo.GetAllParentMenu();
    }

	public Menu deleteMenu(String parentName) {
		Optional<Menu> menuTemp=menuRepo.findById(parentName);

        if(!menuTemp.isPresent())
        {
            throw new EntityNotFoundException("Menu does not exist");
        }

        if(menuTemp.get().getIsDeleted())
        {
            throw new EntityNotFoundException("Menu already deleted");
        }
        else{
            menuRepo.deleteRight(parentName);
            menuTemp.get().setIsDeleted(true);
            return menuRepo.save(menuTemp.get());
        }
	}
    
}