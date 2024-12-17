package aurionpro.erp.ipms.billingmgmt.billingschedule;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/billingschedule")
public class BillingScheduleController {

    @Autowired
    BillingScheduleService billService;

    @PreAuthorize("hasAuthority('Billing_Schedule_ADD')")
    @PostMapping()
    public BillingSchedule createSchedule (@RequestBody @Valid BillingSchedule bill) {
    	validate(); 
    	return billService.createSchedule(bill);
    }
    

    @PreAuthorize("hasAuthority('Billing_Schedule_EDIT')")
    @PutMapping()
    public BillingSchedule updateSchedule(@Valid @RequestBody BillingSchedule bill) {
		validate();
		return billService.updateSchedule(bill);
    }
    
    private void validate() {
	
	}


  //  @PreAuthorize("hasAuthority('Billing_Schedule_VIEW')")
    @GetMapping("{projectid}/{milestoneno}")
    public Optional<BillingSchedule> getScheduleById(@PathVariable(value = "projectid") Long projectid,
    		@PathVariable(value = "milestoneno") String milestoneno) 
    {
    	return billService.getScheduleById(projectid,milestoneno);
    }
	
    @PreAuthorize("hasAuthority('Billing_Schedule_VIEW')")
    @PostMapping("/getScheduleList/{projectid}")
	    public List<BillingSchedule> getScheduleList(@PathVariable(value = "projectid") Long projectid,
	    		@RequestParam(name = "page", required = false) Integer page,
	    		@RequestParam(name = "size", required = false) Integer size, @RequestBody BillingSchedule bs){
    	
    	ScheduleIdentity obj = new ScheduleIdentity(projectid, null);
    			
    		bs.setId(obj);
	        return billService.getScheduleList(bs, page, size);
	    } 
    
    @GetMapping("/getBillingScheduleList/{projectid}")
    public List<SelectionList> getBillingScheduleList(@PathVariable(value = "projectid") Long projectid){
        return billService.getBillingScheduleList(projectid);
    } 
    
    @GetMapping("/getActiveBillingScheduleList/{projectid}")
    public List<SelectionList> getActiveBillingScheduleList(@PathVariable(value = "projectid") Long projectid){
        return billService.getActiveBillingScheduleList(projectid);
    } 
    
    @PreAuthorize("hasAuthority('Billing_Schedule_DELETE')")
     @DeleteMapping("{projectid}/{milestoneno}")
    public BillingSchedule deleteSchedule(@PathVariable(value = "projectid") Long projectid, @PathVariable(value = "milestoneno") String milestoneno){
    	return billService.deleteSchedule(projectid,milestoneno);
    }

}