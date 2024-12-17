package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.billingmgmt.invoice.InvoiceMaster;
import aurionpro.erp.ipms.billingmgmt.invoice.InvoiceService;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@Service()
public class CollectionTaggingService {
	
	@Autowired
    CollectionTaggingRepository collectionRepo;
	
	@Autowired
    CollectionViewRepository collectionViewRepo;
	
	@Autowired
	CollectionTaskRepository taskRepo;
	
	@Autowired
	CollectionTaskViewRepository taskViewRepo;
	
	@Autowired
	TaskMasterService tmService;
		
	@Autowired
    InvoiceService invoiceService;
	
	@Autowired
    NotificationMailFormat notificaton;
	
	public CollectionTagging createColection(@Valid CollectionTagging collection) {
		
	   	if (verifyCollection(collection.getInvoiceid(), collection.getNetamountcredieted()))
		{
	   		collection.setCollectionstatus("DRAFT");
	      	 return collectionRepo.save(collection);
		}
		else
		{
			 throw new EntityExistsException("Amount of Collection can't be greater than Invoice");
		}	
	}

	private boolean verifyCollection(Long invoiceid, Double amt) {
		Optional<InvoiceMaster> invoiceTemp = invoiceService.findInvoiceById(invoiceid);
		List<CollectionTagging> allCollection = collectionRepo.getActiveCollectionByInvoice(invoiceid);
		Double totalAmt= 0.0;
		Double dbAmt = 0.0;
		if (allCollection.size()>0)
		{ 
			dbAmt = collectionRepo.getCollection(invoiceid);	
		}
			totalAmt = dbAmt + amt;
			
			if ( totalAmt > invoiceTemp.get().getTotalamount())
			{
				return false;
			}
			else
			{
				return true;
			}
		
	}

	public CollectionTagging updateCollection(@Valid CollectionTagging collection) {
		Optional<CollectionTagging> collectionTemp= collectionRepo.findById(collection.getEntityId());
	     
		if(collectionTemp == null)
 			 throw new EntityExistsException("The Specified Collection doesn't exists");
 
 		if (verifyCollection(collection.getInvoiceid(), collection.getNetamountcredieted()))
		{
	   		return collectionRepo.save(collection);
		}
		else
		{
			 throw new EntityExistsException("Amount of Collection can't be greater than Invoice");
		}	
	}

	public Optional<CollectionTagging> getCollectionById(Long id) {
		Optional<CollectionTagging> collectionTemp= collectionRepo.findById(id);
    	
		if (collectionTemp == null)
    	{
       		new RuntimeException("The Specified Collection doesn't exists");
    	}
       	return collectionTemp;
	}

	public CollectionTagging deleteCollection(Long id) {
		Optional<CollectionTagging> collectionTemp= collectionRepo.findById(id);
    	
		 if(collectionTemp== null)
       {
           throw new EntityNotFoundException("The Specified Collection does not exists");
       }

       if (collectionTemp.get().getIsDeleted()){
           throw new EntityNotFoundException("Collection already deleted");
       }
       else{
    	   collectionTemp.get().setIsDeleted(true);
        return collectionRepo.save(collectionTemp.get());
       }
	}

	public List<CollectionTaskView> taskByFilters(CollectionTaskView collection, Integer page, Integer size) {
		collection.setApprovalstatus("PENDING");
		 collection.setWorkflowname("COLLECTION_WORKFLOW");
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<CollectionTaskView> collectioneEx=Example.of(collection,em);
        
        List<CollectionTaskView> taskList;
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		taskList= taskViewRepo.findAll(collectioneEx, paging).getContent();
    	}
    	else
    	{
    		taskList= taskViewRepo.findAll(collectioneEx);
    	}
          
          long profileid = MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = getTask(profileid);
	       
	       List<CollectionTaskView> filteredData = taskList.stream()  
	         	     .filter(f->projectId.contains(f.getTaskid())).collect(Collectors.toList());
	         
	          return filteredData;
	}

	private List<Long> getTask(Long profileId) {
		return taskViewRepo.getTask(profileId);
	}

	public Optional<CollectionTaskMaster> taskByid(Long id) {
		Optional<CollectionTaskMaster> collectionTemp= taskRepo.findById(id);
    	
		if (collectionTemp == null)
    	{
       		new RuntimeException("The Specified Task doesn't exists");
    	}
       	return collectionTemp;
	}

	public CollectionTagging processInvoice(CollectionTaskMaster collectiontask) {
		
		Optional<CollectionTaskMaster> tcheck=taskRepo.findById(collectiontask.getEntityId());

        if (!tcheck.isPresent()){
            throw new RuntimeException("Invalid Task Specified");
        }

        if(!tcheck.get().getCollectionid().equals(collectiontask.getCollectionid()))
            throw new RuntimeException("Task Collection Id does not match with the Entity Collection Id");

       TaskResponse task = tmService.ProcessTask(collectiontask);
       
       Optional<CollectionTagging> collectionTemp= collectionRepo.findById(collectiontask.getCollectionid());
       if (!collectionTemp.isPresent()){
           throw new RuntimeException("Invalid Collection Specified");
       }
       
       CollectionTagging obj = collectionTemp.get();
       
       obj.setCollectionstatus(task.getStatus());

	   InvoiceMaster invoice = invoiceService.findInvoiceById(obj.getInvoiceid()).get();
       notificaton.sendNotification(task, obj.getProjectid(), 
    		   "Invoice Collection Approval", "Collection For Invoice :: " + invoice.getInvoiceno(), "COLLECTION"); 	   
      
       return collectionRepo.save(obj);
	}

	public CollectionTagging submitCollection(Long id) {
		Optional<CollectionTagging> collectionTemp= collectionRepo.findById(id);
	   	
		List<CollectionTaskMaster> tcheck = taskRepo.findByCollectionid(id);
        if (tcheck.size()>0){
            throw new RuntimeException("Workflow for this Collection has already been initiated");
        }

        CollectionTaskMaster tMaster= new CollectionTaskMaster();
        tMaster.setWorkflowName("COLLECTION_WORKFLOW");
        tMaster.setRemark("");
        tMaster.setCollectionid(id);;

        TaskResponse task = tmService.CreateTask(tMaster);
     	
        collectionTemp.get().setCollectionstatus(task.getStatus());
        task.setAction("APPROVED");//set for notification
		
        InvoiceMaster invoice = invoiceService.findInvoiceById(collectionTemp.get().getInvoiceid()).get();
        notificaton.sendNotification(task, collectionTemp.get().getProjectid(), 
     		   "Invoice Collection Approval", "Collection For Invoice :: " + invoice.getInvoiceno(),null); 	   
       
		return collectionRepo.save(collectionTemp.get());	
	}

	public List<CollectionTagging> getCollectionByInvoice(Long id) {
		return collectionRepo.getCollectionByInvoice(id);
	}

	public List<CollectionTagging> getCollectionList(Long projectid) {
		return collectionRepo.getCollectionList(projectid);
	}

	public Iterable<CollectionTaskView> historytaskByFilters(CollectionTaskView collection, Integer page, Integer size) {
		collection.setWorkflowname("COLLECTION_WORKFLOW");
       ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<CollectionTaskView> collectioneEx=Example.of(collection,em);
      
       List<CollectionTaskView> taskList;

       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
   		{
   			Pageable paging = PageRequest.of(page, size);
   			taskList= taskViewRepo.findAll(collectioneEx, paging).getContent();
   		}
       else
       {
    	   taskList= taskViewRepo.findAll(collectioneEx);
   		}
       List<CollectionTaskView> historyTask = taskList.stream()  
         	     .filter(f->!(f.getApprovalstatus().startsWith("PENDING"))).collect(Collectors.toList());
         
          return historyTask;
	}

	public List<SelectionList> monthlyOfCollectionReport(String startYear, String endYear) {
		return collectionViewRepo.monthlyOfCollectionReport(startYear, endYear);	
	}

	public List<CollectionView> monthlyCollectionReport(String month) {
		return collectionViewRepo.monthlyCollectionReport(month);	
	}

	public List<CollectionView> getCollectionList(CollectionView collection, Integer page, Integer size) {
		ExampleMatcher em=ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);

    	Example<CollectionView> collectionEx=Example.of(collection,em);
    	
    	if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
            return collectionViewRepo.findAll(collectionEx, paging).getContent();
    	}
    	else
    	{
    		 return collectionViewRepo.findAll(collectionEx);
    	}
	}

	public List<CollectionTaskView> getHistoryById(long id) {
		return taskViewRepo.getHistoryById(id);
	}

	public List<CollectionTaskView> getAllHistoryTasks(CollectionTaskView request, Integer page, Integer size) {
		request.setUpdatedby(MyPrincipal.getMyProfile().getUsername());
		    	
		        ExampleMatcher em=ExampleMatcher.matching()
		                           .withIgnoreNullValues()
		                           .withIgnoreCase()
		                           .withStringMatcher(StringMatcher.CONTAINING);
		
		       Example<CollectionTaskView> taskEx=Example.of(request,em);
		       
		       List<CollectionTaskView> taskList;
		       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		   	{
		   		Pageable paging = PageRequest.of(page, size);
		   		taskList = taskViewRepo.findAll(taskEx, paging).getContent();
		   	}
		   	else
		   	{
		   		taskList= taskViewRepo.findAll(taskEx);
		   	}
		       List<CollectionTaskView> historyTask = taskList.stream().filter(f->!(f.getApprovalstatus().startsWith("PENDING"))).collect(Collectors.toList());
		       
		       return historyTask;
	}

		
}
