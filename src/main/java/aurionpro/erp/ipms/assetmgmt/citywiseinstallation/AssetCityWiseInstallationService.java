package aurionpro.erp.ipms.assetmgmt.citywiseinstallation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetViewRepository;
import aurionpro.erp.ipms.assetmgmt.common.AssetInstallationData;
import aurionpro.erp.ipms.common.citymaster.CityMasterRepository;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service()
@Transactional
public class AssetCityWiseInstallationService {
	
	@Autowired
    AssetCityWiseInstallationRepository assetRepo;
	
	@Autowired
    AssetCityWiseInstallationViewRepository viewRepo;
	
	@Autowired
    AssetCityWiseInstallationChildRepository childRepo;
			
	@Autowired
    AssetViewRepository assetViewRepo;
	
    @Autowired
    ProjectUtil projectFilterService;
    
	@Autowired
    CityMasterRepository cityRepo;
	
	public AssetCityWiseInstallationMaster saveReport(@Valid AssetCityWiseInstallationMaster constant) {
		Optional<AssetCityWiseInstallationMaster> reportTemp= assetRepo.findByCity(constant.getCity());
    	
		 if(reportTemp.isPresent())
         {
             throw new EntityExistsException("Report for this City already exists");
         }
		 else
		
				 validate();
				 return assetRepo.save(constant);
	}

	private void validate() {
		// TODO Auto-generated method stub
		
	}

	public List<AssetCityWiseInstallationView> reportByFilter(AssetCityWiseInstallationView asset, Integer page, Integer size) {
		 ExampleMatcher em=ExampleMatcher.matching()
	                           .withIgnoreNullValues()
	                           .withIgnoreCase()
	                           .withStringMatcher(StringMatcher.CONTAINING);

	       Example<AssetCityWiseInstallationView> assetEx=Example.of(asset,em);
	      
	       List<AssetCityWiseInstallationView> assetList = viewRepo.findAll(assetEx);
           
	       Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
	       
	       List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	       
	       List<AssetCityWiseInstallationView> filteredProject = assetList.stream()  
	         	     .filter(f->projectId.contains(f.getProject())).collect(Collectors.toList());
	         
	       
	        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
			 Pageable paging = PageRequest.of(page-1, size);
	    	   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	     		
	           return new PageImpl<AssetCityWiseInstallationView>(filteredProject.subList(start, end), paging, filteredProject.size()).getContent();
	      
	    	}
	    	else
	    	{
	    		 return filteredProject;
	    	}
		  

	}

	public AssetCityWiseInstallationMaster updateReport(@Valid AssetCityWiseInstallationMaster asset) {
		Optional<AssetCityWiseInstallationMaster> assetTemp= assetRepo.findByCity(asset.getCity());
	    	 
	   	if (assetTemp == null)
	   		throw new EntityExistsException("The Specified Report doesn't exists");
	   
		 return assetRepo.save(asset);
	}

	public Optional<AssetCityWiseInstallationMaster> findReportById(Long id) {
		return assetRepo.findById(id);
	}

	public AssetCityWiseInstallationMaster deleteReport(Long id) {
		Optional<AssetCityWiseInstallationMaster> asset= assetRepo.findById(id);

        if(asset== null)
        {
            throw new EntityNotFoundException("The Specified Report deos not exists");
        }

        if (asset.get().getIsDeleted()){
            throw new EntityNotFoundException("Report already deleted");
        }
        else{
        	asset.get().setIsDeleted(true);
            return assetRepo.save(asset.get());
        }
	}

	public List<AssetInstallationData> cityinstallation(String id) {
		List<AssetInstallationData> objList = assetViewRepo.cityinstallation(id);
		
		return objList;
	}

	public AssetCityWiseInstallationMaster disablePrint(Long id, AssetCityWiseInstallationMaster dc) {
		Optional<AssetCityWiseInstallationMaster> asset= assetRepo.findById(id);

	        if(asset== null)
	        {
	            throw new EntityNotFoundException("The Specified Report does not exists");
	        }

	        if (asset.get().getPrintflag()){
	            throw new EntityNotFoundException("Print Flag already disabled");
	        }
	        else{
	        	asset.get().setPrintflag(true);
	        	asset.get().setInstallationattachment(dc.getInstallationattachment());
	        	return assetRepo.save(asset.get());
	        }
	}

	public AssetCityWiseInstallationMaster enableUpload(Long id) {
		 Optional<AssetCityWiseInstallationMaster> assetList= assetRepo.findById(id);

	        if(assetList== null)
	        {
	            throw new EntityNotFoundException("The Specified Report does not exists");
	        }

	        if (assetList.get().getPrintflag()){
	            throw new EntityNotFoundException("Upload Flag already disabled");
	        }
	        else{
	        	assetList.get().setUploadflag(true);
	        	 return  assetRepo.save(assetList.get());
	        }
	}
}