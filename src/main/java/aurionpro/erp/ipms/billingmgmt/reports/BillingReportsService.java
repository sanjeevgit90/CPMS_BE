package aurionpro.erp.ipms.billingmgmt.reports;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.billingmgmt.collectiontagging.CollectionTaggingService;
import aurionpro.erp.ipms.billingmgmt.collectiontagging.CollectionView;
import aurionpro.erp.ipms.billingmgmt.invoice.InvoiceService;
import aurionpro.erp.ipms.billingmgmt.invoice.InvoiceView;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;

@Service()
public class BillingReportsService {
	
	@Autowired
    InvoiceAgeingRepository ageingRepo;
	
	@Autowired
    BillingComparsionRepository comapreRepo;
	
	@Autowired
    InvoiceService invoiceService;
	
	@Autowired
    CollectionTaggingService collectionService;
	
    @Autowired
    ProjectUtil projectFilterService;

	public List<InvoiceView> monthlyBillingReport(String month) {
		return invoiceService.monthlyBillingReport(month);
	}

	public List<CollectionView> monthlyCollectionReport(String month) {
		return collectionService.monthlyCollectionReport(month);
	}

	public List<BillingComparsion> billingComparsionReport(BillingComparsion invoice, Integer page, Integer size) {
		 List<BillingComparsion>  list = comapreRepo.billingComparsionReport(invoice.getProjectid());
		 if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
			 Pageable paging = PageRequest.of(page-1, size);
	    	   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), list.size());
	     		
	           return new PageImpl<BillingComparsion>(list.subList(start, end), paging, list.size()).getContent();
	      
	    	}
	    	else
	    	{
	    		 return list;
	    	}
		 
	}
	

	public List<InvoiceAgeingReport> InvoiceAgeingReport(InvoiceAgeingReport invoice, Integer page, Integer size) {
		invoice.setIsdeleted(false);
		ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<InvoiceAgeingReport> invoiceEx=Example.of(invoice,em);
       
        List<InvoiceAgeingReport> list;
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		list= ageingRepo.findAll(invoiceEx, paging).getContent();
    	}
    	else
    	{
    		list= ageingRepo.findAll(invoiceEx);
    	}
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
	     List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	       
	    List<InvoiceAgeingReport> filteredProject = list.stream()  
	         	     .filter(f->projectId.contains(f.getProjectid())).collect(Collectors.toList());
	         
	          return filteredProject;
	}

	public List<SelectionList> monthlyOfBillingReport(String year) {
		  String [] arrOfStr = year.split("to");
		 String startYear = arrOfStr[0];
		 String endYear = arrOfStr[1];
		return invoiceService.monthlyOfBillingReport(startYear,endYear);
	}

	public List<SelectionList> monthlyOfCollectionReport(String year) {
		 String [] arrOfStr = year.split("to");
		 String startYear = arrOfStr[0];
		 String endYear = arrOfStr[1];
		return collectionService.monthlyOfCollectionReport(startYear,endYear);
	}
	
	
		
}
