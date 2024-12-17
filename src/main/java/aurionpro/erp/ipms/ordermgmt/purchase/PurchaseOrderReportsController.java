package aurionpro.erp.ipms.ordermgmt.purchase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.ordermgmt.productdetails.ProductsReportViewRepository;
import aurionpro.erp.ipms.ordermgmt.vendorinvoicepayment.PoFulfilmentReportView;
import aurionpro.erp.ipms.ordermgmt.vendorinvoicepayment.PoFulfilmentViewRepository;
import aurionpro.erp.ipms.projectmgmt.projectmaster.Project;
import aurionpro.erp.ipms.projectmgmt.projectmaster.ProjectRepository;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@RestController
@RequestMapping(value = "/ipms/purchaseorderreports")
public class PurchaseOrderReportsController {

	@Autowired
	PurchaseOrderViewRepository purchaseOrderViewRepo;
	
	@Autowired
	PoFulfilmentViewRepository poFulfilmentViewRepo;
	
	@Autowired
	ProductsReportViewRepository productsReportViewRepo;
	
	@Autowired
    ProjectRepository projectRepo;
	
	@Autowired
	PoWiseProductReportViewRepository poWiseProductReportViewRepo;
	
	@GetMapping("/getApprovalCount/{account}")
    public List<ApprovalReport> getApprovalCount(@PathVariable(value="account") Long account){
		System.out.println("account :: "+ account);
		if(account > 0)
			return purchaseOrderViewRepo.getApprovalCount(account);
		else
			return purchaseOrderViewRepo.getApprovalCount();
    }
	
	@PostMapping("/getAllApprovalReport")
    public List<PurchaseOrderView> getAllApprovalReport(@RequestBody PurchaseOrderView poRequest, @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PurchaseOrderView> poEx = Example.of(poRequest,em);
		//return purchaseOrderViewRepo.findAll(poEx);
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
    		Pageable paging = PageRequest.of(page, size);
    		return purchaseOrderViewRepo.findAll(poEx, paging).getContent();
    	}
		else {
    		return purchaseOrderViewRepo.findAll(poEx);
    	}
    }
	
	@PostMapping("/getAllSupplierReport")
    public List<PurchaseOrderView> getSupplierReportData(@RequestBody PurchaseOrderView poRequest, @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PurchaseOrderView> poEx = Example.of(poRequest,em);
		//return purchaseOrderViewRepo.findAll(poEx);
		
		List<PurchaseOrderView> reportData = null;
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
    		Pageable paging = PageRequest.of(page, size);
    		reportData = purchaseOrderViewRepo.findAll(poEx, paging).getContent();
    	}
		else {
			reportData = purchaseOrderViewRepo.findAll(poEx);
    	}
		
		if(poRequest.getFromDate()!=null && poRequest.getToDate()!=null) {
			if(poRequest.getFromDate()>0 && poRequest.getToDate()>0) {
				reportData = reportData.stream().filter(p -> (p.getOrderDate() >= poRequest.getFromDate()) && (p.getOrderDate() <= poRequest.getToDate())).collect(Collectors.toList());
			}
		}
		
		return reportData;
    }
	
	@PostMapping("/getProjectVendorWiseReport")
    public List<ProjectVendorWiseReportDto> getProjectVendorWiseCountReport(@RequestBody PurchaseOrderView poRequest, @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size){
		/*ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PurchaseOrderView> poEx = Example.of(poRequest,em);
		//return purchaseOrderViewRepo.findAll(poEx);
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
    		Pageable paging = PageRequest.of(page, size);
    		return purchaseOrderViewRepo.findAll(poEx, paging).getContent();
    	}
		else {
			return purchaseOrderViewRepo.findAll(poEx);
    	}*/
		List<ProjectVendorWiseReportDto> reportList = null;
		if(!StringUtils.isEmpty(poRequest.getWiseReport())) {
			if(poRequest.getWiseReport().equalsIgnoreCase("PROJECT")) {
				if(poRequest.getFromDate() != null && poRequest.getToDate() != null) {
					reportList = purchaseOrderViewRepo.getProjectWiseDateReport(poRequest.getFromDate(), poRequest.getToDate());
				} else {
					reportList = purchaseOrderViewRepo.getProjectWiseReport();
				}
			} else if(poRequest.getWiseReport().equalsIgnoreCase("SUPPLIER")) {
				if(poRequest.getFromDate() != null && poRequest.getToDate() != null) {
					reportList = purchaseOrderViewRepo.getVendorWiseDateReport(poRequest.getFromDate(), poRequest.getToDate());
				} else {
					reportList = purchaseOrderViewRepo.getVendorWiseReport();
				}
			}
		}
		return reportList;
    }
	@Autowired
	 ProjectUtil projectFilterService;  
	 
	@PostMapping("/getPoFulfilmentReport")
    public PageImpl<PoFulfilmentReportView> getPoFulfilmentReport(@RequestBody PoFulfilmentReportView request, @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PoFulfilmentReportView> poEx = Example.of(request,em);
		
		List<PoFulfilmentReportView> reportData = poFulfilmentViewRepo.findAll(poEx);
 
		if(request.getFromDate()!=null && request.getToDate()!=null) {
			if(request.getFromDate()>0 && request.getToDate()>0) {
				reportData = reportData.stream().filter(p -> (p.getOrderDate() >= request.getFromDate()) && (p.getOrderDate() <= request.getToDate())).collect(Collectors.toList());
			}
		}
		 Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	       
		   List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
		       
		   List<PoFulfilmentReportView> filteredData = reportData.stream()  
		         	     .filter(f->projectId.contains(f.getProjectId())).collect(Collectors.toList());
		         
		   if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
			 Pageable paging = PageRequest.of(page-1, size);
	    	   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), filteredData.size());
	     		
	           return new PageImpl<PoFulfilmentReportView>(filteredData.subList(start, end), paging, filteredData.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<PoFulfilmentReportView>(filteredData);
	    	}

		}
	
	@PostMapping("/getCatWiseReport")
    public List<PurchaseOrderView> getCatWiseReport(@RequestBody PurchaseOrderView request, @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size){
		int flag = 0;
		Optional<Project> proj = null;
		String projectName = null;
		if(!StringUtils.isEmpty(request.getAccountName())) {
			proj = projectRepo.findById(request.getAccountName());
			if(proj.isPresent()) {
				projectName = proj.get().getProjectName();
				flag = 4;
			}
		}

		List<Long> list1 = null, list2 = null, list3 = null;
		if(!StringUtils.isEmpty(request.getCategory()) && StringUtils.isEmpty(request.getSubCategory())) {
			list1 = productsReportViewRepo.getPoIdListByCategory(request.getCategory());
			if(!list1.isEmpty() && list1!=null) flag = 1;
		}
		if(!StringUtils.isEmpty(request.getSubCategory())) {
			list2 = productsReportViewRepo.getPoIdListBySubCategory(request.getSubCategory());
			if(!list2.isEmpty() && list2!=null) flag = 2;
		}
		if(!StringUtils.isEmpty(request.getProductId())) {
			list3 = productsReportViewRepo.getPoIdListByProduct(request.getProductId());
			if(!list3.isEmpty() && list3!=null) flag = 3;
		}
		
		List<PurchaseOrderView> poData = null;
		switch (flag) {
		case 0:
			poData = purchaseOrderViewRepo.findAll();
			break;
		case 1:
			poData = productsReportViewRepo.getCatWiseReportData(list1, projectName);
			break;
		case 2:
			poData = productsReportViewRepo.getCatWiseReportData(list2, projectName);
			break;
		case 3:
			poData = productsReportViewRepo.getCatWiseReportData(list3, projectName);
			break;
		case 4:
			poData = productsReportViewRepo.getCatWiseReportData(projectName);
			break;
		}
		
		if(request.getFromDate()!=null && request.getToDate()!=null) {
			if(request.getFromDate()>0 && request.getToDate()>0) {
				poData = poData.stream().filter(p -> (p.getOrderDate() >= request.getFromDate()) && (p.getOrderDate() <= request.getToDate())).collect(Collectors.toList());
			}
		}
		
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
			Pageable paging = PageRequest.of(page-1, size);
			int start =  (int) paging.getOffset();
			int end = Math.min((start + paging.getPageSize()), poData.size());
			return new PageImpl<PurchaseOrderView>(poData.subList(start, end), paging, poData.size()).getContent();
    	} else {
    		return poData;
    	}
		//return poData;
    }
	
	// view po wise products 
	@PostMapping("/getpowiseproductreport")
	public List<PoWiseProductReportView> getPoWiseProductReport(@RequestBody PoWiseProductReportView request, @RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size){
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PoWiseProductReportView> poEx = Example.of(request,em);
		
		List<PoWiseProductReportView> reportData = null;
		if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size))) {
    		Pageable paging = PageRequest.of(page, size);
    		reportData = poWiseProductReportViewRepo.findAll(poEx, paging).getContent();
    	}
		else {
			reportData = poWiseProductReportViewRepo.findAll(poEx);
    	}

		if(request.getFromDate()!=null && request.getToDate()!=null) {
			if(request.getFromDate()>0 && request.getToDate()>0) {
				reportData = reportData.stream().filter(p -> (p.getOrderDate() >= request.getFromDate()) && (p.getOrderDate() <= request.getToDate())).collect(Collectors.toList());
			}
		}
		
		return reportData;
    }
}
