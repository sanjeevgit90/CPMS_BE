package aurionpro.erp.ipms.ticketmgmt.ticketmaster;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetMaster;
import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetMasterRepository;
import aurionpro.erp.ipms.authorization.role.Role;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileRepository;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterRepository;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.jkdframework.workflow.WorkflowRespository;
import aurionpro.erp.ipms.projectmgmt.projectmaster.Project;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectRepository;
import aurionpro.erp.ipms.ticketmgmt.ticketcounter.TicketCounter;
import aurionpro.erp.ipms.ticketmgmt.ticketcounter.TicketCounterRepository;
import aurionpro.erp.ipms.ticketmgmt.ticketreports.TicketReportRequest;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;
import aurionpro.erp.ipms.utility.ProjectUtil;


@Service()
public class TicketService {
	
	@Autowired
    TicketRepository ticketRepo;
	
	@Autowired
    WorkflowRespository wfRepo;
	
	@Autowired
	TicketTaskRepository ticketTaskRepository; 
	
	@Autowired
	TicketTaskViewRepository ticketTaskViewRepository;
	
	@Autowired
	TicketViewRepository ticketViewRepo;
	
	@Autowired
	TaskMasterService tmService;
	
	@Autowired
    private ProjectRepository projectRepo;
	
	@Autowired
	ProjectUtil projectFilterService;  
	
	@Autowired
	UserProfileService userService;  
	
	@Autowired
	AssetMasterRepository assetRepo;  

	
	 @Autowired
	 private UserProfileRepository upRepo;
	 
	 @Autowired
	 private TicketCounterRepository countRepo;
	 
	 @Autowired
		TaskMasterRepository taskRepo;

	   @Autowired
	   NotificationMailFormat notificaton;

	public TicketMaster save(@Valid TicketMaster ticketMaster ) {
		
		String ticketNo = getTicketNo(ticketMaster.getAccountName());
	
		ticketMaster.setTicketNo(ticketNo);
		TicketMaster ticketObj = ticketRepo.save(ticketMaster);
		
		SelectionList userDta = upRepo.getEmailMobileNoByUserProfile(ticketMaster.getAssignTo());
			       
			if(ticketMaster.getTicketStatus().equalsIgnoreCase("OPEN")) {
			TicketTaskHistory ticketTaskHistory = new TicketTaskHistory();
			if(ticketObj!=null) {		
				ticketTaskHistory.setWorkflowName("TICKET_WORKFLOW");
				ticketTaskHistory.setRemark("");
				ticketTaskHistory.setTicketNo(ticketObj.getTicketNo());
				ticketTaskHistory.setTicketId(ticketObj.getEntityId());
				ticketTaskHistory.setAssignTo(ticketObj.getAssignTo());
				ticketTaskHistory.setAssignToUser(userDta.getSelectionid());
				ticketTaskHistory.setAssignToRole("ROLE_CHM");
				
				TaskResponse task = tmService.CreateTask(ticketTaskHistory);
			notificaton.sendTicketNotification(task, ticketObj);	
	       }
		}
			return ticketObj;
	}
	
	public TicketMaster saveAsDraft(@Valid TicketMaster ticketMaster) {
		
		String ticketNo = getTicketNo(ticketMaster.getAccountName());
	ticketMaster.setTicketStatus("DRAFT");
		ticketMaster.setTicketNo(ticketNo);
		TicketMaster ticketObj = ticketRepo.save(ticketMaster);
	//	notificaton.sendTicketNotification(null, ticketObj);	
		return ticketObj;
	}
	

	private String getTicketNo(Long projectid) {
		Optional<Project> project = projectRepo.findById(projectid);
		String pin = project.get().getProjectPin();
		
		if (pin != null)
		{
			Optional<TicketCounter> countCheck = countRepo.findById(pin);
			TicketCounter count;
			long counter = 1 ;
			if (countCheck.isPresent())
			{
				count = countCheck.get();
				counter = count.getCounter()+1;
				count.setCounter(counter);
				countRepo.save(count);
			}
			else
			{
				count =new TicketCounter();
				
				count.setProjectPin(pin);
				count.setCounter(1);
				countRepo.save(count);
			}
			
			String ticketNumber = "" + pin.toUpperCase() + Calendar.getInstance().get(Calendar.YEAR) + String.format("%08d",counter);

			return ticketNumber;
		}
		else
		{
			throw new RuntimeException("Ticket can't be created for this project");
		}
		
	}


	public TicketMaster updateTicket(@Valid TicketMaster ticketMaster) {
		
		SelectionList userDta = upRepo.getEmailMobileNoByUserProfile(ticketMaster.getAssignTo());
		
		TicketMaster ticketObj = ticketRepo.save(ticketMaster);
		if(ticketMaster.getTicketStatus().equalsIgnoreCase("OPEN")) {
			TicketTaskHistory ticketTaskHistory = new TicketTaskHistory();
			if(ticketObj!=null) {		
				ticketTaskHistory.setWorkflowName("TICKET_WORKFLOW");
				ticketTaskHistory.setRemark("");
				ticketTaskHistory.setTicketNo(ticketMaster.getTicketNo());
				ticketTaskHistory.setTicketId(ticketObj.getEntityId());
				ticketTaskHistory.setAssignTo(ticketObj.getAssignTo());
				ticketTaskHistory.setAssignToUser(userDta.getSelectionid());
				ticketTaskHistory.setAssignToRole("ROLE_CHM");
				 tmService.CreateTask(ticketTaskHistory);
			}
         }
		return ticketObj;
		
	}
	
           
	public TicketTaskHistory processWorkflow(@RequestBody TicketTaskRequest ticketTaskRequest, Long assignName){

          Optional<TicketTaskHistory> tcheck=ticketTaskRepository.findById(ticketTaskRequest.getEntityId());
          
          Optional<TicketMaster> ticketTemp= ticketRepo.findByTicketNo(tcheck.get().getTicketNo());
			TicketMaster ticketObj =ticketTemp.get();
			if (ticketTaskRequest.getTicketClosedTime() != null)
			{
				if (ticketObj.getDueDate() > ticketTaskRequest.getTicketClosedTime()) {
				    throw new RuntimeException("Ticket Created Date Time  should be less than Ticket Closed Date Time");
			    }
			}

			if (!tcheck.isPresent()){
			    throw new RuntimeException("Invalid Task Specified");
			}
 
			if(!tcheck.get().getTicketNo().equals(ticketTaskRequest.getTicketNo()))
			    throw new RuntimeException("Task Id does not match with the Entity Name");
						
			TicketTaskHistory tkt = new TicketTaskHistory();
			tkt.setApprovalStatus(ticketTaskRequest.getApprovalStatus());
			tkt.setEntityId(tcheck.get().getEntityId());
			tkt.setCaptureImg(ticketTaskRequest.getCaptureImg());
			tkt.setLatitude(ticketTaskRequest.getLatitude());
			tkt.setLongitude(ticketTaskRequest.getLongitude());
			tkt.setRemark(ticketTaskRequest.getRemark());
			tkt.setStageName(tcheck.get().getStageName());
			tkt.setWorkflowName(tcheck.get().getWorkflowName());
			tkt.setVendorname(ticketTaskRequest.getVendorname());
			tkt.setTicketNo(tcheck.get().getTicketNo());
			tkt.setTicketId(tcheck.get().getTicketId());
			tkt.setAssignTo(tcheck.get().getAssignTo());
			if (ticketTaskRequest.getApprovalStatus().equalsIgnoreCase("ESCALATED"))
			{
				tkt.setEscalatedOn(System.currentTimeMillis());
				tkt.setEscalatedTo(ticketTaskRequest.getApprovalStatus()+" To "+ticketTaskRequest.getVendorname());
			}
			
			
			TaskResponse task;
			if (assignName != null)
			{
				SelectionList userDta = upRepo.getEmailMobileNoByUserProfile(assignName);
				tcheck.get().setAssignToUser(userDta.getSelectionid());
				task =  tmService.ProcessTask(tkt, userDta.getSelectionid(), tcheck.get().getAssignToRole());
			}
			else
			{
				task =  tmService.ProcessTask(tkt);
			}
		
			ticketObj.setTicketStatus(task.getStatus());
			ticketObj.setClassifications(ticketTaskRequest.getClassifications());
			
			if(ticketTaskRequest.getTicketClosedTime() != null)
			{
				ticketObj.setTicketClosedTime(ticketTaskRequest.getTicketClosedTime());
			}
			if(ticketTaskRequest.getResolution() != null)
			{
				ticketObj.setResolution(ticketTaskRequest.getResolution());
			}
			ticketObj.setTicketCategory(ticketTaskRequest.getTicketCategory());
			ticketObj.setTicketSubcategory(ticketTaskRequest.getTicketSubcategory());
						
			ticketObj.setVehiclesUsed(ticketTaskRequest.getTrip());
			if (assignName != null)
			{
				ticketObj.setAssignTo(assignName);
			}
			
			ticketRepo.save(ticketObj);
			
			notificaton.sendTicketNotification(task, ticketObj);	
			
			return tcheck.get();
			
    }
	
    
    public PageImpl<TicketTaskView> getTicketTaskByFilter(TicketTaskView ticketTaskView, Integer page, Integer size){
    	Long userId = MyPrincipal.getMyProfile().getUserProfileId();
    	Boolean flag = false;
    	List<Role> roleList = upRepo.findById(userId).get().getRoles();
    	for (Role role:roleList)
    	{
    		if (role.getRolename().equalsIgnoreCase("ROLE_CHM"))
    		{
    			flag = true;
    		}
    	}
    	
    	if (!flag)
    	{
    		ticketTaskView.setAssignToUser(MyPrincipal.getMyProfile().getUsername());
    	}
    	ticketTaskView.setApprovalStatus("PENDING");
        ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<TicketTaskView> ticketTaskEx=Example.of(ticketTaskView,em);
       
       List<TicketTaskView> ticketTaskList =  ticketTaskViewRepository.findAll(ticketTaskEx);
   	    Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
       
	   List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	       
	   List<TicketTaskView> filteredTicket = ticketTaskList.stream()  
	         	     .filter(f->projectId.contains(f.getAccountname())).collect(Collectors.toList());
	         
	   if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	   {
		 Pageable paging = PageRequest.of(page-1, size);
   	     int start =  (int) paging.getOffset();
          int end = Math.min((start + paging.getPageSize()), filteredTicket.size());
    		
          return new PageImpl<TicketTaskView>(filteredTicket.subList(start, end), paging, filteredTicket.size());
     
	   }
   		else
   		{
   			return new PageImpl<TicketTaskView>(filteredTicket);
   		}
   }
    
    public PageImpl<TicketMasterView> getTicketByFilter(TicketReportRequest tkt, Integer page, Integer size, String sort, String column){
	Long userId = MyPrincipal.getMyProfile().getUserProfileId();
		
        TicketReportRequest ticketRequest = castFilterValues(tkt);
		List<TicketMasterView> ticketList= ticketViewRepo.getTicketByFilter(ticketRequest.getTicketNo(),
				ticketRequest.getAccountName(), ticketRequest.getPriority(), 
				ticketRequest.getState(),ticketRequest.getDistrict(),ticketRequest.getLocation(),ticketRequest.getTicketStatus(),
				ticketRequest.getTicketTitle(),ticketRequest.getAssetid(),
				ticketRequest.getFromDate(), ticketRequest.getToDate(),ticketRequest.getTicketOwner(),userId, sort, column);
	
	        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	           Pageable paging = PageRequest.of(page-1, size);
			   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), ticketList.size());
	     		
	           return new PageImpl<TicketMasterView>(ticketList.subList(start, end), paging, ticketList.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<>(ticketList);
	    	}
   }
    
    /*
      public PageImpl<TicketMasterView> getTicketByFilter(TicketReportRequest tkt, Integer page, Integer size){
    	TicketMasterView ticket = new TicketMasterView();
    	ticket.setTicketNo(tkt.getTicketNo());
    	ticket.setState(tkt.getState());
    	ticket.setAccountName(tkt.getAccountName());
    	ticket.setPriority(tkt.getPriority());
    	ticket.setDistrict(tkt.getDistrict());
    	ticket.setLocation(tkt.getLocation());
    	ticket.setTicketStatus(tkt.getTicketStatus());
    	ticket.setTicketTitle(tkt.getTicketTitle());
    	ticket.setAssetId(tkt.getAssetid());
        ticket.setIsdeleted(false);
                ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<TicketMasterView> ticketEx=Example.of(ticket,em);
       List<TicketMasterView> tktList= ticketViewRepo.findAll(ticketEx);
 		
       Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
       List<Long> id;
       if( tkt.getFromDate()!=null && tkt.getToDate()!= null )
       {
    	   id = ticketViewRepo.getIncidentTickets(profileId, tkt.getFromDate(),tkt.getToDate());  
       }
       else if( tkt.getFromDate()!=null && tkt.getToDate()== null )
       {
    	   id = ticketViewRepo.getTicketFromDate(profileId, tkt.getFromDate());  
       }
       else if( tkt.getFromDate()==null && tkt.getToDate()!= null )
       {
    	  id = ticketViewRepo.getTicketToDate(profileId, tkt.getFromDate());  
       }
       else
       {
    	  id = ticketViewRepo.getIncidentTickets(profileId); 
       }
		   List<TicketMasterView> filteredProject = tktList.stream()  
		         	     .filter(f->id.contains(f.getEntityId())).collect(Collectors.toList());
	       
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
			 Pageable paging = PageRequest.of(page-1, size);
	    	   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	     		
	           return new PageImpl<TicketMasterView>(filteredProject.subList(start, end), paging, filteredProject.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<TicketMasterView>(filteredProject);
	    	}
       
   }
     * */



//	public Optional<TicketTaskView> findById(Long entiyid) {
//		Optional<TicketTaskView> taskObj = ticketTaskRepository.findById(entityId);
//		if (taskObj == null)
//    	{
//    		throw new RuntimeException("The Specified Asset doesn't exists");
//    	}
//		return taskObj;
//		
//    	}
	
	public Optional<TicketTaskHistory> findById(Long entityId) {
		Optional<TicketTaskHistory> taskObj = ticketTaskRepository.findById(entityId);
    	
    	if (taskObj == null)
    	{
    		throw new RuntimeException("The Specified Task doesn't exists");
    	}
		return taskObj;
	}
	
    
	
	public Optional<TicketMaster> findTicketById(Long entityId) {
		Optional<TicketMaster> assetObj = ticketRepo.findById(entityId);
    	
    	if (assetObj == null)
    	{
    		throw new RuntimeException("The Specified Asset doesn't exists");
    	}
		return assetObj;
	}
	  
    @GetMapping()
	public List<TicketTaskHistory> getHistoryTicketList(String ticketNo) {
		return ticketTaskRepository.getHistoryTicketList(ticketNo);
	}


	public Optional<TicketMasterView> findTicketViewById(Long entityId) {
		// TODO Auto-generated method stub
		return ticketViewRepo.findById(entityId);
	}


	public PageImpl<TicketTaskView> getTicketHistoryByFilter(TicketTaskView ticketTaskView, Integer page, Integer size) {
		
    	ticketTaskView.setAssignToUser(MyPrincipal.getMyProfile().getUsername());
    	
        ExampleMatcher em=ExampleMatcher.matching()
                           .withIgnoreNullValues()
                           .withIgnoreCase()
                           .withStringMatcher(StringMatcher.CONTAINING);

       Example<TicketTaskView> ticketTaskEx=Example.of(ticketTaskView,em);
       List<TicketTaskView> taskList =ticketTaskViewRepository.findAll(ticketTaskEx);
       List<TicketTaskView> historyTask = taskList.stream().filter(f->!(f.getApprovalStatus().startsWith("PENDING"))).collect(Collectors.toList());
       
       
       if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
       {
		 Pageable paging = PageRequest.of(page-1, size);
		 	int start =  (int) paging.getOffset();
          int end = Math.min((start + paging.getPageSize()), historyTask.size());
    		
          return new PageImpl<TicketTaskView>(historyTask.subList(start, end), paging, historyTask.size());
          
   		}
   		else
   		{
   			return new PageImpl<TicketTaskView>(historyTask);
   		}
  
	}

	public Optional<TicketMaster> ticketDataById(Long entityId) {
		Optional<TicketMaster>  tkt = ticketRepo.findById(entityId);
		 AssetMaster asset = assetRepo.findById(tkt.get().getAssetid()).get();
		 tkt.get().setAssetName(asset.getAssetname());
		 return tkt;
		 
	}
	
	 private TicketReportRequest castFilterValues(TicketReportRequest tkt) {
		 TicketReportRequest ticketRequest = new TicketReportRequest();
			long value = 0;
			if (tkt.getTicketNo() == null)
			{
				ticketRequest.setTicketNo("");
			}
			else
			{
				ticketRequest.setTicketNo(tkt.getTicketNo());
			}
			
			if (tkt.getAccountName() == null)
			{
				ticketRequest.setAccountName(value);
			}
			else
			{
				ticketRequest.setAccountName(tkt.getAccountName());
			}
			
			if (tkt.getPriority() == null)
			{
				ticketRequest.setPriority("");
			}
			else
			{
				ticketRequest.setPriority(tkt.getPriority());
			}
			if (tkt.getState() == null)
			{
				ticketRequest.setState("");
			}
			else
			{
				ticketRequest.setState(tkt.getState());
			}
			
			if (tkt.getDistrict() == null)
			{
				ticketRequest.setDistrict("");
			}
			else
			{
				ticketRequest.setDistrict(tkt.getDistrict());
			}
			
			if (tkt.getLocation() == null)
			{
				ticketRequest.setLocation("");
			}
			else
			{
				ticketRequest.setLocation(tkt.getLocation());
			}
			if (tkt.getTicketStatus() == null)
			{
				ticketRequest.setTicketStatus("");
			}
			else
			{
				ticketRequest.setTicketStatus(tkt.getTicketStatus());
			}
			
			if (tkt.getTicketTitle() == null)
			{
				ticketRequest.setTicketTitle("");
			}
			else
			{
				ticketRequest.setTicketTitle(tkt.getTicketTitle());
			}
			
			if (tkt.getAssetid() == null)
			{
				ticketRequest.setAssetid(value);
			}
			else
			{
				ticketRequest.setAssetid(tkt.getAssetid());
			}
			if (tkt.getFromDate() == null)
			{
				ticketRequest.setFromDate(value);
			}
			else
			{
				ticketRequest.setFromDate(tkt.getFromDate());
			}
			if (tkt.getToDate() == null)
			{
				ticketRequest.setToDate(value);
			}
			else
			{
				ticketRequest.setToDate(tkt.getToDate());
			}
			if (tkt.getTicketOwner() == null)
			{
				ticketRequest.setTicketOwner(value);
			}
			else
			{
				ticketRequest.setTicketOwner(tkt.getTicketOwner());
			}
				
			return ticketRequest;
		}

	public TicketMaster reopenTicketById(Long id) {
		TicketMaster ticket = ticketRepo.findById(id).get();
		ticket.setTicketStatus("REOPENED");
		ticket.setTicketClosedTime(null);
		ticket.setAssignTo(null);
		
		TicketMaster tkt = ticketRepo.save(ticket);
		
		if (tkt != null)
		{
			TicketTaskHistory ticketTaskHistory= new TicketTaskHistory();
			ticketTaskHistory.setWorkflowName("TICKET_WORKFLOW");
			ticketTaskHistory.setRemark("");
			ticketTaskHistory.setTicketNo(ticket.getTicketNo());
			ticketTaskHistory.setTicketId(ticket.getEntityId());
			ticketTaskHistory.setStageName("TICKET_REOPENED");
			ticketTaskHistory.setAssignTo(null);
			ticketTaskHistory.setAssignToUser("NONE");
			ticketTaskHistory.setAssignToRole("REOPENED BY ADMIN");
			ticketTaskHistory.setApprovalStatus("REOPENED");
			ticketTaskHistory.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
			ticketTaskRepository.save(ticketTaskHistory);
			

			TicketTaskHistory task= new TicketTaskHistory();
			task.setWorkflowName("TICKET_WORKFLOW");
			task.setRemark("");
			task.setTicketNo(ticket.getTicketNo());
			task.setTicketId(ticket.getEntityId());
			task.setAssignTo(null);
			task.setAssignToUser(null);
			task.setAssignToRole("ROLE_CHM");
			
			tmService.CreateTask(task);
		}
		
		return tkt;
	}

	public TicketMaster updateTicketData(TicketMaster ticketMaster) {
		return ticketRepo.save(ticketMaster);
	}

	public Page<TicketMasterView> getTicketByFilterWithoutBodyRequest(String filter, Integer page, Integer size,
			String sort, String column) {
		
		TicketMasterView ticket = new TicketMasterView();
		ticket.setTicketNo(filter);
		
		 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

		 Example<TicketMasterView> ticketEx=Example.of(ticket,em);
		Pageable paging;
		 if (sort.equalsIgnoreCase("DESC"))
	       {
			   paging = PageRequest.of(page-1, size,Sort.by(column).descending());
	       }else
	       {
	    	   paging = PageRequest.of(page-1, size,Sort.by(column).ascending());
	       }
  		
  		return ticketViewRepo.findAll(ticketEx, paging);

	}
    
	
}
