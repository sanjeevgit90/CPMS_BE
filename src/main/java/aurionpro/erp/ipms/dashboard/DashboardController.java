package aurionpro.erp.ipms.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ipms/dasboard")
public class DashboardController {

    @Autowired
    private DashboardService dasboardService;

    @GetMapping("/totalRegistrationCount")
    public DashboardCount totalRegistrationCount(){
        return dasboardService.totalRegistrationCount();
    }

    @GetMapping("/teamWiseCount")
    public DashboardCount teamWiseCount(){
         return dasboardService.teamWiseCount();
    }
    
    @GetMapping("/buWiseCount")
    public DashboardCount buWiseCount(){
         return dasboardService.buWiseCount();
    }
    
    @GetMapping("/buWiseGraph")
    public List<DashboardCount> buWiseGraph(){
         return dasboardService.buWiseGraph();
    }
    
    @GetMapping("/officeLocationWiseGraph")
    public List<DashboardCount> officeLocationWiseGraph(){
         return dasboardService.officeLocationWiseGraph();
    }
    
}