package aurionpro.erp.ipms.authorization.menu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MenuTaskRepository extends CrudRepository<MenuTaskMaster,Long>{

    public List<MenuTaskMaster> findByMenuname(String menuname);
}