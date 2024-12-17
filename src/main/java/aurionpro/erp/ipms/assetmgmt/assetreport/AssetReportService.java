package aurionpro.erp.ipms.assetmgmt.assetreport;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetViewRepository;
import aurionpro.erp.ipms.assetmgmt.common.AssetCountList;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service()
@Transactional
public class AssetReportService {
	
	@Autowired
    AssetViewRepository assetRepo;
	
	@Autowired
    AssetTPARepository assetTPARepo;
	
    @Autowired
	 ProjectUtil projectFilterService;
	
	public List<AssetCountList> getAssetCountByParameters(String parameters, String district, String state) {
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		if (parameters.equalsIgnoreCase("CITY"))
		{
			if (district != null)
			{
				return assetRepo.getCityWiseAssetCountWithDistrict(profileId, district, state);
			}
			else
			{
				return assetRepo.getCityWiseAssetCount(profileId);
			}
			
		} else if (parameters.equalsIgnoreCase("LOCATION"))
		{
			if (district != null)
			{
				return assetRepo.getLocationWiseAssetCount(profileId, district, state);
			}
			else
			{
				return assetRepo.getLocationWiseAssetCount(profileId);
			}
			
		}
		else if (parameters.equalsIgnoreCase("CATEGORY"))
		{
			if (district != null)
			{
				return assetRepo.getCategoryWiseAssetCount(profileId, district, state);
			}
			else
			{
				return assetRepo.getCategoryWiseAssetCount(profileId);
			}
			
		}
		else if (parameters.equalsIgnoreCase("SUBCATEGORY"))
		{
			if (district != null)
			{
				return assetRepo.getSubCategoryWiseAssetCount(profileId, district, state);
			}
			else
			{
				return assetRepo.getSubCategoryWiseAssetCount(profileId);
			}
								
		}
		else if (parameters.equalsIgnoreCase("CITYSUBCATEGORY"))
		{
			if (district != null)
			{

				return assetRepo.getCityandSubCategoryWiseAssetCount(profileId, district, state);
			}
			else
			{

				return assetRepo.getCityandSubCategoryWiseAssetCount(profileId);
			}
					
		}
		else if (parameters.equalsIgnoreCase("LOCATIONSUBCATEGORY"))
		{
			if (district != null)
			{
				return assetRepo.geLocationandSubCategoryWiseAssetCount(profileId, district, state);
			}
			else
			{
				return assetRepo.geLocationandSubCategoryWiseAssetCount(profileId);
			}
			
		}
		else if (parameters.equalsIgnoreCase("PROJECT"))
		{
			if (district != null)
			{
				return assetRepo.getProjectWiseAssetCount(profileId, district, state);
			}
			else
			{
				return assetRepo.getProjectWiseAssetCount(profileId);	
			}
			
		}
		else
		{
			return null;
		}
		
	}


	public List<AssetTPA> tpaReportByFilter(AssetTPA asset, Integer page, Integer size) {
		   asset.setIsdeleted(false);
	       ExampleMatcher em=ExampleMatcher.matching()
	                           .withIgnoreNullValues()
	                           .withIgnoreCase()
	                           .withStringMatcher(StringMatcher.CONTAINING);

	       Example<AssetTPA> assetEx=Example.of(asset,em);
	      
	        List<AssetTPA> assetList ;
	        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	    		Pageable paging = PageRequest.of(page, size);
	    		assetList=  (List<AssetTPA>) assetTPARepo.findAll(assetEx, paging).toList();
	    	}
	    	else
	    	{
	    		assetList=  assetTPARepo.findAll(assetEx);
	    	}
	       Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	       
	       List<AssetTPA> filteredProject = assetList.stream()  
	         	     .filter(f->projectId.contains(f.getProjectid())).collect(Collectors.toList());
	         
	          return filteredProject;
	}

	
}
