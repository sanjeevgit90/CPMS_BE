package aurionpro.erp.ipms.ordermgmt.prs;

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
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderMaster;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderService;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service
public class PrsService {

	@Autowired
	PrsRepository prsRepo;
	
	@Autowired
	PrsViewRepository prsViewRepo;
	
    @Autowired
    ProjectUtil projectFilterService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	public Prs savePrs(@Valid Prs prsRequest) {
		validateRequest(prsRequest, "ADD");
        List<Prs> tcheck = prsRepo.findByPrsNo(prsRequest.getPrsNo());
        if (tcheck.size()>0){
            throw new RuntimeException("Prs entity already exists.");
        }
        prsRequest.setApprovalStatus("PENDING");
        prsRequest.setRequestedBy(MyPrincipal.getMyProfile().getFirstName() + " " + MyPrincipal.getMyProfile().getLastName());
        
        //Prs saveObj = prsRepo.save(prsRequest);

        return prsRepo.save(prsRequest);
	}

	public Prs updatePrs(long prsId, @Valid Prs prsRequest) {
		// TODO Auto-generated method stub
		if (prsId!=prsRequest.getEntityId())
        	throw new RuntimeException("Request mismatch");

        validateRequest(prsRequest, "UPDATE");
        
        Optional<Prs> prsTemp=prsRepo.findById(prsId);

        if (prsTemp==null) {
        	throw new RuntimeException("Prs entity does not exists.");
        }
        /*if (!prsTemp.get().getPrsNo().equalsIgnoreCase(prsRequest.getPrsNo())) {
        	throw new EntityValidationException("Prs entity is invalid", "Prs number mismatch.");
        }*/
        return prsRepo.save(prsRequest);
	}

	public Optional<Prs> getPrsById(long id) {
		// TODO Auto-generated method stub
		return prsRepo.findById(id);
	}

	public Prs deactivePrsById(long id) {
		//activate - deactivate prs
		/*Prs entity = prsRepo.findById(id).get();
		if(entity == null) {
			throw new RuntimeException("Prs entity does not exists.");
		}
		
		if(entity.isDeleted()) {
			entity.setDeleted(false);
		} else {
			entity.setDeleted(true);
		}
		return prsRepo.save(entity);*/
		
		prsRepo.deleteById(id);
		return null;
	}

	public PageImpl<PrsView> getAllPrsByFilter(PrsView prsRequest, Integer page, Integer size) {
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PrsView> prsEx = Example.of(prsRequest,em);
		List<PrsView> list  = prsViewRepo.findAll(prsEx);
		
	    if(prsRequest.getFromDate()!=null && prsRequest.getToDate()!=null) {
			if(prsRequest.getFromDate()>0 && prsRequest.getToDate()>0) {
				list = list.stream().filter(p -> (p.getPrsDate() >= prsRequest.getFromDate()) && (p.getPrsDate() <= prsRequest.getToDate())).collect(Collectors.toList());
			}
		}

		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	    List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	    List<PrsView> filteredProject = list.stream().filter(f->projectId.contains(f.getProjectName())).collect(Collectors.toList());
	         
	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
		{
			Pageable paging = PageRequest.of(page-1, size);
			int start =  (int) paging.getOffset();
		    int end = Math.min((start + paging.getPageSize()), filteredProject.size());
		    return new PageImpl<PrsView>(filteredProject.subList(start, end), paging, filteredProject.size());
		}
	   	else
	   	{
	   		return new PageImpl<PrsView>(filteredProject);
	   	}
	}

	public List<SelectionList> getSelectionPrsList() {
		return prsRepo.selectionPrsList();
	}

	private boolean validateRequest(@Valid Prs prsRequest, String op) {
		List<String> errorlist = new ArrayList<String>();
		
		if(prsRequest.getIsUtilityPayment()){
			if(StringUtils.isEmpty(prsRequest.getOffice()))
				errorlist.add("Office should not be empty/null.");
			if(StringUtils.isEmpty(prsRequest.getBillType()))
				errorlist.add("Bill type should not be empty/null.");
			if(StringUtils.isEmpty(prsRequest.getBillNo()))
				errorlist.add("Bill no. should not be empty/null.");
			if(StringUtils.isEmpty(prsRequest.getAttachedBill()))
				errorlist.add("Bill attachment should not be empty/null.");
		} else {
			if(prsRequest.getPurchaseOrderNo() == 0){
				errorlist.add("Purchase order no. should not be empty/null.");
			} else {
				PurchaseOrderMaster po = purchaseOrderService.getOrderById(prsRequest.getPurchaseOrderNo()).get();
				float totalinvoiceAmount = (op.equalsIgnoreCase("ADD")) ? getTotalAmountOfPrsByPoId(prsRequest.getPurchaseOrderNo(), 0) : getTotalAmountOfPrsByPoId(prsRequest.getPurchaseOrderNo(), prsRequest.getEntityId());
	       		float balanceAmount = (float) (po.getGrandTotal()- totalinvoiceAmount);
				if(totalinvoiceAmount+prsRequest.getInvoiceAmount() > po.getGrandTotal())
					errorlist.add("Total amount for all Invoice Payment should not exceed the PO Gross Amount(\u20B9"+ po.getGrandTotal()+"), balance amount is \u20B9"+balanceAmount);
			}
		}
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Prs entity is invalid", errorlist);
		}
        return true;
	}

	public Prs changeStatusById(long prsId, String status, String approvedBy) {
		/*Prs p = prsRepo.findById(prsId).get();
		p.setApprovalStatus(status);
		prsRepo.save(p);*/
		
		Prs entity = prsRepo.findById(prsId).get();
		entity.setApprovalStatus(status);
		if(!StringUtils.isEmpty(approvedBy)){
			if(entity.getApprovalStatus().equalsIgnoreCase("APPROVED")){
				entity.setApprovedBy(approvedBy);
			} else {
				entity.setApprovedBy(null);
			}
		}
		return prsRepo.save(entity);
	}
	
	public float getTotalAmountOfPrsByPoId(long poId, long prsId) {
		/*List<Prs> prs = prsRepo.findByPurchaseOrderNo(poId);
		
		float totalinvoiceAmount= 0.0f;
		for(Prs r : prs){
			if(!StringUtils.isEmpty(prsId))
				if(r.getEntityId() == prsId)
					continue;
			totalinvoiceAmount += r.getInvoiceAmount();
		}
		
		return totalinvoiceAmount;*/
		float totalinvoiceAmount= 0.0f;
		Object obj = prsRepo.getTotalAmountOfPrsByPoId(poId, prsId);
		if(obj!=null) {
			totalinvoiceAmount = Float.parseFloat(obj.toString());
		}
		return totalinvoiceAmount;
	}

	public Optional<PrsView> getPrsView(long id) {
		Optional<PrsView> prs = prsViewRepo.findById(id);
		prs.get().setAttachments(prsRepo.getAttachmentList(id));
		return prs;
	}
	
	public PageImpl<PrsView> getPrsReport(PrsView prsRequest, Integer page, Integer size) {
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PrsView> prsEx = Example.of(prsRequest,em);
		List<PrsView> prsData = prsViewRepo.findAll(prsEx);
		//return prsViewRepo.findAll(prsEx);
		
		//for removing data with status PRS_CANCELLED
		prsData = prsData.stream().filter(p -> p.getApprovalStatus() != "PRS_CANCELLED").collect(Collectors.toList());
		
		//for applying date filters
		if(prsRequest.getFromDate()!=null && prsRequest.getToDate()!=null) {
			if(prsRequest.getFromDate()>0 && prsRequest.getToDate()>0) {
				prsData = prsData.stream().filter(p -> (p.getPrsDate() >= prsRequest.getFromDate()) && (p.getPrsDate() <= prsRequest.getToDate())).collect(Collectors.toList());
			}
		}
		
		//applying pagination for mobile app
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
			Pageable paging = PageRequest.of(page-1, size);
			int start =  (int) paging.getOffset();
			int end = Math.min((start + paging.getPageSize()), prsData.size());
			return new PageImpl<PrsView>(prsData.subList(start, end), paging, prsData.size());
    	} else {
    		return new PageImpl<PrsView>(prsData);
    	}
	}
}
