package aurionpro.erp.ipms.assetmgmt.oemdeliverychallan;

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
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service()
public class OEMDeliveryChallanService {
	
	@Autowired
    OEMDeliveryChallanRepository dcRepo;
	
	@Autowired
    OEMDeliveryChallanViewRepository dcViewRepo;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	TaskMasterService tmService;
	
	@Autowired
	OEMDCTaskMasterRepository taskRepo;
	
	@Autowired
	AssetMasterService assetService;
	
    @Autowired
    ProjectUtil projectFilterService;
	
	public OEMDeliveryChallan createDC(OEMDeliveryChallan dc) {
		
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR); 
		
		String dcNo = getDCNumber(dc.getProjectname());
		String projectPin = projectService.getProjectPin(dc.getProjectname());
		
		dc.setDcno("Aurionpro/"+projectPin+"/"+year+"/"+"OEMDC"+dcNo);
	       
		dc.setPrintflag(false);
		dc.setDcstatus("PENDING");
		 return dcRepo.save(dc);		 
	}

	private String getDCNumber(long projectName) {
		List<String> dcNoList = dcRepo.getDCNumber(projectName);
		String dcNo = "0001";
		if (dcNoList.size() !=0)
		{
			String dcNotemp= (String)dcNoList.get(0);
			Integer dcNo1= 0;
			 String [] arrOfStr = dcNotemp.split("OEMDC");
		      dcNo1= Integer.parseInt(arrOfStr[1]);
		      dcNo = String.format("%04d",dcNo1 + 1);
		}
		return dcNo;
	}

	public OEMDeliveryChallan updateDC(OEMDeliveryChallan dc) {
		Optional<OEMDeliveryChallan> dcTemp= dcRepo.findById(dc.getEntityId());
	   	    	 
	   	if (dcTemp == null)
	   		throw new EntityExistsException("The Specified Delivery Challan doesn't exists");
	   		 
	   	if(!(dcTemp.get().getDcno().equalsIgnoreCase(dc.getDcno())))
	        throw new RuntimeException("DC No does not match with the Entity Dc No.");	 
	  
		 return dcRepo.save(dc);
	}

	public Optional<OEMDeliveryChallan> findDCById(Long id) {
		Optional<OEMDeliveryChallan> dcObj = dcRepo.findById(id);
    	
    	if (dcObj == null)
    	{
    		throw new RuntimeException("The Specified Delivery Challan doesn't exists");
    	}
    	List<DCAssetView> dcAssetList = dcRepo.findAssetByDcid(dcObj.get().getEntityId());
    	dcObj.get().setAssetRecord(dcAssetList);
		return dcObj;
	}

	public OEMDeliveryChallan deleteDC(Long id) {
		 Optional<OEMDeliveryChallan> dcList= dcRepo.findById(id);

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


	public OEMDeliveryChallan disablePrint(Long id, OEMDeliveryChallan dc) {
		 Optional<OEMDeliveryChallan> dcList= dcRepo.findById(id);

	        if(dcList== null)
	        {
	            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
	        }

	        if (dcList.get().getPrintflag()){
	            throw new EntityNotFoundException("Print Flag already disabled");
	        }
	        else{
	        	dcList.get().setPrintflag(true);
	        	dcList.get().setChallanattachment(dc.getChallanattachment());
	        	 return dcRepo.save(dcList.get());
	        }
	}
	
	public OEMDeliveryChallan processDC(OEMDCTaskMaster dc) {
		Optional<OEMDCTaskMaster> tcheck=taskRepo.findById(dc.getEntityId());

        if (!tcheck.isPresent()){
            throw new RuntimeException("Invalid Task Specified");
        }

        if(!tcheck.get().getDcno().equals(dc.getDcno()))
            throw new RuntimeException("Task Id does not match with the Entity Name");

       String status = tmService.ProcessTask(dc).getStatus();
       
       Optional<OEMDeliveryChallan> dcTemp= dcRepo.findByDcno(dc.getDcno());
       if (!dcTemp.isPresent()){
           throw new RuntimeException("Invalid Dc No Specified");
       }
       
       OEMDeliveryChallan obj = dcTemp.get();
       
       obj.setDcstatus(status);
       
       assetService.updateAssetStatus(obj.getAsset(), obj.getShippedto());
       return dcRepo.save(obj);
	}

	public OEMDeliveryChallan receiveAssetByOEM(Long id, String status) {
		assetService.receiveAssetByOEM(id,status);
		return null;
	}

	
	public List<OEMDeliveryChallanView> searchDC(OEMDeliveryChallanView dc, Integer page, Integer size) {
		dc.setIsdeleted(false);
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<OEMDeliveryChallanView> dcEx=Example.of(dc,em);
       
        List<OEMDeliveryChallanView> dcList;
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
	       
	       List<OEMDeliveryChallanView> filteredProject = dcList.stream()  
	         	     .filter(f->projectId.contains(f.getProjectid())).collect(Collectors.toList());
	         
	          return filteredProject;
        
	}

	public OEMDeliveryChallan submitDC( Long id) {
		Optional<OEMDeliveryChallan> dcTemp= dcRepo.findById(id);
	   	
		List<OEMDCTaskMaster> tcheck = taskRepo.findByDcno(dcTemp.get().getDcno());
        if (tcheck.size()>0){
            throw new RuntimeException("Workflow for the DC No has already been initiated");
        }

        OEMDCTaskMaster tMaster=new OEMDCTaskMaster();
        tMaster.setWorkflowName("OEM_DC");
        tMaster.setRemark("");
        tMaster.setDcno(dcTemp.get().getDcno());
       		
        TaskResponse task = tmService.CreateTask(tMaster);
        
       // dcTemp.get().setPrintflag(true);
        dcTemp.get().setDcstatus(task.getStatus());
		 return dcRepo.save(dcTemp.get());	
	}

	public Optional<OEMDeliveryChallanView> viewDCById(Long id) {
		Optional<OEMDeliveryChallanView> dcObj = dcViewRepo.findById(id);
    	
    	if (dcObj == null)
    	{
    		throw new RuntimeException("The Specified Delivery Challan doesn't exists");
    	}
    	
    	List<DCAssetView> dcAssetList = dcRepo.findAssetByDcid(dcObj.get().getEntityId());
    	dcObj.get().setAsset(dcAssetList);
    	
		return dcObj;
	}

	public Optional<OEMDCTaskMaster> taskByid(Long id) {
		return taskRepo.findById(id);
	}

	public OEMDeliveryChallan enableUpload(Long id) {
		Optional<OEMDeliveryChallan> dcList= dcRepo.findById(id);

        if(dcList== null)
        {
            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
        }

        if (dcList.get().getPrintflag()){
            throw new EntityNotFoundException("Upload Flag already disabled");
        }
        else{
        	dcList.get().setUploadflag(true);
        	 return  dcRepo.save(dcList.get());
        }
	}

	public OEMDeliveryChallan addCourierDetails(CourierDetailsRequest dc, Long id) {
		 Optional<OEMDeliveryChallan> dcList= dcRepo.findById(id);

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
		Optional<OEMDeliveryChallan> dcList= dcRepo.findById(id);

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
