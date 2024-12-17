package aurionpro.erp.ipms.assetmgmt.deliverychallan;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.assetmgmt.common.DCAssetView;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectService;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service()
public class DeliveryChallanService {
	
	@Autowired
    DeliveryChallanRepository dcRepo;
	
	@Autowired
    DeliveryChallanViewRepository dcViewRepo;
		
	@Autowired
	ProjectService projectService;
	
    @Autowired
    ProjectUtil projectFilterService;
	
	public DeliveryChallan createDC(DeliveryChallan dc) {
		
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR); 
		
		String dcNo = getDCNumber(dc.getProjectname());
		String projectPin = projectService.getProjectPin(dc.getProjectname());
		
		dc.setDcno("Aurionpro/"+projectPin+"/"+year+"/"+"DC"+dcNo);
		dc.setPrintflag(false);
		dc.setUploadflag(false);
		 return dcRepo.save(dc);		 
	}

	private String getDCNumber(long projectName) {
		List<String> dcNoList = dcRepo.getDCNumber(projectName);
		String dcNo = "0001";
		if (dcNoList.size() !=0)
		{
			String dcNotemp= (String)dcNoList.get(0);
			Integer dcNo1= 0;
			 String [] arrOfStr = dcNotemp.split("DC");
            dcNo1= Integer.parseInt(arrOfStr[1]);
		    dcNo = String.format("%04d",dcNo1 + 1);
		}
		return dcNo;
	}

	public DeliveryChallan updateDC(DeliveryChallan dc) {
		Optional<DeliveryChallan> dcTemp= dcRepo.findById(dc.getEntityId());
	   	    	 
	    if (dcTemp == null)
	    	throw new EntityExistsException("The Specified Delivery Challan doesn't exists");
	   		 
	   	if(!(dcTemp.get().getDcno().equalsIgnoreCase(dc.getDcno())))
	            throw new RuntimeException("DC No does not match with the Entity Dc No.");	 
	    return dcRepo.save(dc);
	}

	public Optional<DeliveryChallan> findDCById(Long id) {
		Optional<DeliveryChallan> dcObj = dcRepo.findById(id);
    	
    	if (dcObj == null)
    	{
    		throw new RuntimeException("The Specified Delivery Challan doesn't exists");
    	}
    	
    	List<DCAssetView> dcAssetList = dcRepo.findAssetByDcid(dcObj.get().getEntityId());
    	dcObj.get().setAssetRecord(dcAssetList);
		return dcObj;
	}

	public DeliveryChallan deleteDC(Long id) {
		 Optional<DeliveryChallan> dcList= dcRepo.findById(id);

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


	
	public List<DeliveryChallanView> searchDC(DeliveryChallanView dc, Integer page, Integer size) {
		dc.setIsdeleted(false);
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<DeliveryChallanView> dcEx=Example.of(dc,em);
       
        List<DeliveryChallanView> dcList;
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
	       
	       List<DeliveryChallanView> filteredProject = dcList.stream()  
	         	     .filter(f->projectId.contains(f.getProjectid())).collect(Collectors.toList());
	         
	          return filteredProject;
	}

	public Optional<DeliveryChallanView> viewDCById(Long id) {
		Optional<DeliveryChallanView> dcObj = dcViewRepo.findById(id);
    	
    	if (dcObj == null)
    	{
    		throw new RuntimeException("The Specified Delivery Challan doesn't exists");
    	}
    	
    	List<DCAssetView> dcAssetList = dcRepo.findAssetByDcid(dcObj.get().getEntityId());
    	dcObj.get().setAsset(dcAssetList);
    	
		return dcObj;
	}

	public DeliveryChallanView disablePrint(Long id, DeliveryChallan dc) {
		 Optional<DeliveryChallan> dcList= dcRepo.findById(id);

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
	        	  dcRepo.save(dcList.get());
	        	  return viewDCById(id).get();
	        }
	}

	public DeliveryChallan enableUpload(Long id) {
		 Optional<DeliveryChallan> dcList= dcRepo.findById(id);

	        if(dcList== null)
	        {
	            throw new EntityNotFoundException("The Specified Delivery Challan does not exists");
	        }

	        if (dcList.get().getPrintflag()){
	            throw new EntityNotFoundException("Upload Flag already disabled");
	        }
	        else{
	        	dcList.get().setUploadflag(true);
	        	dcList.get().setPrintflag(false);
	        	 return  dcRepo.save(dcList.get());
	        }
	}

	public DeliveryChallan addCourierDetails(CourierDetailsRequest dc, Long id) {
		 Optional<DeliveryChallan> dcList= dcRepo.findById(id);

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
		Optional<DeliveryChallan> dcList= dcRepo.findById(id);

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
