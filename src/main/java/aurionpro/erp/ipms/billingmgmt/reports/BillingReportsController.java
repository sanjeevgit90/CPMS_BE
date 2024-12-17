package aurionpro.erp.ipms.billingmgmt.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.billingmgmt.collectiontagging.CollectionView;
import aurionpro.erp.ipms.billingmgmt.invoice.InvoiceView;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/billingreports")
public class BillingReportsController {

    @Autowired
    BillingReportsService billingreportService;

    @PreAuthorize("hasAuthority('Invoice_Aging_Report_VIEW')")
    @PostMapping("/invoiceAgingReport")
    public List<InvoiceAgeingReport> invoiceAgingReport (@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody InvoiceAgeingReport invoice) {
    	return billingreportService.InvoiceAgeingReport(invoice, page, size);
    }
    
    @PreAuthorize("hasAuthority('Monthly_Billing_Report_VIEW')")
    @GetMapping("/monthlyBillingReport/{month}")
    public List<InvoiceView> monthlyBillingReport (@PathVariable(value = "month") String month) {
    	return billingreportService.monthlyBillingReport(month);
    }
    
    @PreAuthorize("hasAuthority('Monthly_Billing_Report_VIEW')")
    @GetMapping("/monthOfBillingReport/{year}")
    public List<SelectionList> monthlyOfBillingReport (@PathVariable(value = "year") String year) {
    	return billingreportService.monthlyOfBillingReport(year);
    }
    
    @PreAuthorize("hasAuthority('Monthly_Collection_Report_VIEW')")
    @GetMapping("/monthlyOfCollectionReport/{year}")
    public List<SelectionList> monthlyOfCollectionReport (@PathVariable(value = "year") String year) {
    	return billingreportService.monthlyOfCollectionReport(year);
    }
  
    @PreAuthorize("hasAuthority('Monthly_Collection_Report_VIEW')")
    @GetMapping("/monthlyCollectionReport/{month}")
    public List<CollectionView> monthlyCollectionReport (@PathVariable(value = "month") String month) {
    	return billingreportService.monthlyCollectionReport(month);
    }
        
    @PreAuthorize("hasAuthority('Billing_Comparison_Report_VIEW')")
    @PostMapping("/billingComparsionReport")
    public List<BillingComparsion> billingComparsionReport (@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody BillingComparsion invoice) {
    	return billingreportService.billingComparsionReport(invoice, page, size);
    }
    

	
 }
