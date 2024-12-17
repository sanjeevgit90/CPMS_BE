package aurionpro.erp.ipms.assetmgmt.assetmaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMaster;
import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMasterRepository;
import aurionpro.erp.ipms.authorization.role.Role;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileRepository;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.common.locationmaster.LocationMaster;
import aurionpro.erp.ipms.common.locationmaster.LocationMasterRepository;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service()
@Transactional
public class AssetMasterService {
	
	@Autowired
    AssetMasterRepository assetRepo;
	
	@Autowired
    AssetViewRepository asseViewtRepo;
	
	@Autowired
    AssetAuditTrailRepository assetAuditRepo;
	
	@Autowired
	UserProfileService upService;
	
	 @Autowired
	 ProjectUtil projectFilterService;
	
//	@Autowired
  //  OEMDeliveryChallanRepository dcRepo;

	public AssetMaster createAsset(AssetMaster asset) {
		AssetMaster assetTemp= assetRepo.findAsset(asset.getAssetname(),asset.getSerialno(),asset.getAssettag());
    	
		 if(assetTemp != null)
         {
             throw new RuntimeException("The Specified Asset Name already exists");
         }
		 
		 AssetMaster assetObj = assetRepo.save(asset);
		 
		 if (assetObj != null)
		 {
			if (saveAssetAudit(asset, "INSERT") )
				return assetObj;
		 }
		 
		return assetObj;
		 
	}

	public AssetMaster updateAsset(AssetMaster asset) {
		Optional<AssetMaster> assetTemp= assetRepo.findById(asset.getEntityId());
	   	    	 
	   	if (assetTemp == null)
	   		throw new RuntimeException("The Specified Asset doesn't exists");
	   		 
	   //	if(!(assetTemp.get().getAssetname().equalsIgnoreCase(asset.getAssetname())))
	    //    throw new RuntimeException("Asset Id does not match with the Asset Name");	 
	   	asset.setIsDeleted(assetTemp.get().getIsDeleted());
		 AssetMaster assetObj = assetRepo.save(asset);
		 
		 if (assetObj != null)
		 {
			if (saveAssetAudit(asset, "UPDATE") )
				return assetObj;
		 }
		 
		return assetObj;
	}

	public Optional<AssetView> findAssetById(Long id) {
		Optional<AssetView> assetObj = asseViewtRepo.findById(id);
    	
    	if (assetObj == null)
    	{
    		throw new RuntimeException("The Specified Asset doesn't exists");
    	}
		return assetObj;
	}
	
	public Optional<AssetMaster> findAsset(Long id) {
		Optional<AssetMaster> assetObj = assetRepo.findById(id);
    	
    	if (assetObj == null)
    	{
    		throw new RuntimeException("The Specified Asset doesn't exists");
    	}
		return assetObj;
	}

	public AssetMaster deleteAsset(Long id) {
		 Optional<AssetMaster> assetList= assetRepo.findById(id);

	        if(assetList== null)
	        {
	            throw new EntityNotFoundException("The Specified Asset does not exists");
	        }

	        if (assetList.get().getIsDeleted()){
	            throw new EntityNotFoundException("Record already deleted");
	        }
	        else{
	        	assetList.get().setIsDeleted(true);
	        	 AssetMaster assetObj = assetRepo.save(assetList.get());
	    		 
	    		 if (assetObj != null)
	    		 {
	    			if (saveAssetAudit(assetList.get(), "DELETED") )
	    				return assetObj;
	    		 }
	    		 
	    		return assetObj;
	        }
	}

	public List<SelectionList> getActiveAssetList() {
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		return assetRepo.getActiveAssetList(profileId);
	}

	public List<SelectionList> getAssetList() {
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
	      return assetRepo.getAssetList(profileId);
	}
	
	@Autowired
    ProductMasterRepository productRepo;
	

    @Autowired
    LocationMasterRepository locationRepo;
	
	private boolean saveAssetAudit(AssetMaster asset, String flag)
	{
		ProductMaster product = productRepo.findById(asset.getProductid()).get();
		LocationMaster location = locationRepo.findById(asset.getLocation()).get();
		
		 AssetAuditTrail auditObj = new AssetAuditTrail(asset,product,location);
			 auditObj.setOperation(flag);
			
			 AssetAuditTrail obj = assetAuditRepo.save(auditObj);
		 
			 if (obj == null)
			 {
				 throw new RuntimeException("Audit not saved");
			 }
			 else
			 {
				 return true;
			 }
		
	}

	public List<AssetAuditTrail> getAuditTrail(Long id) {
		return assetAuditRepo.getAuditTrail(id);
	}

	public void updateAssetStatus(List<AssetMaster> list, String shippedto) {
		List<Long> assetId = new ArrayList<>();
		for (AssetMaster assetElement : list) {
			Optional<AssetMaster> assetObj = findAsset(assetElement.getEntityId());
			 if (assetObj != null)
			 {
				saveAssetAudit(assetObj.get(), "UPDATE");
			 }
			assetId.add(assetElement.getEntityId());
		}
		assetRepo.updateAssetStatus(assetId,shippedto);
	}
	
	 @Autowired
	 private UserProfileRepository upRepo;

	public PageImpl<AssetView> getAssetByFilter(AssetView assetvalue, Integer page, Integer size) {
		Long userId = MyPrincipal.getMyProfile().getUserProfileId();
		
		Boolean flag = false;
    	List<Role> roleList = upRepo.findById(userId).get().getRoles();
    	for (Role role:roleList)
    	{
    		if (role.getRolename().equalsIgnoreCase("ROLE_EXTERNAL USER"))
    		{
    			flag = true;
    		}
    	}
		
		AssetView asset = castFilterValues(assetvalue);
		List<AssetView> assetList= asseViewtRepo.getAssetByFilter(asset.getAssetname(),
				asset.getProjectid(), asset.getCategory(), asset.getState(),asset.getDistrict(),
				asset.getCity(),asset.getLocation(), userId, asset.getSerialno(),asset.getAssettag(), asset.getSubcategory(),
				asset.getPolicestation(), flag);
	
	        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	           Pageable paging = PageRequest.of(page-1, size);
			   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), assetList.size());
	     		
	           return new PageImpl<AssetView>(assetList.subList(start, end), paging, assetList.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<>(assetList);
	    	}
	}
	/*
	public PageImpl<AssetView> getAssetByFilter(AssetView asset, Integer page, Integer size) {
		ExampleMatcher em=ExampleMatcher.matching()
		                 .withIgnoreNullValues()
		                 .withIgnoreCase()
		                 .withStringMatcher(StringMatcher.CONTAINING);
		
		Example<AssetView> assetEx=Example.of(asset,em);
		List<AssetView> assetList = asseViewtRepo.findAll(assetEx);
		
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
		List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
		List<AssetView> filteredProject = assetList.stream()  
		  	     .filter(f->projectId.contains(f.getEntityId())).collect(Collectors.toList());
		
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		{
		Pageable paging = PageRequest.of(page-1, size);
		int start =  (int) paging.getOffset();
		int end = Math.min((start + paging.getPageSize()), filteredProject.size());
			
		return new PageImpl<AssetView>(filteredProject.subList(start, end), paging, filteredProject.size()).getContent();
		
		}
		else
		{
			 return filteredProject;
		}
	}
	*/
	
	public List<AssetMaster> getAssetForDC(AssetMaster asset) {

		//List<String> assetList = dcRepo.getAssetsFromDeliveryChallan();
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase();
        		//			.withMatcher("asset", match -> match.transform(source -> ((assetList) source).iterator().next()).caseSensitive());
        Example<AssetMaster> assetEx=Example.of(asset,em);
       
        return assetRepo.findAll(assetEx);
	}

	public void receiveAssetByOEM(Long id, String status) {
		assetRepo.receiveAssetByOEM(id,status);	
		Optional<AssetMaster> assetObj = findAsset(id);
		 if (assetObj != null)
		 {
			saveAssetAudit(assetObj.get(), "UPDATE");
		 }
	}

	public List<SelectionList> getAssetByLocation(String name) {
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		return assetRepo.getAssetByLocation(name, profileId);
	}
	

	public List<SelectionList> getAssetTagByLocation(String name) {
		return assetRepo.getAssetTagByLocation(name);
	}
	
	
	public List<SelectionList> getAssetTagByAsset(Long entityId) {
		return assetRepo.getAssetTagByAsset(entityId);
	}

	public List<AssetView> oemAssetByFilter(AssetView asset, Integer page, Integer size) {
		 ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<AssetView> assetEx=Example.of(asset,em);
        List<AssetView> assetList = asseViewtRepo.findAll(assetEx);
          	
        List<Long> assetId = getDCAssetList("OEM");

        List<AssetView> filteredProject = assetList.stream()  
	         	     .filter(f->assetId.contains(f.getEntityId())).collect(Collectors.toList());
        
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
		 Pageable paging = PageRequest.of(page-1, size);
    	   int start =  (int) paging.getOffset();
           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
     		
           return new PageImpl<AssetView>(filteredProject.subList(start, end), paging, filteredProject.size()).getContent();
      
    	}
    	else
    	{
    		 return filteredProject;
    	}
	  

	}

	private  List<Long> getDCAssetList(String flag) {
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		 
	      if (flag.equalsIgnoreCase("OEM"))
	      {
	    	  return asseViewtRepo.getOEMDCAssetList(profileId);      
	      }
	      else
	      {
	    	  return asseViewtRepo.getDCAssetList(profileId);      
	      }
	}

	public List<AssetView> dcAssetByFilter(AssetView asset, Integer page, Integer size) {
		
        ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<AssetView> assetEx=Example.of(asset,em);
        List<AssetView> assetList = asseViewtRepo.findAll(assetEx);
        
        List<Long> assetId = getDCAssetList("DC");
        List<AssetView> filteredProject = assetList.stream()  
	         	     .filter(f->assetId.contains(f.getEntityId())).collect(Collectors.toList());
	       
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
		 Pageable paging = PageRequest.of(page-1, size);
    	   int start =  (int) paging.getOffset();
           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
     		
           return new PageImpl<AssetView>(filteredProject.subList(start, end), paging, filteredProject.size()).getContent();
      
    	}
    	else
    	{
    		 return filteredProject;
    	}
	  

	}
	
	private AssetView castFilterValues(AssetView asset) {
		AssetView assetRequest = new AssetView();
		long value = 0;
		if (asset.getAssetname() == null)
		{
			assetRequest.setAssetname("");
		}
		else
		{
			assetRequest.setAssetname(asset.getAssetname());
		}
		
		if (asset.getProjectid() == null)
		{
			assetRequest.setProjectid(value);
		}
		else
		{
			assetRequest.setProjectid(asset.getProjectid());
		}
		
		if (asset.getCategory() == null)
		{
			assetRequest.setCategory("");
		}
		else
		{
			assetRequest.setCategory(asset.getCategory());
		}
		
		if (asset.getState() == null)
		{
			assetRequest.setState("");
		}
		else
		{
			assetRequest.setState(asset.getState());
		}
		
		if (asset.getDistrict() == null)
		{
			assetRequest.setDistrict("");
		}
		else
		{
			assetRequest.setDistrict(asset.getDistrict());
		}
		
		if (asset.getCity() == null)
		{
			assetRequest.setCity("");
		}
		else
		{
			assetRequest.setCity(asset.getCity());
		}
		
		if (asset.getLocation() == null)
		{
			assetRequest.setLocation("");
		}
		else
		{
			assetRequest.setLocation(asset.getLocation());
		}
		
		if (asset.getSerialno() == null)
		{
			assetRequest.setSerialno("");
		}
		else
		{
			assetRequest.setSerialno(asset.getSerialno());
		}
		
		if (asset.getAssettag() == null)
		{
			assetRequest.setAssettag("");
		}
		else
		{
			assetRequest.setAssettag(asset.getAssettag());
		}
		
		if (asset.getSubcategory() == null)
		{
			assetRequest.setSubcategory("");
		}
		else
		{
			assetRequest.setSubcategory(asset.getSubcategory());
		}
		if (asset.getPolicestation() == null)
		{
			assetRequest.setPolicestation("");
		}
		else
		{
			assetRequest.setPolicestation(asset.getPolicestation());
		}
			
		return assetRequest;
	}
}
