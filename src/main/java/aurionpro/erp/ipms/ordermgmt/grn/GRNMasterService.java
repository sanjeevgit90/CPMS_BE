package aurionpro.erp.ipms.ordermgmt.grn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetails;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetailsRepository;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetailsView;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetailsViewRepository;
import aurionpro.erp.ipms.ordermgmt.grnproduct.ProductList;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service
public class GRNMasterService {
	
	@Autowired
	GRNMasterRepository grnMasterRepo;
	
	@Autowired
	GRNViewRepository grnViewRepo;
	
	@Autowired
	GRNProductDetailsRepository grnProductDetailsRepo;
	
	@Autowired
	GRNProductDetailsViewRepository grnProductDetailsViewRepo;
	
	@Autowired
    ProjectUtil projectFilterService;
	
	@Autowired
	GRNReportRepository grnReportRepo;

	public GRNMaster saveGrnDetails(@Valid GRNMaster request) {
		request.setApprovalStatus("PENDING");
		return grnMasterRepo.save(request);
	}

	public GRNMaster updateGrnDetails(long id, @Valid GRNMaster request) {
        Optional<GRNMaster> grnTemp = grnMasterRepo.findById(id);

        if (grnTemp==null) {
        	throw new RuntimeException("GRN entity does not exists");
        }
        if (!grnTemp.get().getGrnNumber().equalsIgnoreCase(request.getGrnNumber())) {
        	throw new EntityValidationException("GRN entity is invalid", "GRN number mismatch");
        }
		return grnMasterRepo.save(request);
	}

	public Optional<GRNMasterView> getGrnById(long id) {
		// TODO Auto-generated method stub
		Optional<GRNMasterView> grn = grnViewRepo.findById(id);
		List<GRNProductDetailsView> grnProducts = grnProductDetailsViewRepo.findByGrnId(id);
		
		grn.get().setProdList(grnProducts);
		
		return grn;
	}

	public PageImpl<GRNMasterView> getAllGrn(GRNMasterView request, Integer page, Integer size) {
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<GRNMasterView> prsEx = Example.of(request, em);
		List<GRNMasterView> filteredProject = grnViewRepo.findAll(prsEx);
		
		/*Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	    List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	    List<GRNMasterView> filteredProject = list.stream().filter(f->projectId.contains(f.getProject())).collect(Collectors.toList());*/
	         
	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		{
			Pageable paging = PageRequest.of(page-1, size);
			int start =  (int) paging.getOffset();
		    int end = Math.min((start + paging.getPageSize()), filteredProject.size());
		    return new PageImpl<GRNMasterView>(filteredProject.subList(start, end), paging, filteredProject.size());
		}
	   	else
	   	{
	   		return new PageImpl<GRNMasterView>(filteredProject);
	   	}
	}

	public List<SelectionList> getSelectionGrnList() {
		// TODO Auto-generated method stub
		return grnMasterRepo.getSelectionGrnList();
	}

	public void deleteGrnById(long id) {
		// TODO Auto-generated method stub
		grnProductDetailsRepo.deleteByGrnId(id);
		grnMasterRepo.deleteById(id);
	}
	
	private boolean validateRequest(@Valid GRNMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getGrnNumber()))
			errorlist.add("GRN no. should not be empty/null.");
		//if(request.getAccountId() == 0)
			//errorlist.add("Account should not be empty/null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("GRN entity is invalid", errorlist);
		}
        return true;
	}

	public List<GRNMaster> findByGrnNumber(String grnNumber) {
		return grnMasterRepo.findByGrnNumber(grnNumber);
	}

	public List<GRNValidationDto> checkingSumOfQuantityValidation(Long poNo) {
		// TODO Auto-generated method stub
		return grnMasterRepo.checkingSumOfQuantityValidation(poNo);
	}

	public List<SelectionList> getSelectionGrnListByPo(long id) {
		// TODO Auto-generated method stub
		return grnMasterRepo.getSelectionGrnListByPo(id);
	}

	public List<ProductList> getProductListByPo(long poId) {
		// TODO Auto-generated method stub
		return grnMasterRepo.getProductListByPo(poId);
	}

	public GRNProductDetails saveProductDetails(List<GRNProductDetails> request, Long entityId) {
		GRNProductDetails entity = null;
		for(GRNProductDetails r : request){
			entity = new GRNProductDetails();
			entity.setGrnId(entityId);
			entity.setProductId(r.getProductId());
			entity.setQuantity(r.getQuantity());
			entity.setReceivedQuantity(r.getReceivedQuantity());
			entity.setAcceptedQuantity(r.getAcceptedQuantity());
			entity.setRejectedQuantity(r.getRejectedQuantity());
			grnProductDetailsRepo.save(entity);
		}
		return entity;
	}

	public GRNProductDetails updateProductDetails(List<GRNProductDetails> prodRequest) {
		GRNProductDetails entity = null;
		for(GRNProductDetails r : prodRequest){
			entity = grnProductDetailsRepo.findById(r.getEntityId()).get();
			entity.setGrnId(r.getGrnId());
			entity.setProductId(r.getProductId());
			entity.setQuantity(r.getQuantity());
			entity.setReceivedQuantity(r.getReceivedQuantity());
			entity.setAcceptedQuantity(r.getAcceptedQuantity());
			entity.setRejectedQuantity(r.getRejectedQuantity());
			grnProductDetailsRepo.save(entity);
		}
		return entity;
	}

	public void changeStatusById(long grnId, String status) {
		GRNMaster grn = grnMasterRepo.findById(grnId).get();
		grn.setApprovalStatus(status);
		grnMasterRepo.save(grn);
	}

	public PageImpl<GRNReportView> getGrnReport(GRNReportView request, Integer page, Integer size) {
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<GRNReportView> prsEx = Example.of(request, em);
		List<GRNReportView> filteredProject = grnReportRepo.findAll(prsEx);
		
		/*for applying project filter
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	    List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	    List<GRNMasterView> filteredProject = list.stream().filter(f->projectId.contains(f.getProject())).collect(Collectors.toList());*/
		
		//for applying date filters
		if(request.getFromDate()!=null && request.getToDate()!=null) {
			if(request.getFromDate()>0 && request.getToDate()>0) {
				filteredProject = filteredProject.stream().filter(p -> (p.getOrderDate() >= request.getFromDate()) && (p.getOrderDate() <= request.getToDate())).collect(Collectors.toList());
			}
		}

	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		{
			Pageable paging = PageRequest.of(page-1, size);
			int start =  (int) paging.getOffset();
		    int end = Math.min((start + paging.getPageSize()), filteredProject.size());
		    return new PageImpl<GRNReportView>(filteredProject.subList(start, end), paging, filteredProject.size());
		}
	   	else
	   	{
	   		return new PageImpl<GRNReportView>(filteredProject);
	   	}
	}
}
