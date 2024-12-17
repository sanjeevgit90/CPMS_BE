package aurionpro.erp.ipms.ordermgmt.purchaseordertask;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itextpdf.text.DocumentException;

import aurionpro.erp.ipms.jkdframework.common.TaskCountList;
import aurionpro.erp.ipms.jkdframework.organization.OrgRepository;
import aurionpro.erp.ipms.jkdframework.organization.Organization;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.jkdframework.workflow.WorkflowMaster;
import aurionpro.erp.ipms.jkdframework.workflow.WorkflowRespository;
import aurionpro.erp.ipms.ordermgmt.grntask.GrnTask;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderListView;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderMaster;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderOpenBravoDetails;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderOpenBravoRepository;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderService;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderView;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractService;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectRepository;
import aurionpro.erp.ipms.utility.AppProperties;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;

@Service
public class PoTaskMasterService {

	@Autowired
    PoTaskMasterRepository poTaskMasterRepository;
	
	@Autowired
    PurchaseOrderService poService;
    
    @Autowired
    TaskMasterService tmService;
    
    @Autowired
    PoTaskViewRepository poTaskViewRepository;
    
    @Autowired
    RateContractService rcService;
    
    @Autowired
    AllTaskViewRepository allTaskViewRepo;
    
    @Autowired
    private ProjectRepository projectRepo;
    
    @Autowired
    WorkflowRespository wfRepo;
    
    @Autowired
    private OrgRepository orgRepo;
    
    @Autowired
    private PurchaseOrderOpenBravoRepository purchaseOrderOpenBravoRepo;
    
    @Autowired
	private Environment env;
    
    @Autowired
    NotificationMailFormat notificaton;
    
	@Autowired
	AppProperties appProperties;

    public List<PoTaskMaster> getAllPoTasks(PoTaskMaster taskRequest){
    	ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PoTaskMaster> taskEx = Example.of(taskRequest, em);
        return poTaskMasterRepository.findAll(taskEx);
    }
    
    public PoTaskMaster getPoTaskById(long taskId){
        return (PoTaskMaster) tmService.getTaskById(taskId).get();
    }

    public PoTaskMaster sendPoForApproval(PoTaskMaster request){
    	
    	//saving po team record
    	PoTaskMaster pt = saveFirstTask(request);
    	if(pt==null)
    		return null;
        
    	PoTaskMaster tMaster=new PoTaskMaster();
    	/*if(request.getWorkflowName().equalsIgnoreCase(env.getProperty("POTYPEA")))
    		tMaster.setWorkflowName(env.getProperty("POTYPEA"));
    	if(request.getWorkflowName().equalsIgnoreCase(env.getProperty("POTYPEB")))
    		tMaster.setWorkflowName(env.getProperty("POTYPEB"));
    	if(request.getWorkflowName().equalsIgnoreCase(env.getProperty("OPENBRAVOPOSING")))
    		tMaster.setWorkflowName(env.getProperty("OPENBRAVOPOSING"));
    	if(request.getWorkflowName().equalsIgnoreCase(env.getProperty("OPENBRAVOPOIND")))
    		tMaster.setWorkflowName(env.getProperty("OPENBRAVOPOIND"));
    	if(request.getWorkflowName().equalsIgnoreCase(env.getProperty("OPENBRAVOPOMISC")))
    		tMaster.setWorkflowName(env.getProperty("OPENBRAVOPOMISC"));*/
    	
    	tMaster.setWorkflowName(request.getWorkflowName());
        tMaster.setRemark("");
        tMaster.setPoId(request.getPoId());
        tMaster.setPoRcFlag(request.getPoRcFlag());

        TaskResponse task = null;
        try {
        	task = tmService.CreateTask(tMaster);
        	task.setAction("APPROVED");
        	poService.updateApprovalStatus(request.getPoId(), task.getStatus());

        	PurchaseOrderMaster po = poService.getOrderById(request.getPoId()).get();
        	notificaton.sendNotification(task, po.getAccountName(), 
        			"PO Approval", "PO No :: " + po.getPurchaseOrderNo(), "PO");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return tMaster;
    }
    
    public PoTaskMaster sendPoForApprovalTypeB(PoTaskMaster request){
        
    	PoTaskMaster tMaster=new PoTaskMaster();
        tMaster.setWorkflowName("PO_APPROVAL_TYPE_B");
        tMaster.setRemark(null);
        tMaster.setPoId(request.getPoId());
        tMaster.setPoRcFlag(request.getPoRcFlag());

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        
        TaskResponse task = null;
        try {
        	task = tmService.CreateTask(tMaster);
        	poService.updateApprovalStatus(request.getPoId(), task.getStatus());
        	
        	PurchaseOrderMaster po = poService.getOrderById(request.getPoId()).get();
        	notificaton.sendNotification(task, po.getAccountName(), 
        			"PO Approval", "PO No :: " + po.getPurchaseOrderNo(), "PO");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return tMaster;
    }
    
    public PoTaskMaster sendPoForAmendment(PoTaskMaster request){
    	//saving po team record
    	request.setWorkflowName("PO_AMENDMENT");
    	PoTaskMaster pt = saveFirstTaskForAmend(request);
    	if(pt==null)
    		return null;
    	
    	PoTaskMaster tMaster=new PoTaskMaster();
    	tMaster.setWorkflowName("PO_AMENDMENT");
        tMaster.setRemark("");
        tMaster.setPoId(request.getPoId());
        tMaster.setPoRcFlag(request.getPoRcFlag());

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        
        TaskResponse task = null;
        try {
        	task = tmService.CreateTask(tMaster);
        	task.setAction("APPROVED");
        	poService.updateApprovalStatus(request.getPoId(), task.getStatus());

        	PurchaseOrderMaster po = poService.getOrderById(request.getPoId()).get();
        	notificaton.sendNotification(task, po.getAccountName(), 
        			"PO Amendment", "PO No :: " + po.getPurchaseOrderNo(), "PO");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return tMaster;
    }
    
    //cancellation of approved po that can be requested by po head
    public PoTaskMaster sendPoForCancellation(long poId){
        
    	PoTaskMaster tMaster=new PoTaskMaster();
        tMaster.setWorkflowName("PO_CANCELLATION");
        tMaster.setRemark("");
        tMaster.setPoId(poId);

        //Optional<PurchaseOrderMaster> po = purchaseOrderRepository.findById(poId);
        TaskResponse task = null;
        try {
	        task = tmService.CreateTask(tMaster);
	        task.setAction("APPROVED");
	        poService.updateApprovalStatus(poId, task.getStatus());
	
	        PurchaseOrderMaster po = poService.getOrderById(poId).get();
	    	notificaton.sendNotification(task, po.getAccountName(), 
	    			"PO Cancellation", "PO No :: " + po.getPurchaseOrderNo(), "PO");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return tMaster;
    }

    public PoTaskMaster processWorkflow(PoTaskMaster request){
    	TaskResponse task = null;
	//	String assignToUser = null, assignToRole = null;
        try {
        	/*if(request.getStageName().equalsIgnoreCase("DELIVERY HEAD")) {
        		//PurchaseOrderMaster po = poService.getOrderById(request.getPoId()).get();
        		//Project proj = projectRepo.findById(po.getAccountName()).get();
        		//we need to write the code for assigning the task to delivery head user.
        		msg = tmService.ProcessTask(request, assignToUser, assignToRole);
        	} else {
        		msg = tmService.ProcessTask(request);
        	}*/
        	
        	String poPDF = null;
        	if(request.getStageName().equalsIgnoreCase("PO TEAM") || request.getStageName().equalsIgnoreCase("PO TEAM SCSOFT")) {
        		poPDF = getPdfLinkOfPo(request.getPoId());
        		if(poPDF != null) {
        			if(StringUtils.isEmpty(request.getRemark()))
        				request.setRemark(":" + poPDF);
        			else
        				request.setRemark(request.getRemark() + ":" + poPDF);
        		}
        	}
        	
        	task = tmService.ProcessTask(request);
        	if(task == null) {
        		poService.updateApprovalStatus(request.getPoId(), "APPROVED");
            	request.setStageName("APPROVED");//setting new stage name in response
        	} else {
				//////////////
				if (task.getAssignToRole() != null && task.getAssignToRole().equals("SERVICE HEAD")) {// service head_

					Optional<PurchaseOrderMaster> podetails = poService.getOrderById(request.getPoId());

					Boolean isLevelExist = projectRepo.cheeckServiceHeadLevel(podetails.get().getAccountName(),
							appProperties.getWorkflowlevel, appProperties.getEmailid);

					if (isLevelExist) {
						TaskResponse newtask = null;
						PoTaskMaster newrequest = new PoTaskMaster();
						newrequest.setEntityId(task.getTaskid());
						newrequest.setPoId(request.getPoId());
						newrequest.setUploadFile(request.getUploadFile());
						newrequest.setPoRcFlag(request.getPoRcFlag());
						newrequest.setWorkflowType(request.getWorkflowType());
						newrequest.setStageName(appProperties.getWorkflowlevel);
						newrequest.setWorkflowName(request.getWorkflowName());
						newrequest.setAssignToRole(appProperties.getWorkflowlevel);
						newrequest.setAssignToUser(request.getAssignToUser());
						newrequest.setApprovalStatus("APPROVED");
						newrequest.setRemark("Automatically Approved");

						newtask = tmService.ProcessTask(newrequest);

						poService.updateApprovalStatus(request.getPoId(), newtask.getStatus());
						request.setStageName(newtask.getStatus());// setting new stage name in response

					}
					else {
						////////////
						// other SERVER HEAD except yogesh sir
						poService.updateApprovalStatus(request.getPoId(), task.getStatus());
						request.setStageName(task.getStatus());// setting new stage name in response
					}

				}
				else {

				////////////
        		poService.updateApprovalStatus(request.getPoId(), task.getStatus());
            	request.setStageName(task.getStatus());//setting new stage name in response
			}
        	}

        	PurchaseOrderMaster po = poService.getOrderById(request.getPoId()).get();
        	/*//for sending notification
        	if(!StringUtils.isEmpty(request.getPoRcFlag())) {
        		if(request.getPoRcFlag().equalsIgnoreCase("PO"))
            		notificaton.sendNotification(task, po.getAccountName(), "PO Approval", "PO No :: " + po.getPurchaseOrderNo(), "PO");
        		else if(request.getPoRcFlag().equalsIgnoreCase("AMEND"))
            		notificaton.sendNotification(task, po.getAccountName(), "PO Amendment", "PO No :: " + po.getPurchaseOrderNo(), "PO");
        	} else {
        		notificaton.sendNotification(task, po.getAccountName(), "PO Approval", "PO No :: " + po.getPurchaseOrderNo(), "PO");
        	}*/
        	
        	//for sending notification
        	List<String> emailList = null;

			if (po != null) {
				if (po.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
					emailList = poTaskViewRepository.getAllEmailListFromPoTask(po.getEntityId());
					if (emailList != null) {
						notificaton.sendNotificationToAllLevel(emailList, "PO No :: " + po.getPurchaseOrderNo());
					}
				} else {
					if (!StringUtils.isEmpty(request.getPoRcFlag())) {
						if (request.getPoRcFlag().equalsIgnoreCase("PO"))
							notificaton.sendNotification(task, po.getAccountName(), "PO Approval",
									"PO No :: " + po.getPurchaseOrderNo(), "PO");
						else if (request.getPoRcFlag().equalsIgnoreCase("AMEND"))
							notificaton.sendNotification(task, po.getAccountName(), "PO Amendment",
									"PO No :: " + po.getPurchaseOrderNo(), "PO");
					} else {
						notificaton.sendNotification(task, po.getAccountName(), "PO Approval",
								"PO No :: " + po.getPurchaseOrderNo(), "PO");
					}
				}
			}
        	
        	//for pushing po to openbravo
        	if(po != null) {
        		if(po.getApprovalStatus().equalsIgnoreCase("APPROVED")) {
    				PurchaseOrderOpenBravoDetails obp = purchaseOrderOpenBravoRepo.findByPurchaseOrderMaster(po);
    				if(obp != null) {
    					if(po.getApprovalStatus().equalsIgnoreCase("APPROVED") && obp.getPoPushedStatus().equalsIgnoreCase("PENDING")){
    						if(!StringUtils.isEmpty(po.getOrganisationId())){
    							Organization org = orgRepo.findById(po.getOrganisationId()).get();
    							if(org != null)
    								if(!StringUtils.isEmpty(org.getPushPo()))
    									if(org.getPushPo().equalsIgnoreCase("YES"))
    										poService.pushPoToOpenBravo(po.getEntityId());
    						}
    					}
    				}
    			}
        	}//for pushing po to openbravo
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return request;
    }

	public PageImpl<AllTaskView> getPendingPoTasksFromView(AllTaskView taskRequest, Integer page, Integer size) {
	/*	taskRequest.setApprovalStatus("PENDING");
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withMatcher("stageName", GenericPropertyMatcher.of(StringMatcher.EXACT)).withStringMatcher(StringMatcher.CONTAINING);
		Example<AllTaskView> taskEx = Example.of(taskRequest, em);
		List<AllTaskView> taskList = allTaskViewRepo.findAll(taskEx);
		
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();

		List<Long> taskId = getTask(profileId);

		List<AllTaskView> filteredData = taskList.stream()  
				.filter(f->taskId.contains(f.getEntityId())).collect(Collectors.toList());
*/
		AllTaskView purchaseOrder = castFilterValues(taskRequest);
		
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		
		List<AllTaskView> poList= allTaskViewRepo.getallpotaskbyfilterbysp(
				purchaseOrder.getPurchaseOrderNo(),
				purchaseOrder.getAccountName(),
				purchaseOrder.getSupplierName(),
				"PENDING",
				purchaseOrder.getAssignToRole(),
				purchaseOrder.getStageName(),
				profileId);
		
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
			{
				Pageable paging = PageRequest.of(page-1, size);
			    int start =  (int) paging.getOffset();
			    int end = Math.min((start + paging.getPageSize()), poList.size());
			     		
			    return new PageImpl<AllTaskView>(poList.subList(start, end), paging, poList.size());
			      
			}
			else
			{
			    return new PageImpl<AllTaskView>(poList);
		   	}
	}

	private AllTaskView castFilterValues(AllTaskView taskRequest) {
		long value = 0;
		
		if (taskRequest.getPurchaseOrderNo() == null)
		{
			taskRequest.setPurchaseOrderNo("");
		}
		else
		{
			taskRequest.setPurchaseOrderNo(taskRequest.getPurchaseOrderNo());
		}
		
		if (taskRequest.getAccountName() == null)
		{
			taskRequest.setAccountName(value);
		}
		else
		{
			taskRequest.setAccountName(taskRequest.getAccountName());
		}
			
		if (taskRequest.getSupplierName() == null)
		{
			taskRequest.setSupplierName(value);
		}
		else
		{
			taskRequest.setSupplierName(taskRequest.getSupplierName());
		}
		if (taskRequest.getStageName() == null)
		{
			taskRequest.setStageName("");
		}
		else
		{
			taskRequest.setStageName(taskRequest.getStageName());
		}
		
		if (taskRequest.getAssignToRole() == null)
		{
			taskRequest.setAssignToRole("");
		}
		else
		{
			taskRequest.setAssignToRole(taskRequest.getAssignToRole());
		}	
		return taskRequest;


	}

	private List<Long> getTask(Long profileId) {
		return allTaskViewRepo.getTask(profileId);
	}

	public Optional<PoTaskView> getPoTaskByIdView(long taskId) {
		return poTaskViewRepository.findById(taskId);
		//return (PoTaskView) tmService.getTaskById(taskId).get();
	}

	public PageImpl<AllTaskView> getHistoryPoTasksFromView(AllTaskView taskRequest, Integer page, Integer size) {
		taskRequest.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withMatcher("stageName", GenericPropertyMatcher.of(StringMatcher.EXACT)).withStringMatcher(StringMatcher.CONTAINING);
		Example<AllTaskView> taskEx = Example.of(taskRequest, em);
		//return poTaskViewRepository.findAll(taskEx);
		List<AllTaskView> taskList= allTaskViewRepo.findAll(taskEx);

		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		List<Long> taskId = getTask(profileId);
		List<AllTaskView> filteredData = taskList.stream().filter(f->taskId.contains(f.getEntityId())).collect(Collectors.toList());

		//List<CollectionTaskView> taskList= taskViewRepo.findAll(collectioneEx);
		List<AllTaskView> historyTask = filteredData.stream().filter(f->!(f.getApprovalStatus().startsWith("PENDING"))).collect(Collectors.toList());

		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
			{
				Pageable paging = PageRequest.of(page-1, size);
			    int start =  (int) paging.getOffset();
			    int end = Math.min((start + paging.getPageSize()), historyTask.size());
			     		
			    return new PageImpl<AllTaskView>(historyTask.subList(start, end), paging, historyTask.size());
			      
			}
			else
			{
			    return new PageImpl<AllTaskView>(historyTask);
		   	}
	}
	
	public List<PoTaskView> getPoHistoryById(long poId) {
		return poTaskViewRepository.findByPoId(poId);
	}

	public List<TaskCountList> getPoTaskCount() {
		
		 Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		 return poTaskViewRepository.getPOTaskCount(profileId);
		 
		 /*BigInteger count = poTaskViewRepository.getPOTaskCount(profileId);
		 DashboardCount dt = new DashboardCount();
		 dt.setName("PO Task Count");
		 dt.setCount(count);
		 return dt;*/
	}
	
	public PoTaskMaster saveFirstTask(PoTaskMaster request){
		if (request.getWorkflowName() == null)
            throw new RuntimeException("Workflow not Specified");
		
		Optional<WorkflowMaster> wf = wfRepo.findById(request.getWorkflowName());
        if (!wf.isPresent()) {
            throw new RuntimeException("Specified Workflow does not exist");
        }
        
        List<PoTaskMaster> tcheck = poTaskMasterRepository.findByPoId(request.getPoId());
		   if (tcheck.size()>0){
		            throw new RuntimeException("Workflow for the PO has already been initiated");
		    } 
        
        String poPDF = null;
		poPDF = getPdfLinkOfPo(request.getPoId());
		String remark_link = (poPDF != null) ? request.getRemark() + ":" + poPDF : request.getRemark();
		
    	PoTaskMaster tMaster = null;
        try {
            tMaster = new PoTaskMaster();
        	tMaster.setPoId(request.getPoId());
            tMaster.setPoRcFlag(request.getPoRcFlag());
            tMaster.setStageName("PO TEAM");
            tMaster.setWorkflowName(wf.get().getWorkflowName());
           	tMaster.setRemark(remark_link);
            tMaster.setAssignToRole(wf.get().getAssignToRole());
            tMaster.setAssignToUser(wf.get().getAssignToUser());
            tMaster.setApprovalStatus("SUBMITTED");
            tMaster.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
            tMaster.setUpdatedDate(new Date().getTime());
            poTaskMasterRepository.save(tMaster);
		} catch (Exception e) {
			tMaster = null;
			e.printStackTrace();
		}
        return tMaster;
    }
	
	public String getPdfLinkOfPo(long poId) {
		String poPDF = null;
		try {
			poPDF = poService.writePdf(poId);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return poPDF;
	}

	public List<TaskCountList> getTaskCount() {
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		return allTaskViewRepo.getTaskCount(profileId);
	}
	
	public PoTaskMaster saveFirstTaskForAmend(PoTaskMaster request){
		if (request.getWorkflowName() == null)
            throw new RuntimeException("Workflow not Specified");
		
		Optional<WorkflowMaster> wf = wfRepo.findById(request.getWorkflowName());
        if (!wf.isPresent()) {
            throw new RuntimeException("Specified Workflow does not exist");
        }
        
    	PoTaskMaster tMaster = null;
        try {
            tMaster = new PoTaskMaster();
        	tMaster.setPoId(request.getPoId());
            tMaster.setPoRcFlag(request.getPoRcFlag());
            tMaster.setStageName("PO TEAM");
            tMaster.setWorkflowName(wf.get().getWorkflowName());
           	tMaster.setRemark(request.getRemark());
            tMaster.setAssignToRole(wf.get().getAssignToRole());
            tMaster.setAssignToUser(wf.get().getAssignToUser());
            tMaster.setApprovalStatus("AMENDMENT REQUESTED");
            tMaster.setUpdatedBy(MyPrincipal.getMyProfile().getUsername());
            tMaster.setUpdatedDate(new Date().getTime());
            poTaskMasterRepository.save(tMaster);
		} catch (Exception e) {
			tMaster = null;
			e.printStackTrace();
		}
        return tMaster;
    }
}
