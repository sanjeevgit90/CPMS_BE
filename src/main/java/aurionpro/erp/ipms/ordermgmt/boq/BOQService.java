package aurionpro.erp.ipms.ordermgmt.boq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.boqproduct.BOQProductChild;
import aurionpro.erp.ipms.ordermgmt.boqproduct.BOQProductChildRepository;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service
public class BOQService {

	@Autowired
	BOQRepository boqRepo;
	
	@Autowired
	BOQProductChildRepository boqProductChildRepo;
	
	@Autowired
	BOQAuditTrailRepository boqAuditTrailRepo;
	
    @Autowired
    ProjectUtil projectFilterService;
	
	public BOQ saveBOQ(@Valid BOQ request) {
		validateRequest(request);
        List<BOQ> tcheck = boqRepo.findByBoqNo(request.getBoqNo());
        if (tcheck.size()>0){
            throw new RuntimeException("BOQ entity already exists");
        }
        request.setBoqApprovalStatus("PENDING");
		return boqRepo.save(request);
	}

	public BOQ editBOQ(long id, @Valid BOQ request) {
		// TODO Auto-generated method stub
		if (id!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");

        validateRequest(request);
        
        Optional<BOQ> boqTemp=boqRepo.findById(id);

        if (boqTemp==null) {
        	throw new RuntimeException("BOQ entity does not exists");
        }
        if (!boqTemp.get().getBoqNo().equalsIgnoreCase(request.getBoqNo())) {
        	throw new EntityValidationException("BOQ entity is invalid", "BOQ number mismatch");
        }
		return boqRepo.save(request);
	}

	public Optional<BOQ> getSingleBOQRecord(long id) {
		return boqRepo.findById(id);
	}

	public List<BOQ> getAllBOQs(BOQ request, Integer page, Integer size) {
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<BOQ> boqEx = Example.of(request,em);
		List<BOQ> list;
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	    		Pageable paging = PageRequest.of(page, size);
	    		list = boqRepo.findAll(boqEx, paging).getContent();
	    	}
	    	else
	    	{
	    		list = boqRepo.findAll(boqEx);
	    	}
		

		Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
	    List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	       
	    List<BOQ> filteredProject = list.stream()  
	         	     .filter(f->projectId.contains(f.getAccountId())).collect(Collectors.toList());
	         
	    return filteredProject;
		//return boqRepo.findAll();
	}
	
	private boolean validateRequest(@Valid BOQ request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getBoqNo()))
			errorlist.add("BOQ no. should not be empty/null.");
		if(request.getAccountId() == 0)
			errorlist.add("Account should not be empty/null.");
		if(request.getBoqDate() == 0)
			errorlist.add("BOQ date should not be empty/null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("BOQ entity is invalid", errorlist);
		}
        return true;
	}

	public List<SelectionList> getSelectionBoqList() {
		return boqRepo.selectionBoqList();
	}

	public BOQProductChild saveBOQProductChild(@Valid BOQProductChild request) {
		validateProductRequest(request);
		return boqProductChildRepo.save(request);
	}

	public BOQProductChild editBOQProductChild(long id, @Valid BOQProductChild request) {
		// TODO Auto-generated method stub
		if (id!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");
        
		validateProductRequest(request);

        Optional<BOQProductChild> prodTemp = boqProductChildRepo.findById(id);

        if (prodTemp==null) {
        	throw new RuntimeException("BOQ Product entity does not exists");
        }
		return boqProductChildRepo.save(request);
	}
	
	public void deleteBOQProductById(long id) {
		BOQProductChild boqProd = boqProductChildRepo.findById(id).get();
		if(boqProd == null)
			throw new RuntimeException("BOQ Product entity does not exists");
		
		boqProductChildRepo.deleteById(id);
	}

	public void deleteBOQProductChild(BOQProductChild request) {
		boqProductChildRepo.deleteBOQProductChild(request.getBoq(), request.getProductId());
	}

	private boolean validateProductRequest(@Valid BOQProductChild request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getBoq()))
			errorlist.add("BOQ no. should not be empty/null.");
		if(request.getProductId() == 0)
			errorlist.add("Product should not be empty/null.");
		if(request.getRate() == 0)
			errorlist.add("Rate should not be empty/null.");
		if(request.getQuantity() == 0)
			errorlist.add("Quantity should not be empty/null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("BOQ Product entity is invalid", errorlist);
		}
        return true;
	}

	public Optional<BOQAuditTrail> getBOQProductAuditTrail(long id) {
		return boqAuditTrailRepo.findById(id);
	}

	public BOQ changeStatusById(long boqId, String status) {
		BOQ obj = boqRepo.findById(boqId).get();
		if(obj == null)
			throw new RuntimeException("BOQ entity does not exists");
		
		obj.setBoqApprovalStatus(status);
		return boqRepo.save(obj);
	}
}