package aurionpro.erp.ipms.assetmgmt.interdistrictdeliverychallan;

import java.util.Calendar;
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

import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetMasterService;
import aurionpro.erp.ipms.assetmgmt.common.DCAssetView;
import aurionpro.erp.ipms.assetmgmt.deliverychallan.CourierDetailsRequest;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterService;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectService;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.NotificationMailFormat;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service()
public class InterDistrictDeliveryChallanService {
	
	@Autowired
    InterDistrictDeliveryChallanRepository dcRepo;
	
	@Autowired
    InterDistrictDCTaskMasterRepository taskRepo;
	
	@Autowired
    InterDistrictDCViewRepository dcViewRepo;
	
	@Autowired
	TaskMasterService tmService;
	
    @Autowired
    ProjectUtil projectFilterService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	AssetMasterService assetService;

    @Autowired
    NotificationMailFormat notificaton;
   	
	public InterDistrictDeliveryChallan createDC(InterDistrictDeliveryChallan dc) {
		
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR); 
		
		String dcNo = getDCNumber(dc.getProjectname());
		String projectPin = projectService.getProjectPin(dc.getProjectname());
		
		dc.setDcno("Aurionpro/"+projectPin+"/"+year+"/"+"IDDC"+dcNo);
		
		dc.setPrintflag(false);
		dc.setDcStatus("PENDING");
		return dcRepo.save(dc);	
		
	}

	private String getDCNumber(long projectName) {
		List<String> dcNoList = dcRepo.getDCNumber(projectName);
		String dcNo = "0001";
		if (dcNoList.size() !=0)
		{
			String dcNotemp= (String)dcNoList.get(0);
			Integer dcNo1= 0;
			 String [] arrOfStr = dcNotemp.split("IDDC");
		        dcNo1= Integer.parseInt(arrOfStr[1]);
		        dcNo = String.format("%04d",dcNo1 + 1);
		}
		return dcNo;
	}

	public InterDistrictDeliveryChallan updateDC(InterDistrictDeliveryChallan dc) {
		Optional<InterDistrictDeliveryChallan> dcTemp= dcRepo.findById(dc.getEntityId());
	   	    	 
	   	if (dcTemp == null)
	   		throw new EntityExistsException("The Specified Delivery Challan doesn't exists");
	   		 
	   	if(!(dcTemp.get().getDcno().equalsIgnoreCase(dc.getDcno())))
	       throw new RuntimeException("DC No does not match with the Entity Dc No.");	 
	   		
		 return dcRepo.save(dc);
	}

	public Optional<InterDistrictDeliveryChallan> findDCById(Long id) {
		Optional<InterDistrictDeliveryChallan> dcObj = dcRepo.findById(id);
    	
    	if (dcObj == null)
    	{
    		throw new RuntimeException("The Specified Delivery Challan doesn't exists");
    	}
    	List<DCAssetView> dcAssetList = dcRepo.findAssetByDcid(dcObj.get().getEntityId());
    	dcObj.get().setAssetRecord(dcAssetList);
		return dcObj;
	}

	public InterDistrictDeliveryChallan deleteDC(Long id) {
		 Optional<InterDistrictDeliveryChallan> dcList= dcRepo.findById(id);

	        if(dcList== null)
	        {
	            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
	        }

	        if (dcList.get().getIsDeleted()){
	            throw new EntityNotFoundException("Record already deleted");
	        }
	        else{
	        	dcList.get().setIsDeleted(true);
	        	 return dcRepo.save(dcList.get());
	        }
	}


	public InterDistrictDeliveryChallan processDC(InterDistrictDCTaskMaster dc) {
		Optional<InterDistrictDCTaskMaster> tcheck=taskRepo.findById(dc.getEntityId());

        if (!tcheck.isPresent()){
            throw new RuntimeException("Invalid Task Specified");
        }

        if(!tcheck.get().getDcno().equals(dc.getDcno()))
            throw new RuntimeException("Task Id does not match with the Entity Name");

       TaskResponse task = tmService.ProcessTask(dc);
       
       Optional<InterDistrictDeliveryChallan> dcTemp= dcRepo.findByDcno(dc.getDcno());
       if (!dcTemp.isPresent()){
           throw new RuntimeException("Invalid Dc No Specified");
       }
       
       InterDistrictDeliveryChallan obj = dcTemp.get();
       
       obj.setDcStatus(task.getStatus());
      
       if (task.getStatus().equals("RECEIVED"))
       {
    	   assetService.updateAssetStatus(obj.getAsset(), obj.getShippedto());
       }
      
       notificaton.sendNotification(task, dcTemp.get().getProjectname(), "Inter District DC Approval", "DC No :: " + dc.getDcno(), null);

       return dcRepo.save(obj);
	}

	public List<InterDistrictDCView> searchDC(InterDistrictDCView dc, Integer page, Integer size) {
		dc.setIsdeleted(false);
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<InterDistrictDCView> dcEx=Example.of(dc,em);
        
        List<InterDistrictDCView> dcList;
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		dcList = dcViewRepo.findAll(dcEx, paging).getContent();
    	}
    	else
    	{
    		dcList = dcViewRepo.findAll(dcEx);
    	}
        Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	       
	       List<InterDistrictDCView> filteredProject = dcList.stream()  
	         	     .filter(f->projectId.contains(f.getProjectid())).collect(Collectors.toList());
	         
	          return filteredProject;
	}

	public InterDistrictDeliveryChallan submitDC(Long id) {
		Optional<InterDistrictDeliveryChallan> dcTemp= dcRepo.findById(id);
	   	
		List<InterDistrictDCTaskMaster> tcheck = taskRepo.findByDcno(dcTemp.get().getDcno());
        if (tcheck.size()>0){
            throw new RuntimeException("Workflow for the DC No has already been initiated");
        }

        InterDistrictDCTaskMaster tMaster=new InterDistrictDCTaskMaster();
        tMaster.setWorkflowName("INTER_DISTRICT_DC");
        tMaster.setRemark(dcTemp.get().getRemark());
        tMaster.setDcno(dcTemp.get().getDcno());

        TaskResponse task = tmService.CreateTask(tMaster);
     	
        dcTemp.get().setDcStatus(task.getStatus());
        task.setAction("APPROVED");//set for notification
		
        notificaton.sendNotification(task, dcTemp.get().getProjectname(), "Inter District DC Approval", 
        		"DC No :: " + dcTemp.get().getDcno(), null);

		return dcRepo.save(dcTemp.get());	
	}

	public Optional<InterDistrictDCView> viewDCById(Long id) {
		Optional<InterDistrictDCView> dcObj = dcViewRepo.findById(id);
    	
    	if (dcObj == null)
    	{
    		throw new RuntimeException("The Specified Delivery Challan doesn't exists");
    	}
    	List<DCAssetView> dcAssetList = dcRepo.findAssetByDcid(dcObj.get().getEntityId());
    	dcObj.get().setAsset(dcAssetList);
    	
		return dcObj;
	}

	public Optional<InterDistrictDCTaskMaster> taskByid(Long id) {
		return taskRepo.findById(id);
	}

	public InterDistrictDCView disablePrint(Long id, InterDistrictDeliveryChallan dc) {
		 Optional<InterDistrictDeliveryChallan> dcList= dcRepo.findById(id);

	        if(dcList== null)
	        {
	            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
	        }

	       
	        	dcList.get().setPrintflag(true);
	        	dcList.get().setChallanattachment(dc.getChallanattachment());
	        	  dcRepo.save(dcList.get());
	        	  return viewDCById(id).get();
	}

	public InterDistrictDeliveryChallan enableUpload(Long id) {
		Optional<InterDistrictDeliveryChallan> dcList= dcRepo.findById(id);

        if(dcList== null)
        {
            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
        }

          	dcList.get().setUploadflag(true);
        	 return  dcRepo.save(dcList.get());
	}

	public InterDistrictDeliveryChallan addCourierDetails(CourierDetailsRequest dc, Long id) {
		 Optional<InterDistrictDeliveryChallan> dcList= dcRepo.findById(id);

	        if(dcList== null)
	        {
	            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
	        }

	        	dcList.get().setCourierdate(dc.getCourierdate());
	        	dcList.get().setCourierno(dc.getCourierno());
	        	dcList.get().setCourierDetails(dc.getCourierDetails());
	        	 return dcRepo.save(dcList.get());
	}

	public CourierDetailsRequest getCourierDetails(Long id) {
		Optional<InterDistrictDeliveryChallan> dcList= dcRepo.findById(id);

        if(dcList== null)
        {
            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
        }
        CourierDetailsRequest cRequest = new CourierDetailsRequest();
        
        cRequest.setCourierdate(dcList.get().getCourierdate());
        cRequest.setCourierno(dcList.get().getCourierno());
        cRequest.setCourierDetails(dcList.get().getCourierDetails());
        
        return cRequest;
	}

		
}
