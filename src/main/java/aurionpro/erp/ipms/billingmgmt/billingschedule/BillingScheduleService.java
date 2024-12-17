package aurionpro.erp.ipms.billingmgmt.billingschedule;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.billingmgmt.invoice.InvoiceMaster;
import aurionpro.erp.ipms.billingmgmt.invoice.InvoiceService;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.projectmgmt.projectmaster.Project;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectService;

@Service()
public class BillingScheduleService {
	
	@Autowired
    BillingScheduleRepository billRepo;
	
	@Autowired
    ProjectService projectService;
	
	@Autowired
    InvoiceService invoiceService;
	
	public BillingSchedule createSchedule(@Valid BillingSchedule bill) {
		Optional<BillingSchedule> billTemp= billRepo.findByIdProjectidAndIdMilestoneno(bill.getId().getProjectid(),bill.getId().getMilestoneno());
    	
		 if(billTemp.isPresent())
       {
           throw new EntityExistsException("The Specified Billing Schedule already exists");
       }
	 
		if (verifyMilestone(bill.getId().getProjectid(), bill.getAmountofbilling()))
		{
			return billRepo.save(bill);
		}
		else
		{
			 throw new EntityExistsException("Amount of Milestone can't be greater than Project Budget");
		}
			
   	 
	}

	private boolean verifyMilestone(Long projectid, Double amount) {
		List<SelectionList> allBill = billRepo.getActiveBillingScheduleList(projectid);
		Double totalAmt= 0.0;
		Double dbAmt = 0.0;
		if (allBill.size()>0)
		{
			dbAmt = billRepo.verifyMilestone(projectid);	
		}
			totalAmt = dbAmt + amount;
			Optional<Project> project = projectService.getProjectById(projectid);
			
			if ( totalAmt > project.get().getBudget())
			{
				return false;
			}
			else
			{
				return true;
			}
		
	}

	public BillingSchedule updateSchedule(@Valid BillingSchedule bill) {
		Optional<BillingSchedule> billTemp= billRepo.findByIdProjectidAndIdMilestoneno(bill.getId().getProjectid(),bill.getId().getMilestoneno());
	     
		if(billTemp == null)
 			 throw new EntityExistsException("The Specified Billing Schedule doesn't exists");
		
		if (verifyMilestone(bill.getId().getProjectid(), 0.0))
		{
			return billRepo.save(bill);
		}
		else
		{
			 throw new EntityExistsException("Amount of Milestone can't be greater than Project Budget");
		}
			
	}

	public Optional<BillingSchedule> getScheduleById(Long projectid, String milestoneno) {
		Optional<BillingSchedule> billTemp= billRepo.findByIdProjectidAndIdMilestoneno(projectid,milestoneno);
    	
		if (billTemp == null)
    	{
       		new RuntimeException("The Specified Billing Schedule does not exists");
    	}
       	
		return billTemp;
	}

	public List<BillingSchedule> getScheduleList(BillingSchedule bs, Integer page, Integer size) {
		ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

    	Example<BillingSchedule> billEx=Example.of(bs,em);
    	
    	if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
            return billRepo.findAll(billEx, paging).getContent();
    	}
    	else
    	{
    		 return billRepo.findAll(billEx);
    	}
		// return billRepo.getScheduleList(projectid);
	}

	public List<SelectionList> getBillingScheduleList(Long projectid) {
		 return billRepo.getBillingScheduleList(projectid);
	}

	public List<SelectionList> getActiveBillingScheduleList(Long projectid) {
		 return billRepo.getActiveBillingScheduleList(projectid);
	}

	public BillingSchedule deleteSchedule(Long projectid, String milestoneno) {
		Optional<BillingSchedule> billTemp= billRepo.findByIdProjectidAndIdMilestoneno(projectid,milestoneno);
    	
		 if(billTemp== null)
       {
           throw new EntityNotFoundException("The Specified Billing Schedule does not exists");
       }

		 List<InvoiceMaster> invoice = invoiceService.getInvoiceByMilestoneid(projectid,milestoneno);
		 
		 if (invoice.size() > 0)
		 {
			 throw new EntityNotFoundException("This Billing Schedule can't be deleted as Invoice Exist");
		 }
		 
       if (billTemp.get().getIsDeleted()){
           throw new EntityNotFoundException("Billing Schedule already deleted");
       }
       else{
       	billTemp.get().setIsDeleted(true);
           return billRepo.save(billTemp.get());
       }
	}
	
	
}
