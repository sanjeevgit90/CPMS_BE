package aurionpro.erp.ipms.billingmgmt.invoice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.billingmgmt.billingschedule.BillingSchedule;
import aurionpro.erp.ipms.billingmgmt.billingschedule.BillingScheduleService;
import aurionpro.erp.ipms.billingmgmt.collectiontagging.CollectionTagging;
import aurionpro.erp.ipms.billingmgmt.collectiontagging.CollectionTaggingService;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@Service()
public class InvoiceService {
	
	@Autowired
    InvoiceRepository invoiceRepo;
	
	@Autowired
    InvoiceViewRepository invoiceViewRepo;
	
	@Autowired
    InvoiceTaskRepository taskRepo;
	
	@Autowired
    InvoiceTaskViewRepository taskViewRepo;
	
	@Autowired
	TaskMasterService tmService;
		
	@Autowired
    BillingScheduleService billService;
	
	@Autowired
    CollectionTaggingService collectionService;
	
    @Autowired
    NotificationMailFormat notificaton;
   	
	public InvoiceMaster createInvoice (InvoiceMaster invoice) {
		if (verifyInvoice(invoice.getProjectid(), invoice.getMilestoneno(), invoice.getTotalamount()))
		{
			invoice.setInvoicestatus("DRAFT");
			return invoiceRepo.save(invoice);	
		}
		else
		{
			 throw new EntityExistsException("Amount of Invoice can't be greater than Billing Schedule");
		}		
	}

	private boolean verifyInvoice(Long projectid, String milestoneno, Double amt) {
		Optional<BillingSchedule> billTemp = billService.getScheduleById(projectid, milestoneno);
		
		if ( amt > billTemp.get().getAmountofbilling())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public InvoiceMaster updateInvoice(InvoiceMaster invoice) {
		Optional<InvoiceMaster> invoiceTemp= invoiceRepo.findById(invoice.getEntityId());
	   	    	 
	   	if (invoiceTemp == null)
	   		throw new EntityExistsException("The Specified Invoice doesn't exists");
	   		 	
	   	if (verifyInvoice(invoice.getProjectid(), invoice.getMilestoneno(), invoice.getTotalamount()))
		{
			return invoiceRepo.save(invoice);	
		}
		else
		{
			 throw new EntityExistsException("Amount of Invoice can't be greater than Billing Schedule");
		}	
	}

	public Optional<InvoiceMaster> findInvoiceById(Long id) {
		Optional<InvoiceMaster> invoiceObj = invoiceRepo.findById(id);
    	
    	if (invoiceObj == null)
    	{
    		throw new RuntimeException("The Specified Invoice doesn't exists");
    	}
    	return invoiceObj;
	}

	public InvoiceMaster deleteInvoice(Long id) {
		 Optional<InvoiceMaster> invoiceList= invoiceRepo.findById(id);

	        if(invoiceList== null)
	        {
	            throw new EntityNotFoundException("The Specified Invoice does not exists");
	        }
	        List<CollectionTagging> collection = collectionService.getCollectionByInvoice(id);
			 
			 if (collection.size() > 0)
			 {
				 throw new EntityNotFoundException("This Invoice can't be deleted as Collection Exist");
			 }

	        if (invoiceList.get().getIsDeleted()){
	            throw new EntityNotFoundException("Record already deleted");
	        }
	        else{
	        	invoiceList.get().setIsDeleted(true);
	        	 return invoiceRepo.save(invoiceList.get());
	        }
	}

	
	public InvoiceMaster processInvoice(InvoiceRequest invoice) {
		InvoiceTaskMaster taskTemp = new InvoiceTaskMaster(invoice);
		
		Optional<InvoiceTaskMaster> tcheck=taskRepo.findById(invoice.getEntityId());

        if (!tcheck.isPresent()){
            throw new RuntimeException("Invalid Task Specified");
        }

        if(!tcheck.get().getInvoiceid().equals(invoice.getInvoiceid()))
            throw new RuntimeException("Task Invoice Id does not match with the Entity Invoice Id");

       TaskResponse task = tmService.ProcessTask(taskTemp);
       
       Optional<InvoiceMaster> invoiceTemp= invoiceRepo.findById(invoice.getInvoiceid());
       if (!invoiceTemp.isPresent()){
           throw new RuntimeException("Invalid Dc No Specified");
       }
       InvoiceMaster obj = invoiceTemp.get();
       obj.setInvoicestatus(task.getStatus());
       if (taskTemp.getAssignToRole().equalsIgnoreCase("ACCOUNT HEAD"))
       {
    	   obj.setInvoiceno(invoice.getInvoiceno());
    	   obj.setInvoicedate(invoice.getInvoicedate());
    	   obj.setAccountexcel(invoice.getInvoiceexcel());
    	   
       }
       else if (taskTemp.getAssignToRole().equalsIgnoreCase("BILLING TEAM"))
       {
    	   obj.setInvoicesignedexcel(invoice.getInvoicesignedexcel());
       }
       InvoiceView inv  = invoiceViewRepo.findById(obj.getEntityId()).get();
       
       notificaton.sendNotification(task, obj.getProjectid(), "Invoice Approval", "Invoice for Project :: " + inv.getProjectname() , null);

       return invoiceRepo.save(obj);
	}

	public List<InvoiceView> searchInvoice(InvoiceView invoice, Integer page, Integer size) {
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<InvoiceView> invoiceEx=Example.of(invoice,em);
       
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return invoiceViewRepo.findAll(invoiceEx, paging).getContent();
    	}
    	else
    	{
    		return invoiceViewRepo.findAll(invoiceEx);
    	}
	}

	public InvoiceMaster submitInvoice(Long id) {
		Optional<InvoiceMaster> invoiceTemp= invoiceRepo.findById(id);
	   	
		List<InvoiceTaskMaster> tcheck = taskRepo.findByInvoiceid(id);
        if (tcheck.size()>0){
            throw new RuntimeException("Workflow for this Invoice has already been initiated");
        }

        InvoiceTaskMaster tMaster=new InvoiceTaskMaster();
        tMaster.setWorkflowName("INVOICE_WORKFLOW");
        tMaster.setRemark("");
        tMaster.setInvoiceid(id);;

        TaskResponse task = tmService.CreateTask(tMaster);
        task.setAction("APPROVED");//set for notification
		
        invoiceTemp.get().setInvoicestatus(task.getStatus());
        InvoiceView inv  = invoiceViewRepo.findById(id).get();
        
        notificaton.sendNotification(task, inv.getProjectid(), "Invoice Approval", "Invoice for Project :: " + inv.getProjectname() , null);

		return invoiceRepo.save(invoiceTemp.get());	
	}

	public Optional<InvoiceTaskMaster> taskByid(Long id) {
		return taskRepo.findById(id);
	}

	public Iterable<InvoiceTaskView> taskByFilters(InvoiceTaskView invoice, Integer page, Integer size) {
	    invoice.setApprovalstatus("PENDING");
		 invoice.setWorkflowname("INVOICE_WORKFLOW");
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<InvoiceTaskView> invoiceEx=Example.of(invoice,em);
       
        List<InvoiceTaskView> taskList;
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		taskList= taskViewRepo.findAll(invoiceEx, paging).getContent();
    	}
    	else
    	{
    		taskList= taskViewRepo.findAll(invoiceEx);
    	}
        Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = getTask(profileId);
	       
	       List<InvoiceTaskView> filteredData = taskList.stream()  
	         	     .filter(f->projectId.contains(f.getTaskid())).collect(Collectors.toList());
	         
	          return filteredData;
	}

	private List<Long> getTask(Long profileId) {
		return taskViewRepo.getTask(profileId);
	}

	public InvoiceMaster viewInvoice(Long id) {
		InvoiceMaster invoiceObj = invoiceRepo.viewInvoice(id);
    	
    	if (invoiceObj == null)
    	{
    		throw new RuntimeException("The Specified Invoice doesn't exists");
    	}
    	
		return invoiceObj;
	}

	public List<InvoiceMaster> getInvoiceByMilestoneid(Long projectid, String milestoneno) {
		return invoiceRepo.getInvoiceByMilestoneid(projectid, milestoneno);
	}

	public List<SelectionList> getInvoiceListFromProject(Long projectid) {
		return invoiceRepo.getInvoiceListFromProject(projectid);
	}

	public List<SelectionList> getActiveInvoiceFromProject(Long projectid) {
		return invoiceRepo.getActiveInvoiceFromProject(projectid);
	}

	public List<InvoiceMaster> invoiceFromProject(Long projectid) {
		return invoiceRepo.invoiceFromProject(projectid);
	}

	public List<SelectionList> monthlyOfBillingReport(String startYear, String endYear) {
		return invoiceViewRepo.monthlyOfBillingReport(startYear, endYear);	
	}

	public List<InvoiceView> monthlyBillingReport(String month) {
		return invoiceViewRepo.monthlyBillingReport(month);	
	}

	public List<InvoiceTaskView> getAllHistoryTasks(InvoiceTaskView request, Integer page, Integer size) {
		request.setUpdatedby(MyPrincipal.getMyProfile().getUsername());
    	
        ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<InvoiceTaskView> taskEx=Example.of(request,em);
       
       List<InvoiceTaskView> taskList;
       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
   	{
   		Pageable paging = PageRequest.of(page, size);
   		taskList = taskViewRepo.findAll(taskEx, paging).getContent();
   	}
   	else
   	{
   		taskList= taskViewRepo.findAll(taskEx);
   	}
       List<InvoiceTaskView> historyTask = taskList.stream().filter(f->!(f.getApprovalstatus().startsWith("PENDING"))).collect(Collectors.toList());
       
       return historyTask;
	}

	public List<InvoiceTaskView> getHistoryById(long id) {
		return taskViewRepo.getHistoryById(id);
	}

		
}
